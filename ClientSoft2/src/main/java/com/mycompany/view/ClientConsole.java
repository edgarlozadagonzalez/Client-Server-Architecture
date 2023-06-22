package com.mycompany.view;


public class ClientConsole {

    public static void main(String[] args) {
//        try {
//            ClientController clientController = ClientController.getInstance();
//            clientController.initClient(8080, "localhost");
//            File file2 = new File("D:\\scbee\\Descargas\\python-3.11.3-amd64.exe");
//            clientController.sendDocumentToServer(file2);
//        } catch (IOException ex) {
//            Logger.getLogger(ClientConsole.class.getName()).log(Level.SEVERE, null, ex);
//        }
         new Start().setVisible(true);
    }

    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
