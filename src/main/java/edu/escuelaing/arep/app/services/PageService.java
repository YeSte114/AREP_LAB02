/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.escuelaing.arep.app.services;

import java.io.IOException;
import java.nio.file.*;

/**
 * Obtener todos los recursos necesario para un Servicio WEB.
 * @author Yeison Barreto
 */
public class PageService implements RESTService {

    public static PageService instance;

    /**
     * Constructor
     */
    private PageService() {
    }

    /**
     * Obtener la instancia de la clase segun el patrón de Singleton
     *
     * @return La única instancia de la clase
     */
    public static PageService getInstance() {
        if (instance == null) {
            instance = new PageService();
        }
        return instance;
    }

    @Override
    public String getHeader(String type, String code) {
        return "HTTP/1.1 " + code + "\r\n"
                + "Content-type: text/" + type + "\r\n"
                + "\r\n";
    }

    @Override
    public String getResponse(String path) {
        byte[] fileContent;
        try {
            fileContent = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new String(fileContent);
    }

}
