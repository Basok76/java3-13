package org.example.Glava10.B2;

public class Candy extends Sweet {
    private double sugarContent;

    public Candy(String name, double weight, double sugarContent) {
        super(name, weight);
        this.sugarContent = sugarContent;
    }

    public double getSugarContent() {
        return sugarContent;
    }

    @Override
    public String toString() {
        return super.toString() + ", Сахар: " + sugarContent + "%";
    }
}