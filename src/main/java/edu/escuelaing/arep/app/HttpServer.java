package edu.escuelaing.arep.app;

import java.net.*;
import java.io.*;
import java.util.HashMap;

import edu.escuelaing.arep.app.services.PageService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.*;

/**
 * Levanta servio WEB el cual corre por puerto 35000
 *
 * @author Yeison Barreto
 */
public class HttpServer {

    private static HttpServer instance;

    /**
     * *
     * Encargado de crear la instancia del HttpServer
     *
     * @param
     * @return Instancia
     */
    public static HttpServer getInstance() {
        if (instance == null) {
            instance = new HttpServer();
        }
        return instance;
    }

    /**
     * *
     * Metodo principal
     *
     * @param args
     * @throws IOException
     */
    public void run(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }

        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine, outputLine, title = "";

            boolean first_line = true;
            String request = "/simple";

            while ((inputLine = in.readLine()) != null) {
                if (inputLine.contains("info?title=")) {
                    String[] prov = inputLine.split("title=");
                    title = (prov[1].split("HTTP")[0]).replace(" ", "");
                }
                if (first_line) {
                    request = inputLine.split(" ")[1];
                    first_line = false;
                }

                if (!in.ready()) {
                    break;
                }
            }

            if (request.startsWith("/apps/")) {
                outputLine = executeService(request.substring(5));
                //outputLine = jsonSimple();
            } else if (!title.equals("")) {
                String response = APIConnection.solicitTitle(title, "http://www.omdbapi.com/?t=" + title + "&apikey=7ca9f0c2");
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<br>"
                        + "<table border=\" 1 \"> \n " + createTable(response)
                        + "    </table>";
            } else {
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + index();
            }
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * Ejecuta un servicio indicado por el path /apps/
     *
     * @param serviceName El nombre del servicio o recurso a ejecutar.
     * @return EL Header y Body del recurso solicitado, en caso de no
     * encontrarse el recurso se le dirigir√° a un 404.
     */
    private String executeService(String serviceName) {
        PageService ps = PageService.getInstance();
        try {
            String type = serviceName.split("\\.")[1];
            String header = ps.getHeader(type, "200 OK");
            String body = ps.getResponse("src/main/resources/" + serviceName);
            return header + body;
        } catch (RuntimeException e) {
            String header = ps.getHeader("html", "404 Not Found");
            String body = ps.getResponse("src/main/resources/Error404.html");
            return header + body;
        }
    }

    /**
     * *
     * Contenido en tabla de un String
     *
     * @param response
     * @return
     */
    public static String createTable(String response) {
        Map<String, String> dict = new HashMap<>();
        JSONArray jsonArray = new JSONArray(response);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            for (String key : object.keySet()) {
                dict.put(key, object.get(key).toString());
            }
        }

        List<String> keys = new ArrayList<>(dict.keySet());
        List<String> values = new ArrayList<>(dict.values());

        String table = "<table>\n";
        for (int i = 0; i < keys.size(); i++) {
            table += "<tr>\n";
            table += "<td>" + keys.get(i) + "</td>\n";
            table += "<td>" + values.get(i) + "</td>\n";
            table += "<tr>\n";
        }
        table += "</table>\n";

        return table;
    }

    /**
     * Entregar el index de la p·gina principal
     *
     * @return index en formato de String del HTML del inicio de la P·gina
     */
    private static String index() {
        return "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "    <title>Search</title>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<font size=6 face=\"verdana\">Consultar  informacion de peliculas de cine</font>\n"
                + "\n"
                + "<form action=\"/hello\">\n"
                + "    <label for=\"name\">Titulo de pelicula:</label><br>\n"
                + "    <input type=\"text\" id=\"name\" name=\"name\" value=\"Guardians of the galaxy\"><br><br>\n"
                + "    <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n"
                + "</form>\n"
                + "<div id=\"getrespmsg\"></div>\n"
                + "\n"
                + "<script>\n"
                + "            function loadGetMsg() {\n"
                + "                let nameVar = document.getElementById(\"name\").value;\n"
                + "                const xhttp = new XMLHttpRequest();\n"
                + "                xhttp.onload = function() {\n"
                + "                    document.getElementById(\"getrespmsg\").innerHTML =\n"
                + "                    this.responseText;\n"
                + "                }\n"
                + "                xhttp.open(\"GET\", \"/info?title=\"+nameVar);\n"
                + "                xhttp.send();\n"
                + "            }\n"
                + "        </script>\n"
                + "</body>\n"
                + "</html>";
    }
}
