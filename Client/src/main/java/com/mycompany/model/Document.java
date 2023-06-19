package com.mycompany.model;

import java.io.File;

public class Document {

    private final String name;
    private final long size;
    private final String extension;
    private final String encodedFileContent;
    private final String owner;

    public Document(File file, String encodedFileContent, String ipClient) {
        this.name = getFileNameWithoutExtension(file.getName());
        this.size = file.length();
        this.extension = getFileExtension(file.getName());
        this.encodedFileContent = encodedFileContent;
        this.owner = ipClient;
    }

    private String getFileExtension(String fileName) {
        if (fileName != null && !fileName.isEmpty()) {
            int dotIndex = fileName.lastIndexOf(".");
            if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
                return fileName.substring(dotIndex + 1);
            }
        }
        throw new IllegalArgumentException("Nombre de archivo inválido: " + fileName);
    }

    private String getFileNameWithoutExtension(String fileName) {
        if (fileName != null && !fileName.isEmpty()) {
            int dotIndex = fileName.lastIndexOf(".");
            if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
                return fileName.substring(0, dotIndex);
            }
        }
        throw new IllegalArgumentException("Nombre de archivo inválido: " + fileName);
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public String getExtension() {
        return extension;
    }

    public String getEncodedFileContent() {
        return encodedFileContent;
    }

    public String getOwner() {
        return owner;
    }
    
    
}
