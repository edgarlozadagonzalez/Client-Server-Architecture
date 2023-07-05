package com.mycompany.server;

import com.mycompany.interfaces.IClientHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientPool {

    private static ClientPool instance;
    private final List<IClientHandler> availableClients;

    private ClientPool() {
        availableClients = new ArrayList<>();
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
        if (!hasAvailableClients()) {
            // Si no hay clientes disponibles en el pool, esperamos hasta que haya uno disponible
            try {
                wait();
            } catch (InterruptedException e) {
                 Logger.getLogger(ClientPool.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        // Obtén el primer cliente disponible del pool y remuévelo de la lista
        return availableClients.remove(0);
    }

    public synchronized void releaseClient(IClientHandler client) {
        // Agrega el cliente devuelto al pool y notifica a los hilos en espera
        availableClients.add(client);
        notify();
    }

    public List<IClientHandler> getAvailableClients() {
        return availableClients;
    }
    
    
}
