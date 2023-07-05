package com.mycompany.main;


import com.mycompany.controller.ServerController;
import com.mycompany.server.Server;
import com.mycompany.service.ServerServiceImpl;
import com.mycompany.view.ServerConsole;

public class Main {

    public static void main(String[] args) {

        Server server = new Server();

        ServerConsole viewConsole = new ServerConsole();

        ServerServiceImpl serverServices = new ServerServiceImpl();
        
        
        ServerController serverController = new ServerController(server, viewConsole, serverServices);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                 serverController.closeServer();
            }
        });

        server.setServerController(serverController);
        viewConsole.setServerController(serverController);

        viewConsole.start();

    }
}
