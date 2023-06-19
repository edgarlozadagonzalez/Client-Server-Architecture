package com.mycompany.server;

import com.mycompany.controller.ServerController;
import java.net.Socket;

public class ClientHandler implements IClientHandler {

    private Socket clientSocket;
    private final ClientCommunicationHandler communicationHandler;

    public ClientHandler() {
        this.communicationHandler = new ClientCommunicationHandler();
    }

    private void sendMessage(String message) {
        communicationHandler.sendMessage(message);
    }

    private String receiveMessage() {
        return communicationHandler.receiveMessage();
    }

    private void createStreams() {
        communicationHandler.createStreams();
    }

    private void disconnect() {
        ClientPool.getInstance().releaseClient(this);
    }

    @Override
    public void setClientSocket(Socket socket) {
        this.clientSocket = socket;
        communicationHandler.setClientSocket(socket);
        createStreams();
        sendMessage("Bienvenido al servidor:" + clientSocket.getInetAddress().getHostName());
    }

    @Override
    public void run() {
        while (true) {
            String message = receiveMessage();
            if (message == null) {
                disconnect();
                break;
            }
            ServerController.getInstance().receiveDocumentsFromClient(message);
        }
    }
}
