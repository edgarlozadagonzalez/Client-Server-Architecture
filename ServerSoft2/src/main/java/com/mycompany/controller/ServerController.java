package com.mycompany.controller;

import com.mycompany.dto.ClientDTO;
import com.mycompany.dto.DocumentDTO;
import com.mycompany.interfaces.IServer;
import com.mycompany.interfaces.IView;
import com.mycompany.interfaces.IServerServices;
import com.mycompany.interfaces.ICommunicationHandler;
import com.mycompany.utils.ClientUtils;
import com.mycompany.utils.DocumentUtils;
import java.util.List;


public class ServerController {

    private final IServerServices serverServices;
    private final IView view;
    private final IServer server;
    private final ClientUtils clientUtils;
    private final DocumentUtils documentUtils;
    
    public ServerController(IServer server, IView view, IServerServices serverServices) {
        this.server = server;
        this.view = view;
        this.serverServices = serverServices;
        this.clientUtils = new ClientUtils();
        this.documentUtils = new DocumentUtils();
    }

    public void startServer(int port, int maxClients) {
        getDocumentsFolder();
        server.start(port, maxClients);
    }

    public void addMessageToBuffer(String message) {
        view.displayMessage(message);
    }

    public void viewRefreshDocumentsList() {
        view.listDocuments(getDocumentsList());
    }
    
    public void viewRefreshClientList(){
        view.listClients(getClientsList());
    }

    public void receiveDocument(ICommunicationHandler communicationHandler, String jsonDocument) {
        String message = serverServices.receiveDocument(communicationHandler, jsonDocument,documentUtils);
        addMessageToBuffer(message);
        viewRefreshDocumentsList();
    }

    public void sendDocumentList(ICommunicationHandler communicationHandler) {
        String message = serverServices.sendDocumentList(communicationHandler,documentUtils);
        addMessageToBuffer(message);
    }

    public void sendDocument(ICommunicationHandler communicationHandler, String content) {
        String message = serverServices.sendDocument(communicationHandler, content,documentUtils);
        addMessageToBuffer(message);
    }

    public void sendMessage(ICommunicationHandler communicationHandler, String json) {
        serverServices.sendMessage(communicationHandler, json);
    }

    public void sendReject(ICommunicationHandler communicationHandler, String msg) {
        serverServices.sendReject(communicationHandler, msg);
    }

    private void getDocumentsFolder() {
        documentUtils.getDocumentsInFolder();
    }
    
    private List<DocumentDTO> getDocumentsList(){
        return documentUtils.getDocumentsList();
    }
    
    
    public void addClientList(ClientDTO client){
        clientUtils.addClient(client);
        viewRefreshClientList();
    }
    
    public List<ClientDTO> getClientsList(){
        return clientUtils.getClientsList();
    }
    
    public void closeServer() {
        server.closeServer();
    }
}
