package org.example.Glava6.Subscriber;

public class CorporateSubscriber extends BaseSubscriber {
    private String companyName;

    public CorporateSubscriber(String name, String email, String companyName) {
        super(name, email);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
        System.out.println("Название компании обновлено до: " + companyName);
    }
}