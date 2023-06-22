package com.mycompany.client;

import com.google.gson.Gson;
import com.mycompany.controller.ClientController;
import com.mycompany.dto.Message;
import java.io.IOException;
import java.net.Socket;

public class Client extends MessageProcessor implements Runnable {

    private CommunicationHandler communicationHandler;
    private Socket serverSocket;
    private Message serverMessage;

    public void connectToServer(int port, String host) throws IOException {
        try {
            serverSocket = new Socket(host, port);
            communicationHandler = new CommunicationHandler(serverSocket);
            communicationHandler.createStreams();
            System.out.println("Server address: " + serverSocket.getInetAddress().getHostAddress());
        } catch (IOException ex) {
            //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException("Error al conectar con el servidor. Verifique que la dirección IP y el puerto correspondan al servidor.", ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            String message = communicationHandler.receiveMessage();
            if (message == null) {
                break;
            }
            serverMessage = parsearStringTOServerMessage(message);
            processMessage(serverMessage);
        }
    }

    public void sendRequest(String request, String content) {
        serverMessage = new Message(request, content);
        processMessage(serverMessage);
    }

    @Override
    protected void sendResponse(String request, String content) {
        Message responseMessage = new Message(request, content);
        sendServerMessage(responseMessage);
    }

    @Override
    protected void sendErrorResponse(String errorMessage) {
        Message responseMessage = new Message("_ERROR_", errorMessage);
        sendServerMessage(responseMessage);
    }

    @Override
    protected void sendServerMessage(Message message) {
        Gson gson = new Gson();
        communicationHandler.sendMessage(gson.toJson(message));
    }

    @Override
    protected void setDocumentList(String content) {
        ClientController.getInstance().setAvailableDocuments(content);
    }

    @Override
    protected void setResponseMessage(String content) {
        ClientController.getInstance().setMessage(content);
    }

    public String getIpClient() {
        return serverSocket.getLocalAddress().getHostAddress();
    }

    @Override
    protected void setDocument(String content) {
        ClientController.getInstance().setDownloadDocument(content);
    }

    @Override
    protected void setConnectionReject(String content) {
        ClientController.getInstance().setConnectionReject(content);
    }

    @Override
    protected void setConnectionAdmitted(String content) {
        ClientController.getInstance().setConnectionAdmitted(content);
    }
}
