package com.mycompany.dto;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("request")
    private String request;

    @SerializedName("content")
    private String content;

    // Constructor
    public Message(String request, String content) {
        this.request = request;
        this.content = content;
    }

    // Getters y setters
    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
