package com.mycompany.server;

import com.google.gson.Gson;
import com.mycompany.dto.Message;
import com.mycompany.controller.ServerController;
import com.mycompany.dto.ClientDTO;
import com.mycompany.interfaces.ICommunicationHandler;

public class MessageProcessor {

    private Message message;
    private final ICommunicationHandler communicationHandler;
    private final ServerController serverController;

    public MessageProcessor(ICommunicationHandler communicationHandler, ServerController serverController) {
        this.communicationHandler = communicationHandler;
        this.serverController = serverController;
    }

    public void processMessage(String msg) {
        this.message = parsearStringTOMessage(msg);
        String request = message.getRequest();
        String content = message.getContent();

        switch (request) {
            case "_CLIENT_GET_DOCUMENT_LIST_": {
                serverController.sendDocumentList(communicationHandler);
                break;
            }
            case "_CLIENT_POST_DOCUMENT_": {
                serverController.receiveDocument(communicationHandler, content);
                break;
            }
            case "_CLIENT_GET_DOCUMENT_": {
                serverController.sendDocument(communicationHandler, content);
                break;
            }
            case "_ADMITTED_": {
                serverController.sendMessage(communicationHandler, msg);
                serverController.addClientList(new ClientDTO(communicationHandler.getClientSocket()));
                break;
            }
            case "_SERVER_POST_MESSAGE_": {
                serverController.sendMessage(communicationHandler, msg);
                break;
            }
            case "_DISCONNECT_": {
                serverController.addMessageToBuffer(content);
                break;
            }
            default:
                // Request desconocido, enviar respuesta de error
                String cont = "Unknown request: " + request;
                serverController.sendMessage(communicationHandler, cont);
                break;
        }
    }

    protected Message parsearStringTOMessage(String jsonMessage) {
        Gson gson = new Gson();
        return gson.fromJson(jsonMessage, Message.class);
    }

}
