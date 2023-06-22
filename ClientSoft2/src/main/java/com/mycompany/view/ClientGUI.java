/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.view;

import com.mycompany.controller.CommunicationListener;
import com.mycompany.controller.ClientController;
import com.mycompany.dto.DocumentDTO;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class ClientGUI extends javax.swing.JFrame implements CommunicationListener {

    private final ClientController clientController = ClientController.getInstance();
    private DocumentDTO selectedDocument;

    public ClientGUI() {
        initComponents();
        this.setLocationRelativeTo(null);
        clientController.addSubscriber(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ButtonListar = new javax.swing.JButton();
        ButtonRecibir = new javax.swing.JButton();
        ButtonEnviar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextPaneMensajedeServidor = new javax.swing.JTextPane();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableOfDocuments = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));
        jPanel1.setToolTipText("Basico");

        ButtonListar.setText("Listar");
        ButtonListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonListarActionPerformed(evt);
            }
        });

        ButtonRecibir.setText("Recibir");
        ButtonRecibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonRecibirActionPerformed(evt);
            }
        });

        ButtonEnviar.setText("Enviar");
        ButtonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonEnviarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Showcard Gothic", 3, 36)); // NOI18N
        jLabel1.setText("MenÚ Principal");

        jScrollPane2.setViewportView(TextPaneMensajedeServidor);

        jLabel3.setFont(new java.awt.Font("Showcard Gothic", 0, 18)); // NOI18N
        jLabel3.setText("Mensaje del Servidor:");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        jLabel2.setText("CLIENTE SOFTWARE 2");

        tableOfDocuments.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableOfDocuments.setCellSelectionEnabled(true);
        jScrollPane3.setViewportView(tableOfDocuments);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jLabel2)
                .addContainerGap(221, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(136, 136, 136)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ButtonRecibir, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ButtonListar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ButtonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(89, 89, 89)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonListar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonRecibir, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ButtonEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1243, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleName("Mensaje de servidor");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonListarActionPerformed
        try {
            clientController.requestDocumentList();
        } catch (IOException ex) {
            Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ButtonListarActionPerformed


    private void ButtonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonEnviarActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar archivo");

        int result = fileChooser.showOpenDialog(this); // Mostrar el diálogo de selección de archivo

        if (result == JFileChooser.APPROVE_OPTION) { // Si se selecciona un archivo
            File file = fileChooser.getSelectedFile();
            try {
                clientController.sendDocumentToServer(file);
                System.out.println("Archivo enviado correctamente");
            } catch (IOException ex) {
                System.out.println("Hubo un error al enviar el archivo");
                Logger.getLogger(ClientGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ButtonEnviarActionPerformed

    private void ButtonRecibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonRecibirActionPerformed
        clientController.requestDownloadDocument(selectedDocument);
    }//GEN-LAST:event_ButtonRecibirActionPerformed

    @Override
    public void onDocumentsReceived(List<DocumentDTO> listDocumentos) {
        // Crear el modelo de la tabla
        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desactiva la edición de las celdas
            }
        };
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Extensión");
        tableModel.addColumn("Tamaño");
        tableModel.addColumn("Propietario");

        for (DocumentDTO document : listDocumentos) {
            Object[] rowData = {
                document.getName(),
                document.getExtension(),
                document.getSize(),
                document.getOwner()
            };
            tableModel.addRow(rowData);
        }

        tableOfDocuments.setModel(tableModel);

        // Ajustar el tamaño de las columnas automáticamente
        tableOfDocuments.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tableOfDocuments.getTableHeader().setReorderingAllowed(false);
        // Configurar la selección de fila
        tableOfDocuments.setRowSelectionAllowed(true);
        tableOfDocuments.setColumnSelectionAllowed(false);
        tableOfDocuments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableOfDocuments.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent Mouse_evt) {
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if (Mouse_evt.getClickCount() == 1) {
                    Object name = tableOfDocuments.getValueAt(row, 0);
                    Object extension = tableOfDocuments.getValueAt(row, 1);
                    Object size = tableOfDocuments.getValueAt(row, 2);
                    Object owner = tableOfDocuments.getValueAt(row, 3);

                    // Crear un objeto DocumentDTO y establecer los valores
                    selectedDocument = new DocumentDTO();
                    selectedDocument.setName((String) name);
                    selectedDocument.setSize((long) size);
                    selectedDocument.setExtension((String) extension);
                    selectedDocument.setOwner((String) owner);
                    System.out.println(selectedDocument.toString());
                }
            }
        });
    }

    @Override
    public void onMessageReceived(String message) {
        TextPaneMensajedeServidor.setText(message);
        
    }

    @Override
    public void onDocumentReceived(DocumentDTO documentDTO) {
        // Crear un JFileChooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar documento");

        // Establecer el nombre predeterminado del archivo sugerido
        String fileName = documentDTO.getName() + "." + documentDTO.getExtension();
        fileChooser.setSelectedFile(new File(fileName));

        // Mostrar el diálogo para guardar archivo
        int userChoice = fileChooser.showSaveDialog(null);

        if (userChoice == JFileChooser.APPROVE_OPTION) {
            // Obtener la ruta y el nombre del archivo seleccionado por el usuario
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            // Guardar el archivo en la ubicación seleccionada
            try (FileOutputStream fos = new FileOutputStream(selectedFile)) {
                // Decodificar el contenido codificado en Base64
                byte[] decodedContent = Base64.getDecoder().decode(documentDTO.getEncodedFileContent());

                // Escribir el contenido en el archivo
                fos.write(decodedContent);
                JOptionPane.showMessageDialog(null, "Archivo guardado exitosamente.", "Guardar archivo", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonEnviar;
    private javax.swing.JButton ButtonListar;
    private javax.swing.JButton ButtonRecibir;
    private javax.swing.JTextPane TextPaneMensajedeServidor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tableOfDocuments;
    // End of variables declaration//GEN-END:variables

}
