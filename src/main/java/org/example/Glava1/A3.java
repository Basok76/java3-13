package org.example.Glava1;

import java.util.Random;

public class A3 {
    public static void main(String[] args) {
        int count = Integer.parseInt(args[0]);
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            System.out.print(random.nextInt(10) + " ");
        }

        System.out.println();

        for (int i = 0; i < count; i++) {
            System.out.println(random.nextInt(10));
        }
    }
}
