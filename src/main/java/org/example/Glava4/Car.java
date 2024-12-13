package org.example.Glava4;

import java.util.Objects;

class Kolesso {
    private String marka;

    public Kolesso(String marka) {
        this.marka = marka;
    }

    public String getMarka() {
        return marka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Kolesso kolesso = (Kolesso) o;
        return Objects.equals(marka, kolesso.marka);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marka);
    }

    @Override
    public String toString() {
        return "Колесо{" +
                "marka='" + marka + '\'' +
                '}';
    }
}

class Dvigatel {
    private String model;

    public Dvigatel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dvigatel dvigatel = (Dvigatel) o;
        return Objects.equals(model, dvigatel.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model);
    }

    @Override
    public String toString() {
        return "Двигатель{" +
                "model='" + model + '\'' +
                '}';
    }
}

class Avtomobil {
    private String marka;
    private Kolesso kolesso;
    private Dvigatel dvigatel;

    public Avtomobil(String marka, Kolesso kolesso, Dvigatel dvigatel) {
        this.marka = marka;
        this.kolesso = kolesso;
        this.dvigatel = dvigatel;
    }

    public void exat() {
        System.out.println("Автомобиль едет...");
    }

    public void zapravlyatsya() {
        System.out.println("Автомобиль заправляется...");
    }

    public void menyatKolesso() {
        System.out.println("Меняем колесо...");
    }

    public void vyvestiMarku() {
        System.out.println("Марка автомобиля: " + marka);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Avtomobil avtomobil = (Avtomobil) o;
        return Objects.equals(marka, avtomobil.marka) &&
                Objects.equals(kolesso, avtomobil.kolesso) &&
                Objects.equals(dvigatel, avtomobil.dvigatel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(marka, kolesso, dvigatel);
    }

    @Override
    public String toString() {
        return "Автомобиль{" +
                "marka='" + marka + '\'' +
                ", kolesso=" + kolesso +
                ", dvigatel=" + dvigatel +
                '}';
    }
}

public class Car {
    public static void main(String[] args) {
        Kolesso kolesso = new Kolesso("Michelin");
        Dvigatel dvigatel = new Dvigatel("V8");
        Avtomobil avtomobil = new Avtomobil("Toyota", kolesso, dvigatel);

        avtomobil.vyvestiMarku();
        avtomobil.exat();
        avtomobil.zapravlyatsya();
        avtomobil.menyatKolesso();

        System.out.println(avtomobil);
    }
}

