import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Luftfugtighed {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(hostname, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(output,true);

            //Vi sætter min som 25 og max som 75, da tærsklerne er 70 og 30
            int min=25;
            int max=75;

            int randomNum= min+(int)(Math.random()*((max-min)+1));

            while(true){
                printWriter.println(randomNum);
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
