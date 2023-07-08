package com.mycompany.interfaces;

public interface ConnectionListener {

    void onConnectionAdmitted(String message);

    void onConnectionReject(String message);

}
