import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int threadPoolSize = 10;

    public static void main(String[] args) {

        ExecutorService threadpool = Executors.newFixedThreadPool(threadPoolSize);

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is now listening on port 5000");
            while (true) {
                Socket socket = serverSocket.accept();
                threadpool.submit(new ClientHandler(socket));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

