package com.mycompany.interfaces;


public interface IServer {

    void start(int port, int maxClients);

    void closeServer();

    Object getServerController();

    void setServerController(Object serverController);

}
