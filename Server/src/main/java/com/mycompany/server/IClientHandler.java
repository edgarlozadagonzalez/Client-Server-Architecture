package com.mycompany.server;

import java.net.Socket;

public interface IClientHandler extends Runnable {

    void setClientSocket(Socket socket);

    @Override
    void run();
}
