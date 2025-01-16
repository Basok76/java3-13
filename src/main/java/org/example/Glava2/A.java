package org.example.Glava2;

import java.util.Arrays;
import java.util.Comparator;

public class A {
    public static void main(String[] args) {
        int[] numbers = {123, 45678, 9, 23456, 7890, 12, 1234567};

        int shortestNumber = numbers[0];
        int longestNumber = numbers[0];
        int totalLength = 0;
        int minUniqueDigitsCount = Integer.MAX_VALUE;
        int resultNumber = numbers[0];
        int evenDigitNumbersCount = 0;
        int equalEvenOddDigitsCount = 0;
        int palindromeCount = 0;
        int increasingNumber = -1;
        int uniqueDigitsNumber = -1;

        for (int number : numbers) {
            int length = String.valueOf(number).length();
            totalLength += length;

            if (length < String.valueOf(shortestNumber).length()) shortestNumber = number;
            if (length > String.valueOf(longestNumber).length()) longestNumber = number;

            boolean[] digitsSeen = new boolean[10];
            int uniqueCount = 0;
            int temp = number;
            while (temp > 0) {
                int digit = temp % 10;
                if (!digitsSeen[digit]) {
                    digitsSeen[digit] = true;
                    uniqueCount++;
                }
                temp /= 10;
            }
            if (uniqueCount < minUniqueDigitsCount) {
                minUniqueDigitsCount = uniqueCount;
                resultNumber = number;
            }

            boolean allEvenDigits = true;
            int evenCount = 0;
            int oddCount = 0;
            temp = number;
            while (temp > 0) {
                int digit = temp % 10;
                if (digit % 2 == 0) evenCount++;
                else {
                    oddCount++;
                    allEvenDigits = false;
                }
                temp /= 10;
            }
            if (allEvenDigits) evenDigitNumbersCount++;
            if (evenCount == oddCount) equalEvenOddDigitsCount++;

            temp = number;
            boolean isIncreasing = true;
            int previousDigit = 10;
            while (temp > 0) {
                int digit = temp % 10;
                if (digit >= previousDigit) {
                    isIncreasing = false;
                    break;
                }
                previousDigit = digit;
                temp /= 10;
            }
            if (isIncreasing && increasingNumber == -1) increasingNumber = number;

            temp = number;
            boolean[] seenDigits = new boolean[10];
            boolean hasDuplicate = false;
            while (temp > 0) {
                int digit = temp % 10;
                if (seenDigits[digit]) {
                    hasDuplicate = true;
                    break;
                }
                seenDigits[digit] = true;
                temp /= 10;
            }
            if (!hasDuplicate && uniqueDigitsNumber == -1) uniqueDigitsNumber = number;

            int original = number, reversed = 0;
            temp = number;
            while (temp != 0) {
                int digit = temp % 10;
                reversed = reversed * 10 + digit;
                temp /= 10;
            }
            if (original == reversed) palindromeCount++;
            if (palindromeCount == 2 && resultNumber != original) resultNumber = original;
        }

        double averageLength = (double) totalLength / numbers.length;
        System.out.println("Самое короткое число: " + shortestNumber + ", длина: " + String.valueOf(shortestNumber).length());
        System.out.println("Самое длинное число: " + longestNumber + ", длина: " + String.valueOf(longestNumber).length());

        bubbleSort(numbers, true);
        System.out.println("Сортировка по возрастанию длины значений:");
        for (int number : numbers) System.out.print(number + " ");
        System.out.println();

        bubbleSort(numbers, false);
        System.out.println("Сортировка по убыванию длины значений:");
        for (int number : numbers) System.out.print(number + " ");
        System.out.println();

        System.out.println("Числа, длина которых меньше средней (" + averageLength + "):");
        for (int number : numbers) {
            if (String.valueOf(number).length() < averageLength)
                System.out.println(number + " - длина: " + String.valueOf(number).length());
        }
        System.out.println("Числа, длина которых больше средней (" + averageLength + "):");
        for (int number : numbers) {
            if (String.valueOf(number).length() > averageLength)
                System.out.println(number + " - длина: " + String.valueOf(number).length());
        }
        System.out.println("Число с минимальным количеством различных цифр: " + resultNumber);
        System.out.println("Количество чисел, содержащих только четные цифры: " + evenDigitNumbersCount);
        System.out.println("Количество чисел с равным числом четных и нечетных цифр: " + equalEvenOddDigitsCount);
        System.out.println("Число, цифры в котором идут в строгом порядке возрастания: " + (increasingNumber != -1 ? increasingNumber : "Нет таких чисел"));
        System.out.println("Число, состоящее только из различных цифр: " + (uniqueDigitsNumber != -1 ? uniqueDigitsNumber : "Нет таких чисел"));
        System.out.println("Второе число-палиндром: " + (palindromeCount >= 2 ? resultNumber : "Меньше двух чисел-палиндромов в массиве."));

        if (args.length != 3) {
            System.out.println("Введите три параметра: a, b и c");
            return;
        }

        double a = Double.parseDouble(args[0]);
        double b = Double.parseDouble(args[1]);
        double c = Double.parseDouble(args[2]);

        double discriminant = b * b - 4 * a * c;

        if (discriminant > 0) {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            System.out.println("Корни уравнения: " + root1 + " и " + root2);
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            System.out.println("Корень уравнения: " + root);
        } else {
            System.out.println("Уравнение не имеет действительных корней.");
        }
    }

    private static void bubbleSort(int[] numbers, boolean ascending) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - i - 1; j++) {
                if ((ascending && String.valueOf(numbers[j]).length() > String.valueOf(numbers[j + 1]).length()) ||
                        (!ascending && String.valueOf(numbers[j]).length() < String.valueOf(numbers[j + 1]).length())) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
    }
}