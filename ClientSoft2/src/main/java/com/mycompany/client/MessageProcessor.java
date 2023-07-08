package com.mycompany.client;

import com.google.gson.Gson;
import com.mycompany.controller.ClientController;
import com.mycompany.dto.Message;

public abstract class MessageProcessor {

    public void processMessage(String msg) {
        Message message = parsearStringTOServerMessage(msg);
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
                ClientController.getInstance().setAvailableDocuments(content);
                break;
            }
            case "_SERVER_POST_DOCUMENT_": {
                System.out.println("llego el documento");
                ClientController.getInstance().setDownloadDocument(content);
                break;
            }
            case "_SERVER_POST_MESSAGE_": {
                ClientController.getInstance().setMessage(content);
                break;
            }
            case "_CLIENT_GET_DOCUMENT_": {
                sendServerMessage(message);
                break;
            }
            case "_ERROR_": {
                ClientController.getInstance().setMessage(content);
                break;
            }
            case "_REJECT_": {
                ClientController.getInstance().setConnectionReject(content);
                break;
            }
            case "_ADMITTED_": {
                ClientController.getInstance().setConnectionAdmitted(content);
                break;
            }
            case "_DISCONNECT_":{
                sendServerMessage(message);
            }
            default:
                sendServerMessage( new Message("_ERROR_", "Unknown request: " + request));
                break;
        }
    }
    
    private Message parsearStringTOServerMessage(String jsonMessage) {
        Gson gson = new Gson();
        return gson.fromJson(jsonMessage, Message.class);
    }
    
    protected abstract void sendServerMessage(Message message);
}
