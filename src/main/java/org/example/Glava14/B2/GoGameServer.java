package org.example.Glava14.B2;

import java.io.*;
import java.net.*;
import java.util.*;

public class GoGameServer {
    private static final int PORT = 12345;
    private static Map<String, Socket> clients = new HashMap<>();
    private static char[][] board = new char[15][15];
    private static final Object lock = new Object();
    private static String currentPlayer = null;
    private static boolean gameStarted = false;

    static {
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }
    }

    public static void main(String[] args) {
        System.out.println("Сервер Запущен...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                if (clients.size() < 2) {
                    new Thread(new ClientHandler(clientSocket)).start();
                } else {
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    out.println("Превышено количество игроков. Попробуйте подключиться позже.");
                    clientSocket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;
        private String clientId;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                out.println("Введите ваш номер:");
                clientId = in.readLine();
                synchronized (clients) {
                    clients.put(clientId, socket);
                }
                if (clients.size() == 2 && !gameStarted) {
                    currentPlayer = clients.keySet().iterator().next();
                    gameStarted = true;
                    out.println("Успешное подключение: Ожидание хода. Первый ход у игрока " + currentPlayer);
                } else {
                    out.println("Ожидаем второго игрока.");
                }

                String input;
                while ((input = in.readLine()) != null) {
                    String[] parts = input.split(" ");
                    if (parts.length == 3 && parts[0].equalsIgnoreCase("ХОД")) {
                        int x = Integer.parseInt(parts[1]);
                        int y = Integer.parseInt(parts[2]);
                        char symbol = clientId.charAt(0);

                        synchronized (lock) {
                            if (!clientId.equals(currentPlayer)) {
                                out.println("Не ваш ход. Ожидайте своей очереди.");
                                continue;
                            }

                            if (board[x][y] == '.') {
                                board[x][y] = symbol;
                                broadcastBoard();
                                if (checkWin(x, y, symbol)) {
                                    broadcast("Игрок " + clientId + " Победил!");
                                    resetBoard();
                                    currentPlayer = null;
                                    gameStarted = false;
                                } else {
                                    currentPlayer = getOtherPlayer(clientId);
                                    broadcast(clientId + " сделал ход. Ходит " + currentPlayer + ".");
                                }
                            } else {
                                out.println("Клетка занята.");
                            }
                        }
                    } else {
                        out.println("Неверная команда. Введите ХОД x y.");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                synchronized (clients) {
                    clients.remove(clientId);
                    if (clients.size() < 2) {
                        gameStarted = false;
                        currentPlayer = null;
                    }
                }
            }
        }

        private String getOtherPlayer(String current) {
            for (String player : clients.keySet()) {
                if (!player.equals(current)) {
                    return player;
                }
            }
            return null;
        }

        private void broadcastBoard() {
            StringBuilder boardState = new StringBuilder("Доска:\n");
            synchronized (lock) {
                for (char[] row : board) {
                    boardState.append(new String(row)).append("\n");
                }
            }
            broadcast(boardState.toString());
        }

        private boolean checkWin(int x, int y, char symbol) {
            return checkDirection(x, y, symbol, 1, 0) ||
                    checkDirection(x, y, symbol, 0, 1) ||
                    checkDirection(x, y, symbol, 1, 1) ||
                    checkDirection(x, y, symbol, 1, -1);
        }

        private boolean checkDirection(int x, int y, char symbol, int dx, int dy) {
            int count = 1;
            for (int i = 1; i < 5; i++) {
                int nx = x + i * dx, ny = y + i * dy;
                if (nx < 0 || ny < 0 || nx >= 15 || ny >= 15 || board[nx][ny] != symbol) break;
                count++;
            }
            for (int i = 1; i < 5; i++) {
                int nx = x - i * dx, ny = y - i * dy;
                if (nx < 0 || ny < 0 || nx >= 15 || ny >= 15 || board[nx][ny] != symbol) break;
                count++;
            }
            return count >= 5;
        }

        private void resetBoard() {
            synchronized (lock) {
                for (char[] row : board) {
                    Arrays.fill(row, '.');
                }
            }
        }

        private void broadcast(String message) {
            synchronized (clients) {
                for (Socket client : clients.values()) {
                    try {
                        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                        out.println(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
