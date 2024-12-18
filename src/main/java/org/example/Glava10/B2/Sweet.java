package org.example.Glava10.B2;

import java.io.Serializable;

public abstract class Sweet implements Serializable {
    protected String name;
    protected double weight;

    public Sweet(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return name + " (Вес: " + weight + " г)";
    }
}