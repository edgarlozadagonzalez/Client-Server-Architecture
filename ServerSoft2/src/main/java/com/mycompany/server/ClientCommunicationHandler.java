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
import com.mycompany.interfaces.ICommunicationHandler;

public class ClientCommunicationHandler implements ICommunicationHandler {

    private BufferedReader clientReader;
    private BufferedWriter clientWriter;
    private Socket clientSocket;

    @Override
    public void createStreams() {
        try {
            InputStream is = clientSocket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            clientReader = new BufferedReader(isr);

            OutputStream os = clientSocket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            clientWriter = new BufferedWriter(osw);
        } catch (IOException ex) {
            Logger.getLogger(ClientCommunicationHandler.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Error al crear los flujos con el cliente.", ex);
        }
    }

    @Override
    public void sendMessage(String message) {
        try {
            clientWriter.write(message);
            clientWriter.newLine();
            clientWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String receiveMessage() {
        try {
            return clientReader.readLine();
        }catch (SocketException ex) {
        } catch (IOException ex) {
            Logger.getLogger(ClientCommunicationHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Socket getClientSocket() {
        return clientSocket;
    }

    @Override
    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
}
