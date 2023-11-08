import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        int serverPort = Integer.parseInt(args[0]);
        String targetHost = args[1];
        int targetPort = Integer.parseInt(args[2]);

        ServerSocket serverSocket = new ServerSocket(serverPort);
        while(true) {
            Socket clientSocket = serverSocket.accept();
            Socket targetSocket = new Socket(targetHost, targetPort);

            Thread targetToClient = new Thread(() -> {
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(targetSocket.getInputStream()));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    String message;
                    while(!Objects.equals(message = reader.readLine(), " ")) {
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
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(targetSocket.getOutputStream()));

                    String message;
                    while(!Objects.equals(message = reader.readLine(), " ")) {
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
}