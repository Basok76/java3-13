package org.example.Glava6.Subscriber;

public interface Subscriber {
    void createContract();
    void openAccount(String phoneNumber);
    void editAccount(String newName, String newEmail);
    String getAccountInfo();
    double checkBalance();
    double checkTraffic();
    void viewPaymentHistory();
    void changeTariffPlan(String newTariff);
    void changeOperator(String newOperator);
    void topUpAccount(double amount);
    void closeAccount();
}