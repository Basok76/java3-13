package org.example.Glava5;
import java.util.ArrayList;
import java.util.List;

public class Payment {
    private List<Item> items;

    public Payment() {
        items = new ArrayList<>();
    }

    public void addItem(String name, double price, int quantity) {
        items.add(new Item(name, price, quantity));
    }

    public void removeItem(String name) {
        items.removeIf(item -> item.getName().equalsIgnoreCase(name));
    }

    public double calculateTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }

    public void showItems() {
        if (items.isEmpty()) {
            System.out.println("Корзина пуста.");
        } else {
            System.out.println("Товары в корзине:");
            for (Item item : items) {
                System.out.println(item);
            }
        }
    }

    private static class Item {
        private String name;
        private double price;
        private int quantity;

        public Item(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return String.format("Товар: %s, Цена: %.2f, Количество: %d", name, price, quantity);
        }
    }

    public static void main(String[] args) {
        Payment payment = new Payment();

        payment.addItem("Книга", 500.0, 2);
        payment.addItem("Ручка", 50.0, 5);
        payment.addItem("Ноутбук", 50000.0, 1);

        payment.showItems();

        System.out.printf("Общая стоимость: %.2f%n", payment.calculateTotal());

        payment.removeItem("Ручка");
        payment.showItems();
        System.out.printf("Общая стоимость после удаления: %.2f%n", payment.calculateTotal());
    }
}
