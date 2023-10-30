package org.example;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        URL url = new URL(args[0]);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String inputLine;
        Scanner scanner = new Scanner(System.in);
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
                String input = scanner.nextLine();
                System.out.println(input);
                if (!input.equals(" ")) {
                    break;
                }
            }
        }
        in.close();
        connection.disconnect();
    }
}