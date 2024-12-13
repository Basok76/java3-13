package org.example.Glava6.Subscriber;

import java.util.ArrayList;
import java.util.List;

public class BaseSubscriber implements Subscriber {
    private String name;
    private String email;
    private String phoneNumber;
    private String tariffPlan;
    private String operator;
    private double balance;
    private double traffic;
    private List<String> paymentHistory;

    public BaseSubscriber(String name, String email) {
        this.name = name;
        this.email = email;
        this.balance = 0.0;
        this.traffic = 0.0;
        this.paymentHistory = new ArrayList<>();
    }

    @Override
    public void createContract() {
        System.out.println("Контракт, созданн для: " + name);
    }

    @Override
    public void openAccount(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        System.out.println("Счет, открыт с указанием номера телефона: " + phoneNumber);
    }

    @Override
    public void editAccount(String newName, String newEmail) {
        this.name = newName;
        this.email = newEmail;
        System.out.println("Аккаунт обнавлен: " + name + ", " + email);
    }

    @Override
    public String getAccountInfo() {
        return "Имя: " + name + ", Почта: " + email + ", Номер телефона: " + phoneNumber + ", Баланс: " + balance + ", Трафик: " + traffic + "GB";
    }

    @Override
    public double checkBalance() {
        return balance;
    }

    @Override
    public double checkTraffic() {
        return traffic;
    }

    @Override
    public void viewPaymentHistory() {
        System.out.println("История платежей:");
        for (String payment : paymentHistory) {
            System.out.println(payment);
        }
    }

    @Override
    public void changeTariffPlan(String newTariff) {
        this.tariffPlan = newTariff;
        System.out.println("Тарифный план изменен на: " + newTariff);
    }

    @Override
    public void changeOperator(String newOperator) {
        this.operator = newOperator;
        System.out.println("Оператор сменился на: " + newOperator);
    }

    @Override
    public void topUpAccount(double amount) {
        this.balance += amount;
        paymentHistory.add("Top-up: " + amount);
        System.out.println("Счет пополнен: " + amount);
    }

    @Override
    public void closeAccount() {
        System.out.println("Учетная запись с номером телефона " + phoneNumber + " была закрыта.");
        this.phoneNumber = null;
    }
}