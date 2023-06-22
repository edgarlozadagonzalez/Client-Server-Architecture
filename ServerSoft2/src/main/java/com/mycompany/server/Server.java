package com.mycompany.server;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.dto.ClientDTO;
import com.mycompany.dto.Message;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private int PORT;
    private ServerSocket serverSocket;
    private final List<ClientDTO> listClientDTO;
    private ClientCommunicationHandler communicationHandler;
    private Socket clientSocket;

    public Server() {
        this.listClientDTO = new ArrayList<>();
    }

    public void start(int port) {
        try {
            this.PORT = port;
            serverSocket = new ServerSocket(PORT);
            System.out.println("Servidor iniciado en el puerto " + PORT);
            System.out.println("Servidor en espera de clientes...");
            waitForClients();
        } catch (IOException ex) {
            System.out.println("Error al iniciar el servidor: " + ex.getMessage());
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void waitForClients() {
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                communicationHandler = new ClientCommunicationHandler(clientSocket);
                if (ClientPool.getInstance().hasAvailableClients()) {
                    System.out.println("El cliente " + clientSocket.getInetAddress().getHostAddress() + " se ha conectado.");
                    handleClient();
                } else {
                    System.out.println("El servidor está lleno. Rechazando la conexión del cliente " + clientSocket.getInetAddress().getHostAddress());
                    // Enviar mensaje al cliente indicando que el servidor está lleno
                    communicationHandler.createStreams();
                    Message message = new Message("_REJECT_","El servidor está lleno. Intente más tarde.");
                    Gson json = new Gson();
                    communicationHandler.sendMessage(json.toJson(message));
                    clientSocket.close();
                }
            } catch (IOException ex) {
                System.out.println("Error al aceptar el cliente: " + ex.getMessage());
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void handleClient(){
        // Obtiene un cliente del pool
        IClientHandler handler = ClientPool.getInstance().getClient();
        handler.setCommunicationHandler(communicationHandler);
        // Crea un nuevo hilo utilizando el cliente obtenido
        Thread thread = new Thread(handler);
        thread.start();
        listClientDTO.add(new ClientDTO(clientSocket.getInetAddress().getHostName(), clientSocket.getInetAddress().getHostAddress(), LocalDateTime.now()));
        int threadCount = Thread.activeCount();
        System.out.println("Cantidad de hilos: " + threadCount);
    }

    public int getPORT() {
        return PORT;
    }

    public List<ClientDTO> getListClientDTO() {
        return listClientDTO;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

}
