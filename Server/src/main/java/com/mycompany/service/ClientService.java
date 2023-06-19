package com.mycompany.service;

import com.mycompany.dto.ClientDTO;
import com.mycompany.model.Client;
import java.time.LocalDateTime;
import java.util.List;

public class ClientService {

    public Client searchClient(List<ClientDTO> listClient, String ipClient) {
        for (ClientDTO clientDTO : listClient) {
            if (clientDTO.getIpAddress().equals(ipClient)) {
                return convertToClient(clientDTO);
            }
        }
        return null;
    }

    private Client convertToClient(ClientDTO clientDTO) {
        String name = clientDTO.getName();
        String ipAddress = clientDTO.getIpAddress();
        LocalDateTime dateTime = clientDTO.getDateTime();
        return new Client(name,ipAddress,dateTime);  
    }
}
