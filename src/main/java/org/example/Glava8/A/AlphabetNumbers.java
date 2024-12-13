package org.example.Glava8.A;

import java.util.Scanner;

public class AlphabetNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите текст: ");
        String input = scanner.nextLine();

        input = input.toLowerCase();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (isLetter(ch)) {
                System.out.print(ch + "  ");
            }
        }

        System.out.println();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (isLetter(ch)) {
                int position = getLetterPosition(ch);
                System.out.print(position + "  ");
            }
        }
    }

    public static boolean isLetter(char ch) {
        return (ch >= 'a' && ch <= 'z') ||
                (ch >= 'а' && ch <= 'я') ||
                (ch == 'ё');
    }

    public static int getLetterPosition(char ch) {
        if (ch >= 'a' && ch <= 'z') {
            return ch - 'a' + 1;
        } else if (ch >= 'а' && ch <= 'я') {
            return ch - 'а' + 1;
        } else if (ch == 'ё') {
            return 7;
        }
        return -1;
    }

}