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
        }
    }
}