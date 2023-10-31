import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Target {
    public static void main(String[] args) throws IOException {
        int targetPort = Integer.parseInt(args[2]);
        ServerSocket targetSocket = new ServerSocket(targetPort);
        Socket clientSocket = targetSocket.accept();
        Thread targetToClient = new Thread(() -> {
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
        Thread clientToTarget = new Thread(() -> {
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
        targetToClient.start();
        clientToTarget.start();
    }
}
