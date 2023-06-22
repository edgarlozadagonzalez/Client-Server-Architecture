package com.mycompany.controller;

import com.mycompany.dto.DocumentDTO;
import java.util.List;

public interface CommunicationListener {
    void onDocumentsReceived(List<DocumentDTO> documentList);
    void onMessageReceived(String message);
    void onDocumentReceived(DocumentDTO documentDTO);
}
