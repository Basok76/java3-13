package org.example.Glava10.B2;

public class B2 {
    public static void main(String[] args) {
        Candy candy1 = new Candy("Конфета 1", 100, 50);
        Candy candy2 = new Candy("Конфета 2", 120, 30);
        Candy candy3 = new Candy("Конфета 3", 80, 60);
        Candy candy4 = new Candy("Конфета 4", 90, 40);

        Gift gift = new Gift(10);
        gift.addSweet(candy1);
        gift.addSweet(candy2);
        gift.addSweet(candy3);
        gift.addSweet(candy4);

        gift.displayGift();
        System.out.println("Общий вес подарка: " + gift.getTotalWeight() + " г");

        gift.sortCandiesBySugarContent();
        System.out.println("\nПодарок после сортировки по содержанию сахара:");
        gift.displayGift();

        Sweet foundCandy = gift.findCandyBySugarRange(30, 50);
        if (foundCandy != null) {
            System.out.println("\nКонфета, соответствующая диапазону содержания сахара 30%-50%: " + foundCandy);
        } else {
            System.out.println("\nКонфета не найдена.");
        }
        GiftConnector.saveGiftToFile(gift, "gift.dat");
        Gift loadedGift = GiftConnector.loadGiftFromFile("gift.dat");

        if (loadedGift != null) {
            System.out.println("\nПодарок загружен из файла:");
            loadedGift.displayGift();
        }
    }
}
