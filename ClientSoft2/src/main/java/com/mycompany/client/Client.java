package com.mycompany.client;

import com.google.gson.Gson;
import com.mycompany.dto.Message;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends MessageProcessor implements Runnable {

    private CommunicationHandler communicationHandler;
    private Socket serverSocket;


    public void connectToServer(int port, String host) throws IOException {
        try {
            serverSocket = new Socket(host, port);
            communicationHandler = new CommunicationHandler(serverSocket);
            communicationHandler.createStreams();
            System.out.println("Server address: " + serverSocket.getInetAddress().getHostAddress());
        } catch (IOException ex) {
            //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            throw new IOException("Error al conectar con el servidor. Verifique que la dirección IP y el puerto correspondan al servidor.", ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            String message = communicationHandler.receiveMessage();
            if (message == null) {
                break;
            }
           processMessage(message);
        }
    }

    public void sendRequest(String request, String content) {
        Gson gson = new Gson();
        String json = gson.toJson(new Message(request, content));
        processMessage(json);
    }

    public String getIpClient() {
        return serverSocket.getLocalAddress().getHostAddress();
    }
    
    public void disconnect(){
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @Override
    protected void sendServerMessage(Message message) {
         Gson gson = new Gson();
        communicationHandler.sendMessage(gson.toJson(message));
    }

}
