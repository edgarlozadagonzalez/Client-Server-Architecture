package com.mycompany.FileClient.service;

import com.mycompany.FileClient.model.Document;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.FileClient.dto.DocumentDTO;
import java.lang.reflect.Type;
import java.util.List;

public class DocumentService {

    public DocumentDTO loadDocument(File file, String ipClient) throws IOException {
        byte[] fileContent = readDocumentBytes(file.getPath());
        String encodedFileContent = encodeToBase64(fileContent);
        Document document = new Document(file, encodedFileContent, ipClient);
        DocumentDTO documentDTO = new DocumentDTO();
        documentDTO.setName(document.getName());
        documentDTO.setSize(document.getSize());
        documentDTO.setExtension(document.getExtension());
        documentDTO.setEncodedFileContent(document.getEncodedFileContent());
        documentDTO.setOwner(document.getOwner());
        return documentDTO;
    }

    public List<DocumentDTO> convertJsonToDocumentList(String json) {
        Gson gson = new Gson();
        Type documentListType = new TypeToken<List<DocumentDTO>>() {
        }.getType();
        List<DocumentDTO> documentList = gson.fromJson(json, documentListType);
        return documentList;
    }
    
    
    public byte[] readDocumentBytes(String path) throws IOException {
        try {
            Path filePath = Paths.get(path);
            byte[] fileContent = Files.readAllBytes(filePath);
            return fileContent;
        } catch (IOException e) {
            throw new IOException("Error al leer el documento", e);
        }
    }

    public String encodeToBase64(byte[] fileContent) {
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public String convertToJson(DocumentDTO documentDTO) {
        Gson gson = new Gson();
        return gson.toJson(documentDTO);
    }
    
     public DocumentDTO parseJsonToDocumentDTO(String json) {
        Gson gson = new Gson();
        DocumentDTO documentDTO = gson.fromJson(json, DocumentDTO.class);
        System.out.println("Nombre del archivo: " + documentDTO.getName());
        System.out.println("Tamaño del archivo: " + documentDTO.getSize());
        System.out.println("Extensión del archivo: " + documentDTO.getExtension());
        System.out.println("Dueño: " + documentDTO.getOwner());
        return documentDTO;
    }

}
