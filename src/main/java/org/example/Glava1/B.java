package org.example.Glava1;

import java.util.*;

public class B {
    public static void main(String[] args) {
        int[] b = new int[10];
        System.out.print("Введите числа:");
        Scanner scan = new Scanner(System.in);
        for (int i = 0;i<10;i++){
            int a = scan.nextInt();
            b[i] = a;
        }

        System.out.println("B1:Четные и нечетные числа");
        B1(b);
        System.out.println("B2:Наибольшее и наименьшее число");
        B2(b);
        System.out.println("B3:Числа, которые делятся на 3 или на 9");
        B3(b);
        System.out.println("B4:Числа, которые деклятся на 5 и на 7");
        B4(b);
        System.out.println("B5:Все трехзанчные числа, в десятичной записи которых нет одинаковых цифр");
        B5(b);
        System.out.println("B6:Простые числа");
        B6(b);
        System.out.println("B7: Отсортированные числа в порядке возрастания и убывания");
        B7(b);
        System.out.println("B8: Числа в порядке убывания частоты встречаемости чисел");
        B8(b);
        System.out.println("B9: «Счастливые» числа");
        B9(b);
        System.out.println("B10: Числа-палиндромы, значения которых в прямом и обратном порядке совпадают");
        B10(b);
        System.out.println("B11: Элементы, которые равны полусумме соседних элементов");
        B11(b);

    }

    public static void B1(int[] b) {
        for (int i = 0; i < 10; i++) {
            if (b[i] % 2 == 0) {
                System.out.println("Четное число:" + b[i] + " ");
            } else {
                System.out.print("Нечетное число:" + b[i] + " ");
            }
        }
        System.out.println();
    }

    public static void B2(int[] b){
        int max = Arrays.stream(b).max().getAsInt();
        int min = Arrays.stream(b).min().getAsInt();
        System.out.println("Наибольшее:"+max+" "+"Минимальное:"+min);
        System.out.println();
    }

    public static void B3(int[] b) {
        for(int i = 0 ;i<10;i++){
            if (b[i] %3 ==0 || b[i]%9==0) {
                System.out.print(b[i]+" ");
            }
        }
        System.out.println();
    }

    public static void B4(int[] b){
        for(int i = 0 ;i<10;i++){
            if (b[i] %5 ==0 && b[i]%7==0) {
                System.out.print(b[i]+" ");
            }
        }
        System.out.println();
    }

    public static void B5(int[] b){
        for (int i = 0; i < 10; i++) {
            if ((b[i] >= 100 && b[i] <= 999) && (b[i] / 100 != (b[i] / 10) % 10 && (b[i] / 10) % 10 != b[i] % 10 && b[i] % 10 != b[i] / 100))
            {
                System.out.println(b[i]);
            }
        }
        System.out.println();
    }

    public static void B6(int[] b) {
        for(int i = 0;i<10;i++){
            if(isPrime(b[i])){
                System.out.println(b[i]);
            }
        }
        System.out.println();
    }
    public static boolean isPrime(int i){
        if (i < 2) return false;
        for (int j = 2; j <= Math.sqrt(i); j++) {
            if (i % j == 0) return false;
        }
        return true;
    }

    public static void B7(int[] b) {
        Arrays.sort(b);
        for (int i : b){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int i =b.length-1;i>=0;i--){
            System.out.print(b[i]+" ");
        }
        System.out.println();
    }

    public static void B8(int[] b) {
        boolean[] visited = new boolean[b.length];
        int[] values = new int[b.length];
        int[] frequencies = new int[b.length];
        int uniqueCount = 0;

        for (int i = 0; i < b.length; i++) {
            if (!visited[i]) {
                int count = 1;
                for (int j = i + 1; j < b.length; j++) {
                    if (b[i] == b[j]) {
                        visited[j] = true;
                        count++;
                    }
                }
                values[uniqueCount] = b[i];
                frequencies[uniqueCount] = count;
                uniqueCount++;
            }
        }

        for (int i = 0; i < uniqueCount - 1; i++) {
            for (int j = i + 1; j < uniqueCount; j++) {
                if (frequencies[i] < frequencies[j]) {
                    int tempFreq = frequencies[i];
                    frequencies[i] = frequencies[j];
                    frequencies[j] = tempFreq;

                    int tempVal = values[i];
                    values[i] = values[j];
                    values[j] = tempVal;
                }
            }
        }

        System.out.println("Частота встречаемости чисел в порядке убывания:");
        for (int i = 0; i < uniqueCount; i++) {
            System.out.println(values[i] + ": " + frequencies[i] + " раз(а)");
        }
    }



    public static void B9(int[] b) {
        for (int value : b) {
            if (isLucky(value)) {
                System.out.println(value);
            }
        }
        System.out.println();
    }

    public static boolean isLucky(int num) {
        String s = Integer.toString(Math.abs(num));
        int half = s.length() / 2;
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < half; i++) {
            sum1 += s.charAt(i) - '0';
            sum2 += s.charAt(s.length() - 1 - i) - '0';
        }
        return sum1 == sum2;
    }

    public static void B10(int[] b) {
        for (int value : b) {
            if (isPalindrome(value)) {
                System.out.println(value);
            }
        }
        System.out.println();
    }

    public static boolean isPalindrome(int num) {
        String s = Integer.toString(num);
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void B11(int[] b) {
        for (int i = 1; i < b.length - 1; i++) {
            if (b[i] == (b[i - 1] + b[i + 1]) / 2) {
                System.out.println(b[i]);
            }
        }
    }
}
