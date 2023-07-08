package com.mycompany.server;

import com.google.gson.Gson;
import com.mycompany.controller.ServerController;
import com.mycompany.interfaces.IClientHandler;
import com.mycompany.dto.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mycompany.interfaces.ICommunicationHandler;

public class ClientHandler implements IClientHandler {

    private MessageProcessor messageProcessor;
    private ICommunicationHandler communicationHandler;
    private boolean online;

    @Override
    public void connect(ICommunicationHandler comnHandler, Object serverController) {
        this.communicationHandler = comnHandler;
        online = true;
        communicationHandler.createStreams();

        messageProcessor = new MessageProcessor(communicationHandler, (ServerController) serverController);

        Gson gson = new Gson();
        String json = gson.toJson(new Message("_ADMITTED_", "¡Has sido admitido en nuestro servidor! ¡Bienvenido!"));
        messageProcessor.processMessage(json);
        json = gson.toJson(new Message("_SERVER_POST_MESSAGE_", "Bienvenido al servidor: " + communicationHandler.getClientSocket().getInetAddress().getHostName()));
        messageProcessor.processMessage(json);
    }

    @Override
    public void disconnect() {
        online = false;
        Gson gson = new Gson();
        String json = gson.toJson(new Message("_DISCONNECT_", "El cliente " + communicationHandler.getClientSocket().getInetAddress().getHostAddress() + " se ha desconectado."));
        try {
            ClientPool.getInstance().releaseClient(this);
            communicationHandler.getClientSocket().close();
            messageProcessor.processMessage(json);
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (online == true) {
            String message = communicationHandler.receiveMessage();
            if (message == null) {
                disconnect();
                break;
            }
            messageProcessor.processMessage(message);
        }
    }
}
