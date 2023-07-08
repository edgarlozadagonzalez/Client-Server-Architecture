package com.mycompany.view;

import com.mycompany.controller.Controller;
import com.mycompany.entity.Document;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class ClientConsole {

    private static final Scanner leer = new Scanner(System.in);
    private final Controller controller;

    public ClientConsole(Controller controller) {
        this.controller = controller;
    }

    public void menu() {
        System.out.println("|----------------------------------------|");
        System.out.println("|--------------MENU CLIENTE--------------|");
        System.out.println("|1. LISTAR DOCUMENTOS.                   |");
        System.out.println("|2. DESCARGAR DOCUMENTO.                 |");
        System.out.println("|3. SALIR.                               |");
        System.out.print("|Ingrese la opción (entre 1 y 3):        ");
        try {
            int opc = leer.nextInt();
            switch (opc) {
                case 1:
                    listDocuments();
                    menu();
                    break;
                case 2:
                    menuDocument();
                    menu();
                    break;
                case 3:
                    System.out.println("|-----------------------------------------|");
                    System.out.println("|-----------PROGRAMA FINALIZADO-----------|");
                    System.out.println("|-----------------------------------------|");
                    break;
                default:
                    System.out.println("La opcion no esta entre el rango");
                    menu();
                    break;
            }
        } catch (InputMismatchException e) {
            e.getMessage();
            System.out.println("Debe ingresar como opcion un numero entero.");
        }
    }

    public void menuDocument() {
        System.out.println("|----------------------------------------|");
        System.out.print("|Ingrese el numero de documento: ");
        try {
            int pos = leer.nextInt();
            List<String> documents = controller.getListFiles();
            String document = getSelectDocument(documents, pos - 1);
            if (document != null) {
                Document doc = controller.getFile(document);
                saveDocument(doc);
            } else {
                System.out.println("El numero de documento ingresado no es valido.");
            }

        } catch (InputMismatchException e) {
            e.getMessage();
            System.out.println("Debe ingresar como opcion un numero entero.");
        }
    }

    public void listDocuments() {
        System.out.println("|----------------------------------------|");
        System.out.println("|----------LISTA DE DOCUMENTOS-----------|");
        List<String> documents = controller.getListFiles();
        if (documents.size() <= 0) {
            System.out.println("No hay documentos en el servidor.");
        } else {
            for (int i = 0; i < documents.size(); i++) {
                String document = documents.get(i);
                int numDocument = i + 1;
                System.out.println("| " + numDocument + ". " + document);
            }
        }
    }

    public void saveDocument(Document document) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar documento");

        String nameFile = document.getName();

        File file = new File(nameFile);
        fileChooser.setSelectedFile(file);

        int userChoice = fileChooser.showSaveDialog(fileChooser);

        if (userChoice == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                fos.write(document.getContent());
                System.out.println("Documento guardado exitosamente en: " + filePath);
                openDocument(filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (userChoice == JFileChooser.CANCEL_OPTION) {
            System.out.println("Operación de guardar documento cancelada por el usuario.");
        }

    }

    public void openDocument(String filePath) {
        File document = new File(filePath);
        if (Desktop.isDesktopSupported() && document.exists()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(document);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String getSelectDocument(List<String> documents, int pos) {
        if (pos >= 0 && pos < documents.size()) {
            return documents.get(pos);
        } else {
            return null;
        }
    }
}
