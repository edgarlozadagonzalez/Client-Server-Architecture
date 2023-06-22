package com.mycompany.server;

import com.google.gson.Gson;
import com.mycompany.dto.Message;

public abstract class MessageProcessor {

    public void processMessage(Message message) {
        String request = message.getRequest();
        String content = message.getContent();

        switch (request) {
            case "_CLIENT_GET_DOCUMENT_LIST_": {
                request = "_SERVER_POST_DOCUMENT_LIST_";
                content = getDocumentList();
                sendResponse(request, content);
                break;
            }
            case "_CLIENT_POST_DOCUMENT_": {
                request = "_SERVER_POST_MESSAGE_";
                content = addDocumenToList(content);
                sendResponse(request, content);
                break;
            }
            case "_CLIENT_GET_DOCUMENT_": {
                request = "_SERVER_POST_DOCUMENT_";
                System.out.println("recibida peticion");
                content = getDocument(content);
                sendResponse(request, content);
                System.out.println("documento enviado");
                break;
            }
            default:
                // Request desconocido, enviar respuesta de error
                sendErrorResponse("Unknown request: " + request);
                break;
        }
    }

    protected abstract String getDocumentList();
    protected abstract String addDocumenToList(String content);
    protected abstract String getDocument(String content);
    protected abstract void sendResponse(String request, String content);
    protected abstract void sendErrorResponse(String errorMessage);
    protected abstract void sendClientMessage(Message message);

    protected Message parsearStringTOMessage(String jsonMessage) {
        Gson gson = new Gson();
        return gson.fromJson(jsonMessage, Message.class);
    }

}
