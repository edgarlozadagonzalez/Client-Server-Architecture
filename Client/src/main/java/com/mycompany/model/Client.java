package com.mycompany.model;

import com.mycompany.controller.ClientController;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client extends Thread {

    private final int port;
    private final String host;
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    
    
    public Client(int port, String host) {
        this.port = port;
        this.host = host;
    }
    
    public void connectToServer() {
        try {
            socket = new Socket(host, port);
            System.out.println("host: " + host);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void createStreams() {
        try {
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);
            System.out.println("creados streams");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage(String message) {
        try {
            bw.write(message);
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public String receiveMessage() {
        try {
            String message = br.readLine();
            return message;
        } catch (SocketException ex) {
            System.out.println("El servidor se ha cerrado.");
        } catch (IOException ex) {
            System.out.println("Error de E/S: " + ex.getMessage());
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void run() {
        while (true) {
            String message = receiveMessage();
             if (message == null) {
                break;
            }
            System.out.println("socket host:" + socket.getInetAddress().getHostAddress());
            ClientController.getInstance().addMessageToTransfer("The Server says: " + message);
        }
    }

    public void sendDocumentoToServer(String json) throws IOException {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println(json);
        } catch (IOException ex) {
            System.out.println(ex);
            throw new IOException("Error al enviar el documento", ex);
        }
    }

    public String getHost() {
        return host;
    }

}
