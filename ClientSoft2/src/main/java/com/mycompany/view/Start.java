package com.mycompany.view;

import com.mycompany.controller.ClientController;
import com.mycompany.interfaces.ConnectionListener;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Start extends javax.swing.JFrame implements ConnectionListener {

    private String message;
    private final ClientController clientController = ClientController.getInstance();

    public Start() {
        initComponents();
        this.setLocationRelativeTo(null);
        fieldAdressIP.setText("127.0.0.1");
        clientController.addConnectionListener(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        labelTitle1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        labelAdressIP = new javax.swing.JLabel();
        fieldPort = new javax.swing.JTextField();
        labelPort = new javax.swing.JLabel();
        fieldAdressIP = new javax.swing.JTextField();
        buttonConnect = new javax.swing.JButton();

        jPanel4.setBackground(new java.awt.Color(255, 102, 102));

        labelTitle1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 36)); // NOI18N
        labelTitle1.setText("CLIENTE LOGIN");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(labelTitle1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(labelTitle1)
                .addGap(31, 31, 31))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 102, 102));

        labelTitle.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 36)); // NOI18N
        labelTitle.setText("CLIENTE LOGIN");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(labelTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(labelTitle)
                .addGap(31, 31, 31))
        );

        labelAdressIP.setBackground(new java.awt.Color(0, 0, 0));
        labelAdressIP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelAdressIP.setText("Dirección IP:");

        fieldPort.setBackground(new java.awt.Color(204, 204, 204));
        fieldPort.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fieldPort.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelPort.setBackground(new java.awt.Color(0, 0, 0));
        labelPort.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelPort.setText("Puerto:");

        fieldAdressIP.setBackground(new java.awt.Color(204, 204, 204));
        fieldAdressIP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        fieldAdressIP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fieldAdressIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldAdressIPActionPerformed(evt);
            }
        });

        buttonConnect.setText("CONNECT");
        buttonConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConnectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(labelAdressIP))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addComponent(labelPort))
                            .addComponent(fieldAdressIP, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(177, 177, 177)
                        .addComponent(buttonConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addComponent(labelAdressIP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldAdressIP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(labelPort)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fieldPort, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(buttonConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConnectActionPerformed
        if (validateAdressIP()) {
            if (validatePort()) {
                connectToServer(Integer.parseInt(fieldPort.getText()), fieldAdressIP.getText());
            } else {
                JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buttonConnectActionPerformed

    private void fieldAdressIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldAdressIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fieldAdressIPActionPerformed

    public boolean validateAdressIP() {
        String ipAddress = fieldAdressIP.getText();
        String ipPattern = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        Pattern pattern = Pattern.compile(ipPattern);
        Matcher matcher = pattern.matcher(ipAddress);

        if (matcher.matches()) {
            return true;
        } else {
            message = "Dirección IP inválida.";
            return false;
        }
    }

    public boolean validatePort() {
        int port;
        try {
            port = Integer.parseInt(fieldPort.getText());
            if (port > 0 && port < 65535) {
                return true;
            } else {
                message = "Puerto inválido.";
                return false;
            }
        } catch (NumberFormatException e) {
            message = "No ha ingresado un número válido en el puerto.";
            return false;
        }
    }

    public void connectToServer(int port, String ip) {
        try {
            System.out.println("Conectando con el Servidor...");
            clientController.connectToServer(port, ip);
            clientController.initClient();
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
            JOptionPane.showMessageDialog(null, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonConnect;
    private javax.swing.JTextField fieldAdressIP;
    private javax.swing.JTextField fieldPort;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel labelAdressIP;
    private javax.swing.JLabel labelPort;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JLabel labelTitle1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onConnectionAdmitted(String message) {
        System.out.println(message);
        JOptionPane.showMessageDialog(null, message, "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        new ClientGUI().setVisible(true);
    }

    @Override
    public void onConnectionReject(String message) {
        System.out.println("Error: " + message);
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
