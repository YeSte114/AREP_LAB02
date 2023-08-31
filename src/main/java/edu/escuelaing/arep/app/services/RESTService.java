/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.escuelaing.arep.app.services;

/**
 * Interfaz para la implementacion de un servicio REST.
 * @author Yeison Barreto
 */
public interface RESTService {
    /**
     * Obtiene el Header de un recurso que se solicite.
     * @param type El tipo o extensión del recurso.
     * @param code Codigo del HTTP.
     * @return EL header del recurso.
     */
    public String getHeader(String type, String code);

    /**
     * Obtiene el contenido o cuerpo del recurso que se solicite.
     * @param path El path o dirección de donde se encuentra el recurso.
     * @return El contenido del recurso.
     */
    public String getResponse(String path);
}
