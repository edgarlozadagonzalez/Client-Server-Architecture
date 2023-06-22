package com.mycompany.model;

import java.io.File;

public class Document {

    private final String name;
    private final long size;
    private final String extension;
    private final String owner;
    private final File file;

    public Document(File file) {
        String fileName = file.getName();
        int lastUnderscoreIndex = fileName.lastIndexOf("_");
        int extensionIndex = fileName.lastIndexOf(".");
        
        this.name = fileName.substring(0, lastUnderscoreIndex);
        this.owner = fileName.substring(lastUnderscoreIndex + 1, extensionIndex);
        this.extension = fileName.substring(extensionIndex + 1);
        this.size = file.length();
        this.file = file;
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

    public String getOwner() {
        return owner;
    }

    public File getFile() {
        return file;
    }
}
