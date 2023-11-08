import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class Target {
    public static void main(String[] args) throws IOException {
        int targetPort = Integer.parseInt(args[2]);
        ServerSocket targetSocket = new ServerSocket(targetPort);
        Socket clientSocket = targetSocket.accept();
        Thread targetToClient = new Thread(() -> {
            try {
                //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                OutputStream writer = clientSocket.getOutputStream();

                Scanner reader = new Scanner(System.in);
                byte[] buffer = new byte[1024];
                int bytesRead;

                String message;
                int a = 0;
                while(a != -1) {
                    buffer = reader.nextLine().getBytes();
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

                InputStream reader = clientSocket.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while((bytesRead = reader.read(buffer)) != -1) {
                    String message = new String(buffer, 0, bytesRead, StandardCharsets.UTF_8);
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
