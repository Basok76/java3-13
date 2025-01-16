package Glava14;
import org.example.Glava14.B2.GoGameServer;
import org.junit.jupiter.api.*;
import java.io.*;
import java.net.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BTest {

    private static Thread serverThread;
    private static Socket player1;
    private static Socket player2;
    private static BufferedReader in1;
    private static BufferedReader in2;
    private static PrintWriter out1;
    private static PrintWriter out2;

    @BeforeAll
    public static void startServer() {
        serverThread = new Thread(() -> {
            GoGameServer.main(new String[]{});
        });
        serverThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setupClients() throws IOException {
        player1 = new Socket("localhost", 12345);
        player2 = new Socket("localhost", 12345);

        in1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
        in2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
        out1 = new PrintWriter(player1.getOutputStream(), true);
        out2 = new PrintWriter(player2.getOutputStream(), true);

        out1.println("Игрок1");
        out2.println("Игрок2");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void closeSockets() throws IOException {
        if (player1 != null && !player1.isClosed()) {
            player1.close();
        }
        if (player2 != null && !player2.isClosed()) {
            player2.close();
        }
    }

    @AfterAll
    public static void stopServer() {
        serverThread.interrupt();
    }

    @Test
    public void testTwoPlayersConnect() throws IOException {
        String response1 = in1.readLine();
        assertNotNull(response1);
        assertTrue(response1.contains("Введите ваш номер:"));

        String response2 = in2.readLine();
        assertNotNull(response2);
        assertTrue(response2.contains("Введите ваш номер:"));
    }
}
