package com.mycompany.service;

import com.google.gson.Gson;
import com.mycompany.dto.DocumentDTO;
import java.util.ArrayList;

import java.util.Base64;
import java.util.List;

public class DocumentService {
    List<DocumentDTO> listDocumentDTO;

    public DocumentService() {
        this.listDocumentDTO = new ArrayList<>();
    }
    
    public DocumentDTO parseJsonToDocumentDTO(String json) {
        Gson gson = new Gson();
        DocumentDTO documentDTO = gson.fromJson(json, DocumentDTO.class);
        System.out.println("Nombre del archivo: " + documentDTO.getName());
        System.out.println("Tamaño del archivo: " + documentDTO.getSize());
        System.out.println("Extensión del archivo: " + documentDTO.getExtension());
        System.out.println("Dueño: " + documentDTO.getOwner());
        listDocumentDTO.add(documentDTO);
        return documentDTO;
    }

    public List<DocumentDTO> getListDocumentDTO() {
        return listDocumentDTO;
    }
      
    public byte[] decodeToBase64(String encodedFileContent) {
        return Base64.getDecoder().decode(encodedFileContent);
    }

//    public void saveDocument(DocumentDTO documentDTO) throws IOException {
//        byte[] decodedContent = decodeToBase64(documentDTO.getEncodedFileContent());
//        String folderPath = "Documents"; // Ruta de la carpeta "Documents" relativa al directorio actual
//        File folder = new File(folderPath);
//
//        if (!folder.exists()) {
//            folder.mkdirs();
//        }
//
//        File file = new File(folderPath + File.separator + documentDTO.getName() + "." + documentDTO.getExtension());
//
//        try (FileOutputStream fos = new FileOutputStream(file)) {
//            fos.write(decodedContent);
//        }
//    }
}
