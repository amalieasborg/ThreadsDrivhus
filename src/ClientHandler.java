import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private static final String LOG_FILE = "log.txt";

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            readFromClients();
            BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void readFromClients() {
        while(true){
            try (InputStream input = socket.getInputStream()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                PrintWriter writer=new PrintWriter(socket.getOutputStream(), true);
                String message;
                while ((message = reader.readLine()) != null) {
                    System.out.println(message);
                    int temperatur = Integer.parseInt(message);
                    log("Temperature: " + temperatur+"°C");
                    temperaturAlarm(temperatur,reader,writer);

                    int luftfugtighed=Integer.parseInt(message);
                    log("Luftfugtighed: " + luftfugtighed+"%");
                    luftfugtighedAlarm(luftfugtighed);

                    int jordfugtighed=Integer.parseInt(message);
                    log("Jordfugtighed: " + jordfugtighed+"%");
                    jordfugtigehedAlarm(jordfugtighed);

                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void temperaturAlarm(int number,BufferedReader reader,PrintWriter writer) {
        if (number < 10) {
            System.out.println("ALARM: Temperature below low threshold! (" + number + "°C)");
        } else if (number > 30) {
            System.out.println("ALARM: Temperature exceeds high threshold! (" + number + "°C)");
        } else {
            // If the temperature is within the normal range, no alarm
            System.out.println("Temperature is within the normal range: (" + number + "°C)");
        }
    }
    public void luftfugtighedAlarm(int number) {
        if (number < 30) {
            System.out.println("ALARM: Humidity below low threshold! (" + number + "%)");
        } else if (number > 70) {
            System.out.println("ALARM: Humidity exceeds high threshold! (" + number + "%)");
        } else {
            // If the temperature is within the normal range, no alarm
            System.out.println("Humidity is within the normal range: (" + number + "%)");
        }
    }
    public void jordfugtigehedAlarm(int number) {
        if (number < 20) {
            System.out.println("ALARM: Soil moisture below  threshold! (" + number + "%)");
        } else {
            // If the temperature is within the normal range, no alarm
            System.out.println("Soil moisture is within the normal range: (" + number + "%)");
        }
    }

    public void log(String data){
        try(FileWriter fw=new FileWriter(ClientHandler.LOG_FILE,true);
            BufferedWriter bw=new BufferedWriter(fw);
            PrintWriter writer=new PrintWriter(fw)) {
            writer.println(data);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
