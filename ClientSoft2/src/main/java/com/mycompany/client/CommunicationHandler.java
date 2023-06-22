package com.mycompany.client;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommunicationHandler {

    private BufferedReader serverReader;
    private BufferedWriter serverWriter;
    private final Socket serverSocket;

    public CommunicationHandler(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void createStreams() {
        try {
            InputStream is = serverSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            serverReader = new BufferedReader(isr);

            OutputStream os = serverSocket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            serverWriter = new BufferedWriter(osw);
        } catch (IOException ex) {
            Logger.getLogger(CommunicationHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error al crear los flujos de el cliente.", ex);
        }
    }

    public void sendMessage(String message) {
        try {
            serverWriter.write(message);
            serverWriter.newLine();
            serverWriter.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(CommunicationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String receiveMessage() {
        try {
            return serverReader.readLine();
        } catch (SocketException ex) {
            System.out.println("El servidor se ha cerrado.");
        } catch (IOException ex) {
            System.out.println("Error de E/S: " + ex.getMessage());
            Logger.getLogger(CommunicationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
