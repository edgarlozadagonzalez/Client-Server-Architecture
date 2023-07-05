package com.mycompany.filewebserviceclient;

import com.mycompany.entity.Document;
import com.mycompany.filewebservice.FileWeb;
import com.mycompany.filewebservice.IFileWebService;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class FileWebServiceClient {

    private static final Scanner leer = new Scanner(System.in);

    public static void main(String[] args) {
        menu();
    }

    public static List<String> obtenerListaDocumentos() {
        try {
            FileWeb service = new FileWeb();
            IFileWebService port = service.getFileWebServiceImplPort();
            return port.getListFiles();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public static Document descargarDocumento(String filePath) {
        try {
            FileWeb service = new FileWeb();
            IFileWebService port = service.getFileWebServiceImplPort();
            Document documento = new Document(filePath, port.getFile(filePath));
            return documento;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    public static void listarDocumentos() {
        System.out.println("|----------------------------------------|");
        System.out.println("|----------LISTA DE DOCUMENTOS-----------|");
        List<String> documentos = obtenerListaDocumentos();
        if (documentos.size() <= 0) {
            System.out.println("No hay documentos en el servidor.");
        } else {
            for (int i = 0; i < documentos.size(); i++) {
                String documento = documentos.get(i);
                int numeroDocumento = i + 1;
                System.out.println("| " + numeroDocumento + ". " + documento);
            }
        }
    }

    public static String obtenerDocumentoEnPosicion(List<String> documentos, int posicion) {
        if (posicion >= 0 && posicion < documentos.size()) {
            return documentos.get(posicion);
        } else {
            return null; // O puedes lanzar una excepción si lo consideras apropiado
        }
    }

    public static void guardarDocumentoConVentana(Document document) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar documento");

        String nombreArchivoPorDefecto = document.getName(); // Obtiene el nombre del documento

        // Establece el nombre del archivo por defecto
        File archivoPorDefecto = new File(nombreArchivoPorDefecto);
        fileChooser.setSelectedFile(archivoPorDefecto);

        int seleccion = fileChooser.showSaveDialog(null);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            String rutaArchivo = fileChooser.getSelectedFile().getAbsolutePath();
            try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
                fos.write(document.getContent());
                System.out.println("Documento guardado exitosamente en: " + rutaArchivo);
                abrirArchivo(rutaArchivo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (seleccion == JFileChooser.CANCEL_OPTION) {
            System.out.println("Operación de guardar documento cancelada por el usuario.");
        }
    }

    public static void abrirArchivo(String rutaArchivo) {
        File archivo = new File(rutaArchivo);
        if (Desktop.isDesktopSupported() && archivo.exists()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(archivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void menuDocumento() {
        System.out.println("|----------------------------------------|");
        System.out.print("|Ingrese el numero de documento: ");
        try {
            int opc = leer.nextInt();
            List<String> documentos = obtenerListaDocumentos();
            String documento = obtenerDocumentoEnPosicion(documentos, opc);
            if (documento != null) {
                Document document = descargarDocumento(documento);
                guardarDocumentoConVentana(document);
            } else {
                System.out.println("El numero de documento ingresado no es valido.");
            }

        } catch (InputMismatchException e) {
            e.getMessage();
            System.out.println("Debe ingresar como opcion un numero entero.");
        }
    }

    public static void menu() {
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
                    listarDocumentos();
                    menu();
                    break;
                case 2:
                    menuDocumento();
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

}
