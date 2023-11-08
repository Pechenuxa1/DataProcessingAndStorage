import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        int serverPort = Integer.parseInt(args[0]);
        Socket clientSocket = new Socket("localhost", serverPort);

        Thread targetToClient = new Thread(() -> {
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
        Thread clientToTarget = new Thread(() -> {
            try {
                //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                OutputStream writer = clientSocket.getOutputStream();

                Scanner reader = new Scanner(System.in);

                byte[] buffer = new byte[1024];
                int bytesRead;

                int a = 0;
                while(a != -1) {
                    buffer = reader.nextLine().getBytes();
                    //String message = new String(buffer, StandardCharsets.UTF_8);
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
