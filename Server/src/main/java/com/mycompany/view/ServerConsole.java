package com.mycompany.view;

import com.mycompany.controller.ServerController;
import com.mycompany.dto.ClientDTO;
import com.mycompany.dto.DocumentDTO;
import java.util.List;

public class ServerConsole {

    public static void main(String[] args) {

        int port = 8080;
        ServerController.getInstance().initServer(port);
    }


    public static void listarClientes(List<ClientDTO> listclientsDTO) {
        System.out.println("Listado de clientes conectados:");
        for (ClientDTO client : listclientsDTO) {
            System.out.println("Maquina: " + client.getName()
                    + " - Dirección IP: " + client.getIpAddress()
                    + " - Fecha: " + client.getDateTime().toLocalDate()
                    + " - Hora Inicio: " + client.getDateTime().toLocalTime());
        }
    }

    public static void listarDocumentos(List<DocumentDTO> listDocumentDTO) {
        System.out.println("Listado de documentos disponibles: ");
        for (DocumentDTO documenDTO : listDocumentDTO) {
            System.out.println("Nombre: " + documenDTO.getName()
                    + " - Tamaño: " + documenDTO.getSize()
                    + " - Extencion: " + documenDTO.getExtension()
                    + " - Propietario: " + documenDTO.getOwner());
        }
    }

    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
