package com.mycompany.interfaces;
import com.mycompany.dto.ClientDTO;
import com.mycompany.dto.DocumentDTO;
import java.util.List;

public interface IView {

    void start();

    void listClients(List<ClientDTO> listclientsDTO);

    void listDocuments(List<DocumentDTO> listDocumentDTO);

    void displayMessage(String mensaje);
    
    void setServerController(Object serverController);
}
