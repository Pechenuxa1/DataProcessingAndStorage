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
                    //BufferedReader reader = new BufferedReader(new InputStreamReader(targetSocket.getInputStream()));
                    //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                    InputStream reader = targetSocket.getInputStream();
                    OutputStream writer = clientSocket.getOutputStream();

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    String message;
                    while((bytesRead = reader.read(buffer)) != -1) {
                        writer.write(buffer);
                        //writer.flush();
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            Thread clientToTarget = new Thread(() -> {
                try {
                    //BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(targetSocket.getOutputStream()));

                    InputStream reader = clientSocket.getInputStream();
                    OutputStream writer = targetSocket.getOutputStream();

                    byte[] buffer = new byte[1024];
                    int bytesRead;


                    String message;
                    while((bytesRead = reader.read(buffer)) != -1) {
                        writer.write(buffer);
                        //writer.flush();
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