package escuelaing.edu.co;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private static HttpServer _instance = new HttpServer();

    public HttpServer() {
    }

    public static HttpServer get_instance() {
        return _instance;
    }

    public static void start(String[] args) throws Exception{
        ExecutorService poolThreads = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e){
            System.err.println("Cloud not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running){
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e){
                System.err.println("Accet failed.");
                System.exit(1);
            }

            RequestProcesor requestProcesor = new RequestProcesor(clientSocket);

            poolThreads.execute(requestProcesor);
        }
    }

    private static int getPort(){
        if (System.getenv("PORT") != null){
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000;
    }

}
