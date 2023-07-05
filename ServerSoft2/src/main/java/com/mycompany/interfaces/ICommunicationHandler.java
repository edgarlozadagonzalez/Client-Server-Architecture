package com.mycompany.interfaces;

import java.net.Socket;

public interface ICommunicationHandler {

    void createStreams();

    void sendMessage(String message);

    String receiveMessage();

    Socket getClientSocket();

    void setClientSocket(Socket clientSocket);
}
