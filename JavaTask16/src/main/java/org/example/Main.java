package org.example;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Terminal
        URL url = new URL("http://lib.ru");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        int lines = 0;
        int pageSize = 25;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine).append("\n");
            lines += 1;
            if (lines == pageSize) {
                System.out.println(response);
                response.setLength(0);
                lines = 0;
                System.out.println("Press space to scroll down.");
                char input = (char) System.in.read();
                if (input != ' ') {
                    break;
                }
            }
            in.close();
            connection.disconnect();
        }
    }
}