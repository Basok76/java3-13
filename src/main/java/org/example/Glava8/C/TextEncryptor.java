package org.example.Glava8.C;

import java.util.ArrayList;
import java.util.List;

public class TextEncryptor {
    public static String encrypt(String text) {
        int n = (int) Math.ceil(Math.sqrt(text.length()));
        char[][] table = new char[n][n];

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (index < text.length()) {
                    table[i][j] = text.charAt(index++);
                } else {
                    table[i][j] = ' ';
                }
            }
        }

        char[][] rotatedTable = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotatedTable[j][n - 1 - i] = table[i][j];
            }
        }

        for (int i = 0; i < n / 2; i++) {
            char[] temp = rotatedTable[i];
            rotatedTable[i] = rotatedTable[n - 1 - i];
            rotatedTable[n - 1 - i] = temp;
        }

        for (int j = 0; j < n; j += 2) {
            for (int i = 0; i < n; i++) {
                if (j + 1 < n) {
                    char temp = rotatedTable[i][j];
                    rotatedTable[i][j] = rotatedTable[i][j + 1];
                    rotatedTable[i][j + 1] = temp;
                }
            }
        }

        StringBuilder encryptedText = new StringBuilder();
        int top = 0, bottom = n - 1, left = 0, right = n - 1;

        while (top <= bottom && left <= right) {
            for (int j = left; j <= right; j++) {
                encryptedText.append(rotatedTable[top][j]);
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                encryptedText.append(rotatedTable[i][right]);
            }
            right--;
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    encryptedText.append(rotatedTable[bottom][j]);
                }
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    encryptedText.append(rotatedTable[i][left]);
                }
                left++;
            }
        }

        return encryptedText.toString();
    }

    public static void main(String[] args) {
        String text = "123!@#";
        String encrypted = encrypt(text);
        System.out.println("Исходный текст: " + text);
        System.out.println("Зашифрованный текст:" + encrypted);
    }
}
