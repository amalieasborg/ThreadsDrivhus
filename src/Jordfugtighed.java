import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Jordfugtighed {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5000;
        while (true) {
            try (Socket socket = new Socket(hostname, port)) {
                OutputStream output = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(output, true);

                //Vi sætter min som 15 og max som 100, da tærsklen for minimum er 20

                int min = 15;
                int max = 100;
                int randomNum = min + (int) (Math.random() * ((max - min) + 1));
                printWriter.println(randomNum);
                System.out.println(randomNum);
                Thread thread = new Thread();
                thread.start();
                thread.sleep(5000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

