package org.example.Glava4;
abstract class Sweet {
    protected String name;
    protected double weight;

    public Sweet(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name + " (Вес: " + weight + " г)";
    }
}

class Candy extends Sweet {
    private double sugarContent;

    public Candy(String name, double weight, double sugarContent) {
        super(name, weight);
        this.sugarContent = sugarContent;
    }

    public double getSugarContent() {
        return sugarContent;
    }

    @Override
    public String toString() {
        return super.toString() + ", Сахар: " + sugarContent + "%";
    }
}

class Gift {
    private Sweet[] sweets;
    private int count;

    public Gift(int capacity) {
        sweets = new Sweet[capacity];
        count = 0;
    }

    public void addSweet(Sweet sweet) {
        if (count < sweets.length) {
            sweets[count++] = sweet;
        }
    }

    public void sortCandiesBySugarContent() {
        for (int i = 0; i < count - 1; i++) {
            for (int j = i + 1; j < count; j++) {
                if (sweets[i] instanceof Candy && sweets[j] instanceof Candy) {
                    Candy candy1 = (Candy) sweets[i];
                    Candy candy2 = (Candy) sweets[j];
                    if (candy1.getSugarContent() > candy2.getSugarContent()) {
                        Sweet temp = sweets[i];
                        sweets[i] = sweets[j];
                        sweets[j] = temp;
                    }
                }
            }
        }
    }

    public Sweet findCandyBySugarRange(double minSugar, double maxSugar) {
        for (int i = 0; i < count; i++) {
            if (sweets[i] instanceof Candy) {
                Candy candy = (Candy) sweets[i];
                if (candy.getSugarContent() >= minSugar && candy.getSugarContent() <= maxSugar) {
                    return candy;
                }
            }
        }
        return null;
    }

    public void displayGift() {
        System.out.println("Подарок содержит следующие сладости:");
        for (int i = 0; i < count; i++) {
            System.out.println(sweets[i]);
        }
    }

    public double getTotalWeight() {
        double totalWeight = 0;
        for (int i = 0; i < count; i++) {
            totalWeight += sweets[i].getWeight();
        }
        return totalWeight;
    }
}

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
    }
}