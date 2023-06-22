package com.mycompany.server;

import com.google.gson.Gson;
import com.mycompany.controller.ServerController;
import com.mycompany.dto.Message;

public class ClientHandler extends MessageProcessor implements IClientHandler {

    private ClientCommunicationHandler communicationHandler;
    private Message clientMessage;

    @Override
    public void run() {
        while (true) {
            String message = communicationHandler.receiveMessage();
            if (message == null) {
                disconnect();
                break;
            }
            clientMessage = parsearStringTOMessage(message);
            processMessage(clientMessage);
        }
    }

    private void disconnect() {
        ClientPool.getInstance().releaseClient(this);
    }

    @Override
    public void setCommunicationHandler(ClientCommunicationHandler communicationHandler) {
        this.communicationHandler = communicationHandler;
        communicationHandler.createStreams();
        clientMessage = new Message("_ADMITTED_","¡Has sido admitido en nuestro servidor! ¡Bienvenido!");
        sendClientMessage(clientMessage);
        clientMessage = new Message("_SERVER_POST_MESSAGE_", "Bienvenido al servidor: " + communicationHandler.getClientSocket().getInetAddress().getHostName());
        sendClientMessage(clientMessage);
    }

    @Override
    protected void sendResponse(String request, String content) {
        Message responseMessage = new Message(request, content);
        sendClientMessage(responseMessage);
    }

    @Override
    protected void sendErrorResponse(String errorMessage) {
        Message responseMessage = new Message("_ERROR_", errorMessage);
        sendClientMessage(responseMessage);
    }

    @Override
    protected void sendClientMessage(Message message) {
        Gson gson = new Gson();
        communicationHandler.sendMessage(gson.toJson(message));
    }

    @Override
    protected String getDocumentList() {
        return ServerController.getInstance().getDocumentListJson();
    }

    @Override
    protected String addDocumenToList(String content) {
        ServerController.getInstance().receiveDocumentsFromClient(content);
        return "Documento recibido y almacenado en el servidor";
    }

    @Override
    protected String getDocument(String content) {
       return ServerController.getInstance().getDocument(content);
    }
}
