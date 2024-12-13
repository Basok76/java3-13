package org.example.Glava6;


import org.example.Glava6.Subscriber.BaseSubscriber;
import org.example.Glava6.Subscriber.CorporateSubscriber;
import org.example.Glava6.Subscriber.InternetAccount;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Базовый абонент ===");
        BaseSubscriber subscriber = new BaseSubscriber("John Doe", "john.doe@example.com");
        subscriber.createContract();
        subscriber.openAccount("+1234567890");
        subscriber.topUpAccount(100);
        subscriber.editAccount("Johnathan Doe", "johnathan.doe@example.com");
        subscriber.changeTariffPlan("Premium");
        subscriber.changeOperator("NewOperator");
        System.out.println("Баланс: " + subscriber.checkBalance() + " руб.");
        System.out.println("Остаток трафика: " + subscriber.checkTraffic() + " ГБ");
        subscriber.topUpAccount(50);
        subscriber.viewPaymentHistory();
        subscriber.closeAccount();
        System.out.println();

        System.out.println("=== Учетная запись домашнего интернета ===");
        InternetAccount internetSubscriber = new InternetAccount("Alice Smith", "alice.smith@example.com", 100);
        internetSubscriber.createContract();
        internetSubscriber.openAccount("+9876543210");
        internetSubscriber.topUpAccount(200);
        internetSubscriber.changeTariffPlan("SuperFast");
        internetSubscriber.upgradeInternetSpeed(500);
        System.out.println("Баланс: " + internetSubscriber.checkBalance() + " руб.");
        System.out.println("Остаток трафика: " + internetSubscriber.checkTraffic() + " ГБ");
        internetSubscriber.viewPaymentHistory();
        internetSubscriber.closeAccount();
        System.out.println();

        System.out.println("=== Корпоративный абонент ===");
        CorporateSubscriber corporateSubscriber = new CorporateSubscriber("Bob Manager", "bob.manager@company.com", "Tech Corp");
        corporateSubscriber.createContract();
        corporateSubscriber.openAccount("+1122334455");
        corporateSubscriber.topUpAccount(500);
        corporateSubscriber.editAccount("Robert Manager", "robert.manager@newcompany.com");
        corporateSubscriber.setCompanyName("New Tech Corp");
        corporateSubscriber.changeTariffPlan("Enterprise");
        System.out.println("Баланс: " + corporateSubscriber.checkBalance() + " руб.");
        System.out.println("Остаток трафика: " + corporateSubscriber.checkTraffic() + " ГБ");
        corporateSubscriber.viewPaymentHistory();
        corporateSubscriber.closeAccount();
        System.out.println();
    }
}
