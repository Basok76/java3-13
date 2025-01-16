package org.example.Glava14.A2;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static final int PORT = 12346;
    private static Set<PrintWriter> clientWriters = ConcurrentHashMap.newKeySet();
    private static Map<String, PrintWriter> clientNames = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Сервер запущен...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println("Введите ваше имя:");
                clientName = in.readLine();
                synchronized (clientNames) {
                    if (clientNames.containsKey(clientName)) {
                        out.println("Имя уже занято. Попробуйте другое.");
                        return;
                    }
                    clientNames.put(clientName, out);
                }

                synchronized (clientWriters) {
                    clientWriters.add(out);
                }
                broadcast(clientName + " присоединился к чату.");

                String message;
                while ((message = in.readLine()) != null) {
                    if (message.equalsIgnoreCase("/exit")) {
                        break;
                    }
                    broadcast(clientName + ": " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                synchronized (clientWriters) {
                    clientWriters.remove(out);
                }
                synchronized (clientNames) {
                    clientNames.remove(clientName);
                }
                broadcast(clientName + " покинул чат.");
            }
        }

        private void broadcast(String message) {
            for (PrintWriter writer : clientWriters) {
                writer.println(message);
            }
        }
    }
}
