import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Jordfugtighed {
    public static void main(String[] args) {

        String hostname = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(hostname, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(output,true);

            //Vi sætter min som 15 og max som 80, da tærsklen for min er 20
            int min=15;
            int max=80;

            int randomNum= min+(int)(Math.random()*((max-min)+1));

            while(true){
                printWriter.println(randomNum);
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}