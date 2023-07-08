package com.mycompany.server;

import com.mycompany.interfaces.IClientHandler;
import java.util.ArrayList;
import java.util.List;

public class ClientPool {

    private static ClientPool instance;
    private final List<IClientHandler> availableClients;
    private final List<IClientHandler> takenClients;

    private ClientPool() {
        availableClients = new ArrayList<>();
        takenClients = new ArrayList<>();

    }

    public void createPool(int maxClients) {
        for (int i = 0; i < maxClients; i++) {
            availableClients.add(createClient());
        }
    }

    public static synchronized ClientPool getInstance() {
        if (instance == null) {
            instance = new ClientPool();
        }
        return instance;
    }

    private IClientHandler createClient() {
        return new ClientHandler();
    }

    public synchronized boolean hasAvailableClients() {
        return !availableClients.isEmpty();
    }

    public synchronized IClientHandler getClient() {
        // Obtén el primer cliente disponible del pool y remuévelo de la lista
        IClientHandler client = availableClients.remove(0);
        takenClients.add(client);
        return client;
    }

    public synchronized void releaseClient(IClientHandler client) {
        // Agrega el cliente devuelto al pool y notifica a los hilos en espera
        takenClients.remove(client);
        availableClients.add(client);
    }

    public List<IClientHandler> getTakenClients() {
        return takenClients;
    }

}
