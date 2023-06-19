package com.mycompany.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientCommunicationHandler {

    private BufferedReader clientReader;
    private BufferedWriter clientWriter;
    private Socket clientSocket;

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void createStreams() {
        try {
            InputStream is = clientSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            clientReader = new BufferedReader(isr);

            OutputStream os = clientSocket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            clientWriter = new BufferedWriter(osw);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error al crear los flujos de cliente.", ex);
        }
    }

    public void sendMessage(String message) {
        try {
            clientWriter.write(message);
            clientWriter.newLine();
            clientWriter.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String receiveMessage() {
        try {
            return clientReader.readLine();
        } catch (SocketException ex) {
            System.out.println("El cliente " + clientSocket.getInetAddress().getHostAddress() + " se ha desconectado.");
        } catch (IOException ex) {
            System.out.println("Error de E/S: " + ex.getMessage());
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
