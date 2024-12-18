package Glava10;
import org.example.Glava10.B2.Candy;
import org.example.Glava10.B2.Gift;
import org.example.Glava10.B2.GiftConnector;
import org.example.Glava10.B2.Sweet;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class B2Test {

    @TempDir
    static Path tempDir;

    @Test
    void testSaveGiftToFile() {
        Candy candy1 = new Candy("Конфета 1", 100, 50);
        Candy candy2 = new Candy("Конфета 2", 120, 30);
        Candy candy3 = new Candy("Конфета 3", 80, 60);
        Candy candy4 = new Candy("Конфета 4", 90, 40);
        Gift gift = new Gift(10);
        gift.addSweet(candy1);
        gift.addSweet(candy2);
        gift.addSweet(candy3);
        gift.addSweet(candy4);
        Path giftFile = tempDir.resolve("gift.dat");
        GiftConnector.saveGiftToFile(gift, giftFile.toString());
        assertTrue(Files.exists(giftFile), "Файл не был создан.");
    }

    @Test
    void testLoadGiftFromFile() {
        Candy candy1 = new Candy("Конфета 1", 100, 50);
        Candy candy2 = new Candy("Конфета 2", 120, 30);
        Candy candy3 = new Candy("Конфета 3", 80, 60);
        Candy candy4 = new Candy("Конфета 4", 90, 40);
        Gift gift = new Gift(10);
        gift.addSweet(candy1);
        gift.addSweet(candy2);
        gift.addSweet(candy3);
        gift.addSweet(candy4);
        Path giftFile = tempDir.resolve("gift.dat");
        GiftConnector.saveGiftToFile(gift, giftFile.toString());
        Gift loadedGift = GiftConnector.loadGiftFromFile(giftFile.toString());
        assertNotNull(loadedGift, "Подарок не был загружен из файла.");
        assertEquals(390, loadedGift.getTotalWeight(), "Неверное количество сладостей в загруженном подарке.");
    }

    @Test
    void testSortCandiesBySugarContent() {
        Candy candy1 = new Candy("Конфета 1", 100, 50);
        Candy candy2 = new Candy("Конфета 2", 120, 30);
        Candy candy3 = new Candy("Конфета 3", 80, 60);
        Candy candy4 = new Candy("Конфета 4", 90, 40);
        Gift gift = new Gift(10);
        gift.addSweet(candy1);
        gift.addSweet(candy2);
        gift.addSweet(candy3);
        gift.addSweet(candy4);
        gift.sortCandiesBySugarContent();
        assertEquals(candy2, gift.findCandyBySugarRange(30, 50), "Конфета с сахаром в диапазоне 30%-50% не найдена.");
        assertEquals(candy4, gift.findCandyBySugarRange(40, 50), "Конфета с сахаром в диапазоне 40%-50% не найдена.");
    }

    @Test
    void testFindCandyBySugarRange() {
        Candy candy1 = new Candy("Конфета 1", 100, 50);
        Candy candy2 = new Candy("Конфета 2", 120, 30);
        Candy candy3 = new Candy("Конфета 3", 80, 60);
        Candy candy4 = new Candy("Конфета 4", 90, 40);
        Gift gift = new Gift(10);
        gift.addSweet(candy1);
        gift.addSweet(candy2);
        gift.addSweet(candy3);
        gift.addSweet(candy4);
        Sweet foundCandy = gift.findCandyBySugarRange(30, 50);
        assertNotNull(foundCandy, "Конфета не найдена в заданном диапазоне сахара.");
        assertTrue(foundCandy instanceof Candy, "Найденный объект не является конфетой.");
        assertEquals("Конфета 1", foundCandy.getName(), "Неверная конфета найдена.");
    }
}
