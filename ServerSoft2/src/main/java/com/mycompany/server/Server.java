package com.mycompany.server;

import com.mycompany.interfaces.IServer;
import com.mycompany.interfaces.IClientHandler;
import com.mycompany.controller.ServerController;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.interfaces.ICommunicationHandler;
import java.util.ArrayList;
import java.util.List;

public class Server implements IServer {

    private ServerSocket serverSocket;
    private ICommunicationHandler communicationHandler;
    private Socket clientSocket;
    private final ClientPool clientPool;
    private ServerController serverController;

    public Server() {
        this.clientPool = ClientPool.getInstance();
    }

    @Override
    public void start(int port, int maxclients) {
        clientPool.createPool(maxclients);
        try {
            serverSocket = new ServerSocket(port);
            serverController.addMessageToBuffer("Servidor iniciado en el puerto " + port);
            serverController.addMessageToBuffer("Servidor en espera de clientes...");
            waitForClients();
        } catch (IOException ex) {
            serverController.addMessageToBuffer("Error al iniciar el servidor: " + ex.getMessage());
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void waitForClients() {
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                communicationHandler = new ClientCommunicationHandler();
                communicationHandler.setClientSocket(clientSocket);
                if (clientPool.hasAvailableClients()) {
                    serverController.addMessageToBuffer("El cliente " + clientSocket.getInetAddress().getHostAddress() + " se ha conectado.");
                    handleClient();
                } else {
                    serverController.addMessageToBuffer("El servidor está lleno. Rechazando la conexión del cliente " + clientSocket.getInetAddress().getHostAddress());
                    // Enviar mensaje al cliente indicando que el servidor está lleno
                    communicationHandler.createStreams();
                    serverController.sendReject(communicationHandler, "El servidor está lleno. Intente más tarde.");
                    clientSocket.close();
                }
            } catch (IOException ex) {
                serverController.addMessageToBuffer( ex.getMessage());
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void handleClient() {
        // Obtiene un cliente del pool
        IClientHandler handler = clientPool.getClient();
        handler.connect(communicationHandler, serverController);
        // Crea un nuevo hilo utilizando el cliente obtenido
        Thread thread = new Thread(handler);
        thread.start();
        int threadCount = Thread.activeCount();
        serverController.addMessageToBuffer("Cantidad de hilos: " + threadCount);
    }

    @Override
    public void closeServer() {
        serverController.addMessageToBuffer("Cerrando las conexiones de los clientes...");
        List<IClientHandler> takenClients = new ArrayList<>(clientPool.getTakenClients());
        for (IClientHandler clientHandler : takenClients) {
            clientHandler.disconnect();
        }
        try {
            serverController.addMessageToBuffer("Cerrando el servidor...");
            serverSocket.close();
        } catch (IOException e) {
            serverController.addMessageToBuffer("Error al cerrar el servidor");
        }
    }

    @Override
    public ServerController getServerController() {
        return serverController;
    }

    @Override
    public void setServerController(Object serverController) {
        this.serverController = (ServerController) serverController;
    }
}
