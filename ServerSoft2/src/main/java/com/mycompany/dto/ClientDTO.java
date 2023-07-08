package com.mycompany.dto;

import java.net.Socket;
import java.time.LocalDateTime;

public class ClientDTO {

    private final String name;
    private final String ipAddress;
    private final LocalDateTime dateTime;
    
    public ClientDTO(Socket clientSocket) {
        this.name = clientSocket.getInetAddress().getHostName();
        this.ipAddress = clientSocket.getInetAddress().getHostAddress();
        this.dateTime =  LocalDateTime.now();
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
