package com.unalco.socketbidireccional;

import java.io.*;
import java.net.*;
import java.util.logging.*;
import javax.swing.*;

public class Socket2 extends javax.swing.JFrame {

    private static final int SERVER_PORT = 9999;
    private static final Logger LOGGER = Logger.getLogger(Socket2.class.getName());

    private ServerSocket server;
    private Socket socket;
    private DataOutputStream output;
    private DataInputStream input;

    public Socket2() {
        initComponents();
        this.setTitle("Servidor de Sockets");
        this.setVisible(true);
        iniciarServidor();
    }

    private void iniciarServidor() {
        new Thread(() -> {
            try {
                server = new ServerSocket(SERVER_PORT);
                jTextArea1.append("Servidor iniciado en puerto " + SERVER_PORT + "\nEsperando cliente...\n");
                socket = server.accept();
                jTextArea1.append("Cliente conectado: " + socket.getInetAddress() + "\n");

                input = new DataInputStream(socket.getInputStream());
                output = new DataOutputStream(socket.getOutputStream());

                while (true) {
                    String mensaje = input.readUTF();
                    jTextArea1.append("\n[Cliente] " + mensaje);
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error en servidor", e);
            }
        }).start();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        texto = new javax.swing.JTextField();
        boton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(700, 300, 0, 0));

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
                LOGGER.log(Level.SEVERE, "Error al enviar mensaje", e);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Socket2::new);
    }

    private javax.swing.JButton boton;
    private javax.swing.JTextField texto;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JScrollPane jScrollPane1;
}
