package com.mycompany.view;

import com.mycompany.controller.ClientController;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientConsole {

    public static void main(String[] args) {
        try {
            ClientController clientController = ClientController.getInstance();
            clientController.initClient(8080,"172.28.32.1");
            File file = new File("D:\\scbee\\Descargas\\Documento.pdf");
            clientController.sendDocumentToServer(file);
        } catch (IOException ex) {
            Logger.getLogger(ClientConsole.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
