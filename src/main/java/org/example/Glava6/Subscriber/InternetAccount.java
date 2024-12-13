package org.example.Glava6.Subscriber;

public class InternetAccount extends BaseSubscriber {
    private double internetSpeed;

    public InternetAccount(String name, String email, double internetSpeed) {
        super(name, email);
        this.internetSpeed = internetSpeed;
    }

    public void upgradeInternetSpeed(double newSpeed) {
        this.internetSpeed = newSpeed;
        System.out.println("Скорость интернета повышена до: " + newSpeed + " Mbps");
    }
}