#include <gtkmm/application.h>
#include <iostream>
#include "util/httpclient.hpp"
#include "view/app/window.hpp"
#include "controllers/appcontroller.hpp"
#include "util/constants.hpp"

int main(int argc, char *argv[]) {
    AppController* controller = AppController::get_instance();
    const auto app = Gtk::Application::create(PROJECT_NAME);
    controller->set_app(app);

    MainWindow main_window;

    return app->run(main_window, argc, argv);
}