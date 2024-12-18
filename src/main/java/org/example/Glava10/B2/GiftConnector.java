package org.example.Glava10.B2;
import java.io.*;

public class GiftConnector {
    public static void saveGiftToFile(Gift gift, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(gift);
            System.out.println("Подарок сохранен в файл: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении: " + e.getMessage());
        }
    }

    public static Gift loadGiftFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Gift) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке: " + e.getMessage());
            return null;
        }
    }
}