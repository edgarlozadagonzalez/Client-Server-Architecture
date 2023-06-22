package com.mycompany.server;

import java.util.ArrayList;
import java.util.List;

public class ClientPool {

    private static ClientPool instance;
    private static final int MAX_CLIENTS = 1; // Define el número máximo de clientes permitidos
    private final List<IClientHandler> availableClients;

    private ClientPool() {
        availableClients = new ArrayList<>();
        for (int i = 0; i < MAX_CLIENTS; i++) {
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
                System.out.println(e);
            }
        }
        // Obtén el primer cliente disponible del pool y remuévelo de la lista
        IClientHandler client = availableClients.remove(0);
        return client;
    }

    public synchronized void releaseClient(IClientHandler client) {
        // Agrega el cliente devuelto al pool y notifica a los hilos en espera
        availableClients.add(client);
        notify();
    }
}
