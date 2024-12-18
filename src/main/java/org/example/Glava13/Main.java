package org.example.Glava13;

public class Main {
    public static void main(String[] args) {
        Vidioteka.createTables();

        DataInsert.insertMovie("Inception", "2010-07-16", "USA");
        DataInsert.insertMovie("The Matrix", "1999-03-31", "USA");
        DataInsert.insertActor("Leonardo DiCaprio", "1974-11-11");
        DataInsert.insertActor("Keanu Reeves", "1964-09-02");
        DataInsert.linkActorToMovie(1, 1);
        DataInsert.linkActorToMovie(2, 2);

        DataSelector.findFilmsByYearRange(1980,2024);
        DataSelector.getActorsByFilmTitle("Inception");
        DataSelector.getFilmsByActorName("Leonardo DiCaprio ");
    }
}