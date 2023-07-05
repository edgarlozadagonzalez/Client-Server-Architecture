package com.mycompany.interfaces;

public interface IClientHandler extends Runnable {

    void connect(ICommunicationHandler communicationHandler,Object serverController);

    void disconnect();

    @Override
    void run();
}
