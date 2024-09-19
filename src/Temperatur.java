import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Temperatur {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 5000;
        while (true) {
            try (Socket socket = new Socket(hostname, port)) {
                OutputStream output = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(output, true);

                //Vi sætter min som 5 og max som 35, da tærsklerne er 10 og 30

                int min = 5;
                int max = 35;
                int randomNum = min + (int) (Math.random() * ((max - min) + 1));
                printWriter.println(randomNum);
                System.out.println(randomNum);
                Thread thread = new Thread();
                thread.start();
                thread.sleep(5000);
            }catch(InterruptedException e){
                throw new RuntimeException();
            }
            catch (UnknownHostException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }

}

