package org.example;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        URL url = new URL(args[0]);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        BlockingQueue<String> response = new LinkedBlockingQueue<>();
        Scanner scanner = new Scanner(System.in);

        Reader reader = new Reader(in, response);
        Thread readThread = new Thread(reader);
        readThread.start();

        String output;
        int lines = 0;
        int pageSize = 25;
        while (!(output = response.take()).equals("stop output")) {
            System.out.println(output);
            lines += 1;
            if(lines == pageSize) {
                System.out.println("Press space to scroll down.");
                String input = scanner.nextLine();
                if (!input.equals(" ")) {
                    break;
                }
                lines = 0;
            }
        }
    }

    private static class Reader implements Runnable {
        BufferedReader in;
        BlockingQueue response;
        Reader(BufferedReader in, BlockingQueue response) {
            this.in = in;
            this.response = response;
        }

        @Override
        public void run() {
            String inputLine;
            while (true) {
                try {
                    if ((inputLine = in.readLine()) == null) {
                        response.add("stop output");
                        break;
                    }
                    response.add(inputLine);

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}