package com.unalco.socketbidireccional;
import javax.swing.SwingUtilities;

/**
 * Clase principal que inicia la aplicación de comunicación por sockets.
 * 
 * Esta clase crea instancias del cliente (socket1) y del servidor (socket2)
 * para demostrar la comunicación bidireccional usando sockets TCP.
 * 
 * @author locon
 * @version 1.1
 */
public class Control {

    /**
     * Método principal que inicia el cliente y el servidor.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        try {
            System.out.println("=== Iniciando Aplicación de Sockets ===");
            System.out.println("Puerto de comunicación: 9999");
            System.out.println("Dirección del servidor: 127.0.0.1");
            System.out.println("=======================================");

            // Iniciar servidor en el hilo de eventos de Swing
            SwingUtilities.invokeLater(() -> {
                System.out.println("Iniciando servidor...");
                new Socket2();
            });

            // Pausa para permitir que el servidor se inicie
            Thread.sleep(1000);

            // Iniciar cliente en el hilo de eventos de Swing
            SwingUtilities.invokeLater(() -> {
                System.out.println("Iniciando cliente...");
                new Socket1();
            });

            System.out.println("Aplicación iniciada correctamente.");
            System.out.println("El servidor está escuchando en el puerto 9999.");
            System.out.println("El cliente está listo para enviar y recibir mensajes.");

        } catch (InterruptedException e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            Thread.currentThread().interrupt(); // buena práctica
        } catch (Exception e) {
            System.err.println("Error inesperado al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
