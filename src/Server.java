import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int threadPoolSize = 10;


    public static void main(String[] args) {
        ExecutorService threadPool= Executors.newFixedThreadPool(threadPoolSize);
        //Run the method with the listening thread


        Server server = new Server();
        server.listeningThread();


    }
    public void listeningThread() {

        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is listening on port 5000");
            Socket socket = serverSocket.accept();
            System.out.println("New client connected");

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            String text;
            while((text =reader.readLine())!=null){
                System.out.println("Message from client: "+text);
                writer.println("Echo: "+text);
            }


        } catch (IOException e) {
            System.out.println("Server exception: "+e.getMessage());
            e.printStackTrace();
        }
    }

}