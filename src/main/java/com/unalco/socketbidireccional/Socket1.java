package com.unalco.socketbidireccional;

import java.io.*;
import java.net.Socket;
import java.util.logging.*;
import javax.swing.*;

public class Socket1 extends javax.swing.JFrame {

    private static final String SERVER_HOST = "127.0.0.1";
    private static final int SERVER_PORT = 9999;
    private static final Logger LOGGER = Logger.getLogger(Socket1.class.getName());

    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;

    public Socket1() {
        initComponents();
        this.setTitle("Cliente de Sockets");
        this.setVisible(true);
        iniciarConexion();
    }

    private void iniciarConexion() {
        try {
            socket = new Socket(SERVER_HOST, SERVER_PORT);
            output = new DataOutputStream(socket.getOutputStream());
            input = new DataInputStream(socket.getInputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        String mensaje = input.readUTF();
                        jTextArea1.append("\n[Servidor] " + mensaje);
                    }
                } catch (IOException e) {
                    LOGGER.log(Level.SEVERE, "Conexión cerrada o error al recibir", e);
                }
            }).start();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "No se pudo conectar al servidor", e);
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        texto = new javax.swing.JTextField();
        boton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(30);
        jTextArea1.setRows(10);
        jScrollPane1.setViewportView(jTextArea1);

        boton.setText("Enviar");
        boton.addActionListener(evt -> enviarMensaje());

        texto.addActionListener(evt -> enviarMensaje());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(texto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(boton))
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
            .addComponent(jScrollPane1)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(texto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(boton))
        );

        pack();
    }

    private void enviarMensaje() {
        String mensaje = texto.getText().trim();
        if (!mensaje.isEmpty()) {
            try {
                output.writeUTF(mensaje);
                jTextArea1.append("\n[Yo] " + mensaje);
                texto.setText("");
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "No se pudo enviar el mensaje", e);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Socket1::new);
    }

    private javax.swing.JButton boton;
    private javax.swing.JTextField texto;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JScrollPane jScrollPane1;
}
