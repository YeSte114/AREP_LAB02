/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.escuelaing.arep.app;

import java.io.IOException;

/**
 * Clase encargada de iniciar el servidor HttpServer.
 * @author Yeison Barreto
 */
public class App {
    /**
     * Inicia el programa.
     * @param args args
     * @throws IOException Por si algo sale mal en el proceso
     */
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.getInstance();
        server.run(args);
    }
}
