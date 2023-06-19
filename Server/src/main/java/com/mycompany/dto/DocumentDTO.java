package com.mycompany.dto;

public class DocumentDTO {

    private String name;
    private long size;
    private String extension;
    private String encodedFileContent;
    private String owner;

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setEncodedFileContent(String encodedFileContent) {
        this.encodedFileContent = encodedFileContent;
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

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
