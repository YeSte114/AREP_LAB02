package edu.escuelaing.arep.app.AREP_LAB01;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Almacena o retorna peliculas que se consulten en el server
 * @author Yeison Barreto
 */
public class Cache {

    private static Cache instance;

    private static ConcurrentHashMap<String, String> cache;

    /**
     * Constructor de la clase Cache()
     */
    public Cache() {
        cache = new ConcurrentHashMap<String, String>();
    }

    /**
     * Consulta el titulo de una pelicula para saber si esta incluida.
     *
     * @param title Titulo a consultar
     * @return Retorna el valor de verdad de una pelicula si esta almacenada.
     */
    public boolean isOnCache(String title) {
        return cache.containsKey(title);
    }

    /**
     * Agrega el titulo de una pelicula y su información al cache.
     *
     * @param title Titulo de la pelicula
     * @param json Información de la pelicula
     */
    public void addMovie(String title, String json) {
        cache.put(title, json);
    }

    /**
     * Retorna descripción de una pelicula almacenada en el cache filtrada por su titulo.
     *
     * @param title Titulo de la pelicula a consultar
     * @return Información de la pelicula consultada
     */
    public String getMovieDescription(String title) {
        return cache.get(title);
    }

    /**
     * Retorna unica instancia de cache que existe basado en patron de SINGLETON
     *
     * @return Instancia del Cache
     */
    public static Cache getInstance() {

        if (instance == null) {
            instance = new Cache();
        }
        return instance;
    }

    /**
     * Retorna tamaño del caché basado en la cantidad de las peliculas almacenadas.
     *
     * @return Cantidad de peliculas alamacenadas en el cache
     */
    public int size() {
        return cache.size();
    }

    /**
     * Limpia al caché y lo deja sin titulos de peliculas.
     */
    public void clear() {
        cache.clear();
    }
}
