package com.mycompany.model;

import java.time.LocalDateTime;

public class Client {

    private final String name;
    private final String ipAddress;
    private final LocalDateTime dateTime;

    public Client(String name, String ipAddress, LocalDateTime dateTime) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
}