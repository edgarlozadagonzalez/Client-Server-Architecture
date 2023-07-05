package com.mycompany.utils;

import com.google.gson.Gson;
import com.mycompany.dto.DocumentDTO;
import com.mycompany.model.Document;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocumentUtils {

    private final List<DocumentDTO> documentsDTO;
    private static final String FOLDER_PATH = "../Documents";
    
    
    public DocumentUtils() {
        documentsDTO = new ArrayList<>();
    }

    public DocumentDTO parseJsonToDocumentDTO(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, DocumentDTO.class);
    }

    public String convertDocumentListToJson(List<DocumentDTO> documentList) {
        Gson gson = new Gson();
        return gson.toJson(documentList);
    }
    
    public byte[] decodeToBase64(String encodedFileContent) {
        return Base64.getDecoder().decode(encodedFileContent);
    }

    public void saveDocument(String jsonDocuemntDTO) throws IOException {
        DocumentDTO documentDTO = parseJsonToDocumentDTO(jsonDocuemntDTO);

        byte[] decodedContent = decodeToBase64(documentDTO.getEncodedFileContent());

        // Ruta de la carpeta "Documents" relativa al directorio actual
        File folder = new File(FOLDER_PATH);

        if (!folder.exists()) {
            folder.mkdirs();
        }

        String fileName = documentDTO.getName() + "_" + documentDTO.getOwner() + "." + documentDTO.getExtension();

        File file = new File(FOLDER_PATH + File.separator + fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(decodedContent);
        }
        //se agrega el documento a la lista de documentos.
        addDocument(documentDTO);
    }

    public void getDocumentsInFolder() {
        File folder = new File(FOLDER_PATH);

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            for (File file : files) {
                if (file.isFile()) {
                    Document document = new Document(file);
                    DocumentDTO documentDTO = new DocumentDTO();

                    documentDTO.setName(document.getName());
                    documentDTO.setSize(document.getSize());
                    documentDTO.setExtension(document.getExtension());
                    documentDTO.setOwner(document.getOwner());
                    addDocument(documentDTO);
                }
            }
        }
    }

    public void addDocument(DocumentDTO document) {
        document.setEncodedFileContent(null);
        documentsDTO.add(document);
    }

    public DocumentDTO findDocument(List<DocumentDTO> documentsDTO, DocumentDTO documentToFind) {
        for (DocumentDTO documentDTO : documentsDTO) {
            if (documentDTO.getName().equals(documentToFind.getName())
                    && documentDTO.getSize() == documentToFind.getSize()
                    && documentDTO.getExtension().equals(documentToFind.getExtension())
                    && documentDTO.getOwner().equals(documentToFind.getOwner())) {
                return documentDTO;
            }
        }
        return null; 
    }

    public String loadDocument(String jsonDocument) {
        DocumentDTO documentToFind = parseJsonToDocumentDTO(jsonDocument);
        DocumentDTO documentDTO = findDocument(documentsDTO, documentToFind);
        if (documentDTO != null) {
            try {
                String fileName = documentDTO.getName() + "_" + documentDTO.getOwner() + "." + documentDTO.getExtension();

                String filePath = FOLDER_PATH + File.separator + fileName;
                String encodedFileContent = encodeToBase64(readDocumentBytes(filePath));
                documentDTO.setEncodedFileContent(encodedFileContent);
                return convertToJson(documentDTO);
            } catch (IOException ex) {
                Logger.getLogger(DocumentUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public String encodeToBase64(byte[] fileContent) {
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public byte[] readDocumentBytes(String path) throws IOException {
        try {
            Path filePath = Paths.get(path);
            return Files.readAllBytes(filePath);
        } catch (IOException e) {
            throw new IOException("Error al leer el documento", e);
        }
    }

    public List<DocumentDTO> getDocumentsList() {
        return documentsDTO;
    }

    public String convertToJson(DocumentDTO documentDTO) {
        Gson gson = new Gson();
        return gson.toJson(documentDTO);
    }
}
