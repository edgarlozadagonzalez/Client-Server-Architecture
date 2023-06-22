package com.mycompany.controller;

import com.mycompany.dto.ClientDTO;
import com.mycompany.dto.DocumentDTO;
import com.mycompany.server.Server;
import com.mycompany.service.DocumentService;
import com.mycompany.view.ServerConsole;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerController {

    private final Server server;
    private final DocumentService documentService;
    private static ServerController instance;

    private ServerController() {
        this.documentService = new DocumentService();
        this.server = new Server();
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public void initServer(int port) {
        documentService.getDocumentsInFolder();
        server.start(port);
    }

    public void addMessageToBuffer(String mensaje) {
        ServerConsole.mostrarMensaje(mensaje);
    }

    public void saveDocument(DocumentDTO documentDTO) {
        //documentService.saveDocument(documentDTO);
        ServerConsole.listarClientes(listClientDTO());
        ServerConsole.listarDocumentos((listDocumentDTO()));
        addMessageToBuffer("Documento recibido y almacenado en el servidor");
    }

    public void receiveDocumentsFromClient(String jsonDocument){
        try {
            documentService.saveDocument( jsonDocument);
        } catch (IOException ex) {
            System.out.println(ex);
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDocumentListJson() {
        return documentService.convertDocumentListToJson(listDocumentDTO());
    }
    
    public String getDocument(String content){ 
        return documentService.loadDocument(content);
    }

    public List<ClientDTO> listClientDTO() {
        return server.getListClientDTO();
    }

    public List<DocumentDTO> listDocumentDTO() {
        return documentService.getDocumentsList();
    }

}
