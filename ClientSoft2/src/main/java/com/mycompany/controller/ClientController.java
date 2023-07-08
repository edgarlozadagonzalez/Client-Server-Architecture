package com.mycompany.controller;

import com.mycompany.interfaces.ConnectionListener;
import com.mycompany.interfaces.CommunicationListener;
import com.mycompany.dto.DocumentDTO;
import com.mycompany.client.Client;

import java.io.File;
import com.mycompany.service.DocumentService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientController {

    private final Client client;
    private final DocumentService documentService;
    private static ClientController instance;
    private List<DocumentDTO> documentList;

    private CommunicationListener comunicationListener;
    private ConnectionListener connectionListener;

    private ClientController() {
        this.client = new Client();
        this.documentService = new DocumentService();
        this.documentList = new ArrayList<>();
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public void addCommunicationListener(CommunicationListener subscriber) {
        this.comunicationListener = subscriber;
    }

    public void addConnectionListener(ConnectionListener subscriber) {
        this.connectionListener = subscriber;
    }

    public void connectToServer(int port, String host) throws IOException {
        client.connectToServer(port, host);
    }

    public void initClient() {
        Thread th = new Thread(client);
        th.start();
    }

    public void sendDocumentToServer(File file) throws IOException {
        String json = documentService.convertToJson(documentService.loadDocument(file, client.getIpClient()));
        client.sendRequest("_CLIENT_POST_DOCUMENT_", json);
    }

    public void requestDocumentList() throws IOException {
        client.sendRequest("_CLIENT_GET_DOCUMENT_LIST_", "");
    }

    public void requestDownloadDocument(DocumentDTO documentDTO) {
        String jsonDocument = documentService.convertToJson(documentDTO);
        client.sendRequest("_CLIENT_GET_DOCUMENT_", jsonDocument);
    }

    public void disconnet(DocumentDTO documentDTO) {
        client.sendRequest("_DISCONNECT_","");
    }

    public void setAvailableDocuments(String jsonDocumentList) {
        documentList = documentService.convertJsonToDocumentList(jsonDocumentList);
        notifyDocumentListReceived();
    }

    public void setDownloadDocument(String jsonDocument) {
        DocumentDTO documentDTO = documentService.parseJsonToDocumentDTO(jsonDocument);
        notifyDocumentReceived(documentDTO);
    }

    private void notifyDocumentReceived(DocumentDTO docmuentDTO) {
        comunicationListener.onDocumentReceived(docmuentDTO);
    }

    private void notifyDocumentListReceived() {
        comunicationListener.onDocumentsReceived(documentList);
    }

    public void setMessage(String message) {
        notifyMessageReceived(message);
    }

    public void setConnectionAdmitted(String message) {
        notifyConnectionAdmitted(message);
    }

    public void setConnectionReject(String message) {
        notifyConnectionReject(message);
    }

    private void notifyConnectionAdmitted(String message) {
        connectionListener.onConnectionAdmitted(message);
    }

    private void notifyConnectionReject(String message) {
        connectionListener.onConnectionReject(message);

    }

    private void notifyMessageReceived(String message) {
        comunicationListener.onMessageReceived(message);
    }
}
