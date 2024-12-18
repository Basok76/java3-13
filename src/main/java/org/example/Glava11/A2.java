package org.example.Glava11;
import java.util.Scanner;
import java.util.Stack;
public class A2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число: ");
        int number = scanner.nextInt();

        int reversedNumber = reverseNumber(number);

        System.out.println("Число в обратном порядке: " + reversedNumber);
    }

    public static int reverseNumber(int number) {
        Stack<Integer> stack = new Stack<>();
        int temp = number;

        while (temp != 0) {
            stack.push(temp % 10);
            temp /= 10;
        }

        int reversedNumber = 0;
        int multiplier = 1;

        while (!stack.isEmpty()) {
            reversedNumber += stack.pop() * multiplier;
            multiplier *= 10;
        }

        return reversedNumber;
    }
}