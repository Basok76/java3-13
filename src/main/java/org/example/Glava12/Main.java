package org.example.Glava12;

import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        List<Book> availableBooks = new ArrayList<>();
        availableBooks.add(new Book("Книга 1", false));
        availableBooks.add(new Book("Книга 2", true));
        availableBooks.add(new Book("Книга 3", false));
        availableBooks.add(new Book("Книга 4", true));

        Library library = new Library(availableBooks);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(new Reader(library, new String[]{"Книга 1", "Книга 2"}));
        executorService.submit(new Reader(library, new String[]{"Книга 3", "Книга 4"}));
        executorService.submit(new Reader(library, new String[]{"Книга 1", "Книга 4"}));

        executorService.shutdown();
    }
}
