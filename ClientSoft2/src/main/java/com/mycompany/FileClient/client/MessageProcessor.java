package com.mycompany.FileClient.client;

import com.google.gson.Gson;
import com.mycompany.FileClient.dto.Message;

public abstract class MessageProcessor {

    public void processMessage(Message message) {
        String request = message.getRequest();
        String content = message.getContent();

        switch (request) {
            case "_CLIENT_GET_DOCUMENT_LIST_": {
                sendServerMessage(message);
                break;
            }
            case "_CLIENT_POST_DOCUMENT_": {
                sendServerMessage(message);
                break;
            }
            case "_SERVER_POST_DOCUMENT_LIST_": {
                setDocumentList(content);
                break;
            }
            case "_SERVER_POST_DOCUMENT_": {
                System.out.println("llego el documento");
                setDocument(content);
                break;
            }
            case "_SERVER_POST_MESSAGE_": {
                setResponseMessage(content);
                break;
            }
            case "_CLIENT_GET_DOCUMENT_": {
                sendServerMessage(message);
                break;
            }
            case "_ERROR_": {
                setResponseMessage(content);
                break;
            }
            case "_REJECT_": {
                setConnectionReject(content);
                break;
            }
            case "_ADMITTED_": {
                setConnectionAdmitted(content);
                break;
            }
            default:
                // Request desconocido, enviar respuesta de error
                sendErrorResponse("Unknown request: " + request);
                break;
        }
    }

    protected abstract void setDocumentList(String content);

    protected abstract void setDocument(String content);

    protected abstract void setConnectionReject(String content);

    protected abstract void setConnectionAdmitted(String content);

    protected abstract void setResponseMessage(String content);

    protected abstract void sendResponse(String request, String content);

    protected abstract void sendErrorResponse(String errorMessage);

    protected abstract void sendServerMessage(Message message);

    protected Message parsearStringTOServerMessage(String jsonMessage) {
        Gson gson = new Gson();
        return gson.fromJson(jsonMessage, Message.class);
    }
}

