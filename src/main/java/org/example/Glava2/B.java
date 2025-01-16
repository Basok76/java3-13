package org.example.Glava2;

import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5};
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число: ");
        int number = Integer.valueOf(scanner.nextLine());
        int count = 0;
        boolean significant = false;
        System.out.println("Определение основания системы счисления для числа: ");
        int decimalNumber = Integer.valueOf(scanner.nextLine());
        System.out.println("Введите систему счисления:");
        int base = Integer.valueOf(scanner.nextLine());;
        String result = "";
        int originalBase = 10;
        int multiplier = 1;
        int monthNumber = 5;

        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                System.out.print(i * j + "\t");
            }
            System.out.println();
        }
        System.out.println("Массив в обратном порядке:");
        for (int i = array.length - 1; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
        int k = Integer.valueOf(scanner.nextLine());
        int n = Integer.valueOf(scanner.nextLine());
        int m = Integer.valueOf(scanner.nextLine());
        if (k > n && k <= m) {
            System.out.println(k + " принадлежит интервалу (n, m]");
        }
        if (k >= n && k < m) {
            System.out.println(k + " принадлежит интервалу [n, m)");
        }
        if (k > n && k < m) {
            System.out.println(k + " принадлежит интервалу (n, m)");
        }
        if (k >= n && k <= m) {
            System.out.println(k + " принадлежит интервалу [n, m]");
        }
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0) {
                System.out.print(i + " ");
            }
        }

        while (number > 0) {
            if (number % 2 == 1) {
                significant = true;
            }
            if (significant && number % 2 == 0) {
                count++;
            }
            number /= 2;
        }
        System.out.println("Количество значащих нулей: " + count);
        for (; base <= 10; base++) {
            int converted = 0;
            int numberDecimal = decimalNumber;

            while (numberDecimal > 0) {
                int digit = numberDecimal % base;
                converted += digit * multiplier;
                multiplier *= 10;
                numberDecimal /= base;
            }

            if (converted == 100) {
                System.out.println("Основание системы счисления: " + base);
                break;
            }
        }

        while (decimalNumber > 0) {
            int remainder = decimalNumber % base;
            result = remainder + result;
            decimalNumber /= base;
        }
        System.out.println("Результат перевода: " + result);

        while (number > 0) {
            decimalNumber += (number % 10) * multiplier;
            multiplier *= originalBase;
            number /= 10;
        }

        while (decimalNumber > 0) {
            int remainder = decimalNumber % base;
            result = remainder + result;
            decimalNumber /= base;
        }
        System.out.println("Результат перевода: " + result);
        if (monthNumber < 1 || monthNumber > 12) {
            System.out.println("Некорректный ввод. Введите число от 1 до 12.");
        } else {
            String[] months = {"Январь", "Февраль", "Март", "Апрель", "Май", "Июнь",
                    "Июль", "Август", "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};
            System.out.println("Месяц: " + months[monthNumber - 1]);
        }
    }
}
