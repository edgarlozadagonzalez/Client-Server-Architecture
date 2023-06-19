package com.mycompany.controller;

import com.mycompany.dto.ClientDTO;
import com.mycompany.dto.DocumentDTO;
import com.mycompany.server.Server;
import com.mycompany.service.DocumentService;
import com.mycompany.view.ServerConsole;
import java.util.List;

public class ServerController {

    private final Server server;
    private final DocumentService documentService;
    private static ServerController instance;

    private ServerController() {
        this.documentService = new DocumentService();
        this.server = new Server();
    }

    public static ServerController getInstance(){
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public void initServer(int port) {
        server.start(port);
    }

    public void addMessageToBuffer(String mensaje) {
        ServerConsole.mostrarMensaje(mensaje);
    }

    public void saveDocument(DocumentDTO documentDTO){
        //documentService.saveDocument(documentDTO);
        ServerConsole.listarClientes(listClientDTO());
        ServerConsole.listarDocumentos((listDocumentDTO()));
        addMessageToBuffer("Documento recibido y almacenado en el servidor");
    }

    public void receiveDocumentsFromClient(String json) {
        DocumentDTO documentDTO = documentService.parseJsonToDocumentDTO(json);
        saveDocument(documentDTO);
    }
    
    public List<ClientDTO> listClientDTO(){
        return server.getListClientDTO();
    }
    
    public List<DocumentDTO> listDocumentDTO(){
        return documentService.getListDocumentDTO();
    }
}
