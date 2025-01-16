package Glava14;

import org.example.Glava14.A2.Server;
import org.junit.jupiter.api.*;
import java.io.*;
import java.net.*;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

public class ATest {
    private static final int PORT = 12345;
    private static Server server;
    private static Thread serverThread;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    @BeforeAll
    public static void setUp() throws InterruptedException {
        serverThread = new Thread(() -> {
            try {
                Server.main(new String[]{});
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
        TimeUnit.SECONDS.sleep(1);
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        serverThread.interrupt();
        executorService.shutdown();
        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void testServerCanAcceptClient() throws Exception {
        try (Socket clientSocket = new Socket("localhost", PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            out.println("Hello, Server!");

            // Проверяем, что сервер прислал какое-то сообщение
            String response = in.readLine();
            assertNotNull(response);
            assertEquals("Введите ваше имя:", response);
        }
    }

    @Test
    public void testServerHandlesMultipleClients() throws Exception {
        Socket client1 = new Socket("localhost", PORT);
        PrintWriter out1 = new PrintWriter(client1.getOutputStream(), true);
        BufferedReader in1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
        out1.println("Client1");
        in1.readLine();
        Socket client2 = new Socket("localhost", PORT);
        PrintWriter out2 = new PrintWriter(client2.getOutputStream(), true);
        BufferedReader in2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
        out2.println("Client2");
        in2.readLine();
        String response2 = in2.readLine();
        assertEquals("Client2 присоединился к чату.", response2);
    }

}
