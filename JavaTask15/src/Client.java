import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        int serverPort = Integer.parseInt(args[0]);
        Socket clientSocket = new Socket("localhost", serverPort);

        Thread targetToClient = new Thread(() -> {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String message;
                while(!Objects.equals(message = reader.readLine(), " ")) {
                    System.out.println(message);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Thread clientToTarget = new Thread(() -> {
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                Scanner reader = new Scanner(System.in);

                String message;
                while(!Objects.equals(message = reader.nextLine(), " ")) {
                    writer.write(message);
                    writer.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        targetToClient.start();
        clientToTarget.start();
    }
}
