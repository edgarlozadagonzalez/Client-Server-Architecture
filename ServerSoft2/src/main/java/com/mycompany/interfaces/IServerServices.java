package com.mycompany.interfaces;

import com.mycompany.utils.ClientUtils;
import com.mycompany.utils.DocumentUtils;

public interface IServerServices {

    String sendDocumentList(ICommunicationHandler communicationHandler,DocumentUtils documentUtils);

    String sendClientList(ICommunicationHandler communicationHandler,ClientUtils clientUtils);

    void sendReject(ICommunicationHandler communicationHandler, String error);

    String sendDocument(ICommunicationHandler communicationHandler, String jsonDocument,DocumentUtils documentUtils);

    String receiveDocument(ICommunicationHandler communicationHandler, String jsonDocument,DocumentUtils documentUtils);

    void sendMessage(ICommunicationHandler communicationHandler, String json);
}
