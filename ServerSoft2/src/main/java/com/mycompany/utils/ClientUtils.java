package com.mycompany.utils;

import com.google.gson.Gson;
import com.mycompany.dto.ClientDTO;
import java.util.ArrayList;
import java.util.List;

public class ClientUtils {

    private final List<ClientDTO> clientsDTO;

    public ClientUtils() {
        clientsDTO = new ArrayList<>();
    }

    public void addClient(ClientDTO client) {
        clientsDTO.add(client);
    }

    public List<ClientDTO> getClientsList() {
        return clientsDTO;
    }

    public String convertClientListToJson(List<ClientDTO> clientList) {
        Gson gson = new Gson();
        return gson.toJson(clientList);
    }
}
