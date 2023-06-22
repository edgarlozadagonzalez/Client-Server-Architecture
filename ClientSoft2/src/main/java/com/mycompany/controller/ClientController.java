package com.mycompany.controller;

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
    private final List<CommunicationListener> subscribers;
    private final List<ConnectionListener> listeners;

    private ClientController() {
        this.client = new Client();
        this.documentService = new DocumentService();
        this.documentList = new ArrayList<>();
        this.subscribers = new ArrayList<>();
        this.listeners = new ArrayList<>();

    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public void addSubscriber(CommunicationListener subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(CommunicationListener subscriber) {
        subscribers.remove(subscriber);
    }

    public void addListener(ConnectionListener listener) {
        listeners.add(listener);
    }
    
    public void removeListener(ConnectionListener listener) {
        listeners.remove(listener);
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

    public void setAvailableDocuments(String jsonDocumentList) {
        documentList = documentService.convertJsonToDocumentList(jsonDocumentList);
        notifySubscribersDocumentListReceived();
    }

    public void setDownloadDocument(String jsonDocument) {
        DocumentDTO documentDTO = documentService.parseJsonToDocumentDTO(jsonDocument);
        notifySubscribersDocumentReceived(documentDTO);
    }

    private void notifySubscribersDocumentReceived(DocumentDTO docmuentDTO) {
        for (CommunicationListener subscriber : subscribers) {
            subscriber.onDocumentReceived(docmuentDTO);
        }
    }

    private void notifySubscribersDocumentListReceived() {
        for (CommunicationListener subscriber : subscribers) {
            subscriber.onDocumentsReceived(documentList);
        }
    }

    public void setMessage(String message) {
        notifySubscribersMessageReceived(message);
    }

    public void setConnectionAdmitted(String message) {
         notifyListenersConnectionAdmitted(message);
    }
    
    public void setConnectionReject(String message) {
         notifyListenersConnectionReject(message);
    }
    
    private void notifyListenersConnectionAdmitted(String message) {
        for (ConnectionListener listener : listeners) {
            listener.onConnectionAdmitted(message);
        }
    }
    
    private void notifyListenersConnectionReject(String message) {
        for (ConnectionListener listener : listeners) {
            listener.onConnectionReject(message);
        }
    }
    private void notifySubscribersMessageReceived(String message) {
        for (CommunicationListener subscriber : subscribers) {
            subscriber.onMessageReceived(message);
        }
    }
}
