package com.mycompany.service;

import com.mycompany.utils.ClientUtils;
import com.mycompany.utils.DocumentUtils;
import com.google.gson.Gson;
import com.mycompany.controller.ServerController;
import com.mycompany.dto.ClientDTO;
import com.mycompany.dto.DocumentDTO;
import com.mycompany.dto.Message;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.interfaces.IServerServices;
import com.mycompany.interfaces.ICommunicationHandler;

public class ServerServiceImpl implements IServerServices {

    
    @Override
    public String sendDocumentList(ICommunicationHandler communicationHandler,DocumentUtils documentUtils) {
        String request = "_SERVER_POST_DOCUMENT_LIST_";
        List<DocumentDTO> documentos = documentUtils.getDocumentsList();
        String content = documentUtils.convertDocumentListToJson(documentos);
        String json = mapMessage(request, content);
        sendMessage(communicationHandler, json);
        return "Lista de documentos enviada al cliente " + communicationHandler.getClientSocket().getInetAddress().getHostAddress();
    }

    @Override
    public String sendClientList(ICommunicationHandler communicationHandler,ClientUtils clientUtils) {
        String request = "_SERVER_POST_CLIENT_LIST_";
        List<ClientDTO> clients = clientUtils.getClientsList();
        String content = clientUtils.convertClientListToJson(clients);
        String json = mapMessage(request, content);
        sendMessage(communicationHandler, json);
        return "Lista de clientes enviada al cliente " + communicationHandler.getClientSocket().getInetAddress().getHostAddress();
    }

    @Override
    public void sendReject(ICommunicationHandler communicationHandler, String msg) {
        String request = "_REJECT_";
        String content = msg;
        String json = mapMessage(request, content);
        sendMessage(communicationHandler, json);
    }

    @Override
    public String sendDocument(ICommunicationHandler communicationHandler, String jsonDocument,DocumentUtils documentUtils) {
        String request = "_SERVER_POST_DOCUMENT_";
        String content = documentUtils.loadDocument(jsonDocument);
        String json = mapMessage(request, content);
        sendMessage(communicationHandler, json);
        return "Documento enviado correctamente al cliente: " + communicationHandler.getClientSocket().getInetAddress().getHostAddress();
    }

    @Override
    public String receiveDocument(ICommunicationHandler communicationHandler, String jsonDocument,DocumentUtils documentUtils) {
        try {
            documentUtils.saveDocument(jsonDocument);
            String request = "_SERVER_POST_MESSAGE_";
            String content = "Documento recibido y almacenado en el servidor";
            String json = mapMessage(request, content);
            sendMessage(communicationHandler, json);
            return "Documento recibido del cliente: " + communicationHandler.getClientSocket().getInetAddress().getHostAddress();
        } catch (IOException ex) {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @Override
    public void sendMessage(ICommunicationHandler communicationHandler, String json) {
        communicationHandler.sendMessage(json);
    }

    private String mapMessage(String request, String content) {
        Gson gson = new Gson();
        return gson.toJson(new Message(request, content));
    }

}
