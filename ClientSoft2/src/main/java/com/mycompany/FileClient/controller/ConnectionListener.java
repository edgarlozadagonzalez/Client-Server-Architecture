package com.mycompany.FileClient.controller;

public interface ConnectionListener {

    void onConnectionAdmitted(String message);

    void onConnectionReject(String message);

}
