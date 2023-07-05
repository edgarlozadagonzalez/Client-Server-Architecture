package com.mycompany.FileClient.controller;

import com.mycompany.FileClient.dto.DocumentDTO;
import java.util.List;

public interface CommunicationListener {
    void onDocumentsReceived(List<DocumentDTO> documentList);
    void onMessageReceived(String message);
    void onDocumentReceived(DocumentDTO documentDTO);
}
