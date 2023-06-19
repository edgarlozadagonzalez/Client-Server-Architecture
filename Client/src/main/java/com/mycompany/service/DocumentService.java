package com.mycompany.service;

import com.mycompany.model.Document;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import com.google.gson.Gson;

public class DocumentService {

    public Document loadDocument(File file, String ipClient) throws IOException {
        byte[] fileContent = readDocumentBytes(file.getPath());
        String encodedFileContent = encodeToBase64(fileContent);
        return new Document(file, encodedFileContent, ipClient);
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

    public String convertToJson(Document document) {
        Gson gson = new Gson();
        return gson.toJson(document);
    }
}
