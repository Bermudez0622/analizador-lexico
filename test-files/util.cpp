#include <iostream>
#include <algorithm>
#include <Poco/JSON/Object.h>

#include "util/util.hpp"
#include "controllers/algorithmcontroller.hpp"
#include "util/constants.hpp"

auto algorithm_controller = AlgorithmController::get_instance();

bool isNumber(const std::string str)
{
    return str.find_first_not_of("0123456789") == std::string::npos;
}

unsigned int to_number(const std::string str)
{
    return std::stoi(str);
}

const int get_process_index(Process *process)
{
    auto process_list = algorithm_controller->get_process();
    std::cout << "Obteniendo indice" << std::endl;
    for (int i = 0; i < process_list->size(); ++i)
    {
        if (process_list->at(i) == process)
        {
            std::cout << "Indice retornado: " << i << std::endl;
            return i;
        }
    }
    std::cout << "Indice no encontrado " << std::endl;
    return -1;
}

const unsigned int get_max_incomming()
{
    auto process_list = algorithm_controller->get_process();
    unsigned int max_incomming = 0;
    for (auto process : *process_list)
    {
        if (process->get_incomming() > max_incomming)
        {
            max_incomming = process->get_incomming();
        }
    }
    return max_incomming;
}

Process *get_last_executed_process()
{
    auto process_list = CriticalSection::get_instance()->get_global_queue();

    std::cout << "Buscando ultimo proceso ejecutado: " << std::endl;
    unsigned int max_end = 0;
    Process *last_executed_process = nullptr;
    for (auto process : *process_list)
    {
        std::cout << &process <<std::endl;
        if (last_executed_process)
        {
            if (process->get_end() > max_end)
            {
                max_end = process->get_end();
                last_executed_process = process;
            }
        }else{
            last_executed_process = process;
        }
    }
    std::cout << "ultimo proceso ejecutado: " << last_executed_process->get_name() << std::endl;
    return last_executed_process;
}

void calculate_process(Process *process)
{
    std::cout << "Calculando información del proceso " << std::endl;

    Process *last_executed_process = get_last_executed_process();

    std::cout << "Last Process Executed: " << last_executed_process->get_name() << std::endl;

    if (!last_executed_process || process == last_executed_process)
    {
        std::cout << "No hay proceso ejecutado o es el mismo" << std::endl;
        process->set_start(0);
    }
    else
    {
        std::cout << "Hay proceso ejecutado" << std::endl;
        process->set_start(std::max(last_executed_process->get_end(), process->get_incomming()));
    }

    process->set_end(process->get_start() + process->get_job());
    process->set_turnaround(process->get_end() - process->get_incomming());
    process->set_waiting(process->get_turnaround() - process->get_job());
}

Process *generate_remanent_process(Process *process, std::string name, unsigned int job, ALGORITHM_QUEUE_INDEX index)
{
    std::cout << "Generando Proceso remanente" << std::endl;
    std::cout << "(1) current Process: " << process->get_name() << std::endl;

    Process *new_process = new Process(name, process->get_incomming());
    std::cout << "(1) new Process: " << new_process->get_name() << " " << new_process->get_job() << std::endl;

    const unsigned int total_job = process->get_job();
    std::cout << "Rafaga total: " << total_job << std::endl;

    process->set_job(job);
    new_process->set_job(total_job - job);

    std::cout << "(2) current Process: " << process->get_name() << std::endl;
    std::cout << "(2) new Process: " << new_process->get_name() << std::endl;

    algorithm_controller->add_process(new_process, index);

    return new_process;
}

Process *generate_remanent_process(Process *process, std::string name, ALGORITHM_QUEUE_INDEX index)
{
    return generate_remanent_process(process, name, QUANTUM_TIME, index);
}

Poco::JSON::Array serialize(std::vector<Process*>* processList) {
    Poco::JSON::Array jsonArray;
    Poco::JSON::Object jsonObject;
    for(auto process : *processList) {
        jsonObject.set("name", process->get_name());
        jsonObject.set("incommingTime", process->get_incomming());
        jsonObject.set("burst", process->get_job());
        jsonObject.set("startTime", process->get_start());
        jsonObject.set("endTime", process->get_end());
        jsonObject.set("turnaroundTime", process->get_turnaround());
        jsonObject.set("waitingTime", process->get_waiting());
        jsonArray.add(jsonObject);
    }
    return jsonArray;
}

bool all_process_executed(std::vector<Process*>* processList) {
    for(auto process : *processList) {
        if(process->get_end() == 0) {
            return false;
        }
    }
    return true;
}