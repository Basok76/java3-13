package org.example.Glava10.B2;

import java.io.Serializable;

public class Gift implements Serializable {
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