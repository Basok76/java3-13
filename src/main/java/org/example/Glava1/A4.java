package org.example.Glava1;

public class A4 {
    public static void main(String[] args) {
        String pass = "123";
            String corpass = args[0];
            if (pass.equals(corpass)) {
                System.out.println("Пароль верный.");
            } else {
                System.out.println("Пароль неверный.");
            }
    }
}
