package com.mycompany.view;

import com.mycompany.interfaces.IView;
import com.mycompany.controller.ServerController;
import com.mycompany.dto.ClientDTO;
import com.mycompany.dto.DocumentDTO;
import java.util.List;
import java.util.Scanner;

public class ServerConsole implements IView {

    private final Scanner scanner = new Scanner(System.in);
    private int port;
    private int maxClients;
    private ServerController serverController;

    @Override
    public void start() {
        requestPort();
        requestMaxClients();
        serverController.startServer(port, maxClients);
    }

    public void requestPort() {
        boolean validPort = false;
        while (!validPort) {
            System.out.print("Ingrese el número de puerto: ");
            String portInput = scanner.nextLine();
            try {
                port = Integer.parseInt(portInput);
                validPort = true;
            } catch (NumberFormatException e) {
                System.err.println("Error: El número de puerto debe ser un valor entero válido.");
            }
        }
    }

    public void requestMaxClients() {
        boolean validMaxClients = false;
        while (!validMaxClients) {
            System.out.print("Ingrese la cantidad máxima de clientes: ");
            String maxClientsInput = scanner.nextLine();
            try {
                maxClients = Integer.parseInt(maxClientsInput);
                validMaxClients = true;
            } catch (NumberFormatException e) {
                System.err.println("Error: La cantidad máxima de clientes debe ser un valor entero válido.");
            }
        }
    }

    @Override
    public void listClients(List<ClientDTO> listclientsDTO) {
        System.out.println("Listado de clientes conectados:");
        for (ClientDTO client : listclientsDTO) {
            System.out.println("Maquina: " + client.getName()
                    + " - Dirección IP: " + client.getIpAddress()
                    + " - Fecha: " + client.getDateTime().toLocalDate()
                    + " - Hora Inicio: " + client.getDateTime().toLocalTime());
        }
    }

    @Override
    public void listDocuments(List<DocumentDTO> listDocumentDTO) {
        System.out.println("Listado de documentos disponibles: ");
        for (DocumentDTO documenDTO : listDocumentDTO) {
            System.out.println("Nombre: " + documenDTO.getName()
                    + " - Tamaño: " + documenDTO.getSize()
                    + " - Extencion: " + documenDTO.getExtension()
                    + " - Propietario: " + documenDTO.getOwner());
        }
    }

    @Override
    public void displayMessage(String mensaje) {
        System.out.println(mensaje);
    }

    @Override
    public void setServerController(Object serverController) {
        this.serverController = (ServerController) serverController;
    }

}
