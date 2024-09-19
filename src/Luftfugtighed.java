import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Luftfugtighed {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5000;
        while (true) {
            try (Socket socket = new Socket(hostname, port)) {
                OutputStream output = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(output, true);

                //Vi sætter min som 25 og max som 75, da tærsklerne er 30 og 70

                int min = 25;
                int max = 75;
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

