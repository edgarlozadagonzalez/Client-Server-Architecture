package com.mycompany.controller;

public interface ConnectionListener {

    void onConnectionAdmitted(String message);

    void onConnectionReject(String message);

}
