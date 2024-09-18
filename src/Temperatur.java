import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Temperatur {
    public static void main(String[] args) {
        Temperatur temperatur = new Temperatur();

        while (true) {
            temperatur.temperaturTal();

        }

    }
    public void temperaturTal(){
        String hostname = "localhost";
        int port = 5000;
        try (Socket socket = new Socket(hostname, port)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(output,true);

            //Vi sætter min som 5 og max som 35, da tærsklerne er 10 og 30
            while(true){
                int min=5;
                int max=35;
                int randomNum= min+(int)(Math.random()*((max-min)+1));
                printWriter.println(randomNum);
                System.out.println(randomNum);
                Thread thread =new Thread();
                thread.start();
                thread.sleep(5000);
            }
        } catch (IOException e) {
            e.printStackTrace();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
