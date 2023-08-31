import edu.escuelaing.arep.app.AREP_LAB01.APIConnection;

import java.io.IOException;

/**
 *
 * @author Yeison Barreto
 */
public class ThreadTest extends Thread{

    private final String title;
    private String response;
    public ThreadTest(String title){
        super();
        this.title = title;
    }

    @Override
    public void run(){
        try {
            response = APIConnection.solicitTitle(title,"https://www.omdbapi.com/?t=" + title + "&apikey=f33b484c");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getResponse(){
        return response;
    }

    public String getTitle(){
        return title;
    }
}