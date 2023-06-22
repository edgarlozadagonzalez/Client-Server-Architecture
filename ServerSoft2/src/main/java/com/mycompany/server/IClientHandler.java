package com.mycompany.server;

public interface IClientHandler extends Runnable {

    void setCommunicationHandler(ClientCommunicationHandler communicationHandler);
    @Override
    void run();
}
