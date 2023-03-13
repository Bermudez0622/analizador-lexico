#include <iostream>
#include "controllers/algorithmcontroller.hpp"
#include "controllers/rendercontroller.hpp"
#include "model/core/criticalSection.hpp"
#include "model/resolvers/firstcomefirstsolve.hpp"
#include "model/resolvers/roundRobinSolver.hpp"
#include "model/resolvers/sfjSolver.hpp"
#include "util/constants.hpp"
#include "util/util.hpp"
#include "util/httpclient.hpp"

AlgorithmController *AlgorithmController::instance = nullptr;
AlgorithmController::AlgorithmController() : current_queue(nullptr), resolver_thread(nullptr), waiting_thread(nullptr),
                                             solver(nullptr)
{
    auto critical_section = CriticalSection::get_instance();
    current_queue = critical_section->get_algorithm_queue(ALGORITHM_QUEUE_INDEX::ROUND_ROBIN_QUEUE_INDEX);
}
AlgorithmController::~AlgorithmController()
{
    for (auto process : *this->current_queue)
    {
        delete process;
    }
    delete current_queue;
}

AlgorithmController *AlgorithmController::get_instance()
{
    if (!instance)
    {
        instance = new AlgorithmController();
    }
    return instance;
}

void AlgorithmController::add_process(Process *process, ALGORITHM_QUEUE_INDEX index)
{
    auto render_controller = RenderController::get_instance();
    auto critical_section = CriticalSection::get_instance();
    critical_section->add_process_to_global_queue(process);
    critical_section->add_process_to_queue(process, index);
    render_controller->set_process(critical_section->get_global_queue());
    render_controller->set_process(critical_section->get_algorithm_queue());
    render_controller->notify();
    critical_section->get_current_process(index);
}

void AlgorithmController::set_state(bool state)
{
    auto render_controller = RenderController::get_instance();
    render_controller->set_state(state);
}

std::vector<Process *> *AlgorithmController::get_process()
{
    return current_queue;
}

void AlgorithmController::set_algorithm_solver(AlgorithmSolver *solver)
{
    this->solver = solver;
}

void AlgorithmController::start_solver()
{
    std::cout << "AlgorithmController::start_solver()" << std::endl;
    solver->setIsActive(true);
    solver->resolve();
    set_state(true);
    solver->setIsActive(false);
}

void AlgorithmController::start_waiting(bool* has_finished) {
    std::cout << "AlgorithmController::start_waiting() FIRST CALL " << *has_finished << std::endl;
    auto critical_section = CriticalSection::get_instance();
    auto algorithms_queue = critical_section->get_algorithm_queue();
    do {
        for (auto algorithm_queue : *algorithms_queue) {
            if (algorithm_queue != current_queue) {
                if(algorithm_queue > current_queue) {
                    std::cout << "AlgorithmController::start_waiting() " << "next queue" << std::endl;
                }
            }
        }
        std::this_thread::sleep_for(std::chrono::milliseconds(SLEEP_TIME));
    } while(!all_process_executed(critical_section->get_global_queue()));
    *has_finished = true;
}

void AlgorithmController::run()
{
    do
    {
        std::cout << "AlgorithmController::run()" << std::endl;
        set_state(false);
        // TODO:Buscar cual cola (en orden de proiridad que se debe procesar)
        ALGORITHM_QUEUE_INDEX currentAlgorithm = ALGORITHM_QUEUE_INDEX::ROUND_ROBIN_QUEUE_INDEX;
        auto critical_section = CriticalSection::get_instance();
        bool canBreak = false;

        do
        {
            current_queue = critical_section->get_algorithm_queue(currentAlgorithm);
            for (auto process : *current_queue)
            {
                std::cout << "Name Process: " << process->get_name() << std::endl;
                if (process->get_end() == 0)
                {
                    canBreak = true;
                    break;
                }
            }
            if (canBreak)
            {
                break;
            }
            else
            {
                if (currentAlgorithm == ALGORITHM_QUEUE_INDEX::ROUND_ROBIN_QUEUE_INDEX)
                {
                    currentAlgorithm = ALGORITHM_QUEUE_INDEX::FCFS_QUEUE_INDEX;
                }
                else if (currentAlgorithm == ALGORITHM_QUEUE_INDEX::FCFS_QUEUE_INDEX)
                {
                    currentAlgorithm = ALGORITHM_QUEUE_INDEX::SJF_QUEUE_INDEX;
                }
            }

        } while (currentAlgorithm != ALGORITHM_QUEUE_INDEX::SJF_QUEUE_INDEX);

        std::cout << "CurrentAlgorithm: " << currentAlgorithm << std::endl;

        switch (currentAlgorithm)
        {
        case ALGORITHM_QUEUE_INDEX::ROUND_ROBIN_QUEUE_INDEX:
            std::cout << "ENTRO" << std::endl;
            set_algorithm_solver(new RoundRobinSolver());
            break;
        case ALGORITHM_QUEUE_INDEX::FCFS_QUEUE_INDEX:
            set_algorithm_solver(new FirstComeFirstSolve());
            break;
        case ALGORITHM_QUEUE_INDEX::SJF_QUEUE_INDEX:
            set_algorithm_solver(new SfjSolver());
            break;
        }
        bool has_finished = false;
        current_queue = critical_section->get_algorithm_queue(currentAlgorithm);
        std::cout << "CREANDO HILO: " << std::endl;
        resolver_thread = new std::thread(&AlgorithmController::start_solver, this);
        waiting_thread = new std::thread(&AlgorithmController::start_waiting, this, &has_finished);
        if (resolver_thread->joinable())
        {
            resolver_thread->join();
            delete resolver_thread;
        }
        std::cout << "AlgorithmController::start_waiting() VERIFIYNG CALL " << has_finished << std::endl;
        if(has_finished && waiting_thread->joinable())
        {
            waiting_thread->join();
            delete waiting_thread;
        }
    } while (!all_process_executed(CriticalSection::get_instance()->get_global_queue()));
    set_image();
}

void AlgorithmController::set_image()
{
    std::string image_location = send_post("http://127.0.0.1:3000/graph", CriticalSection::get_instance()->get_global_queue());
    auto render_controller = RenderController::get_instance();
    render_controller->set_image(image_location);
}

void AlgorithmController::notify()
{
    auto render_controller = RenderController::get_instance();
    render_controller->notify();
}