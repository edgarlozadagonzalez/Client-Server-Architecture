package com.mycompany.controller;

import com.mycompany.model.Client;
import java.io.File;
import com.mycompany.service.DocumentService;
import com.mycompany.view.ClientConsole;
import java.io.IOException;

public class ClientController {

    private Client client;
    private final DocumentService documentService;
    private static ClientController clientController;

    private ClientController() {
        this.documentService = new DocumentService();
    }

    public static ClientController getInstance() {
        if (clientController == null) {
            clientController = new ClientController();
        }
        return clientController;
    }

    public void initClient(int port, String host) {
        client = new Client(port, host);
        ClientConsole.mostrarMensaje("conectando con el Servidor...");
        client.connectToServer();
        client.createStreams();
        client.start();
    }

    public void addMessageToTransfer(String mensaje) {
        ClientConsole.mostrarMensaje(mensaje);
    }

    public void sendDocumentToServer(File file) throws IOException {
        String json = documentService.convertToJson(documentService.loadDocument(file, client.getHost()));
        client.sendDocumentoToServer(json);
    }
}
