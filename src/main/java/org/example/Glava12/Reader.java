package org.example.Glava12;

public class Reader implements Runnable {
    private final Library library;
    private final String[] booksToBorrow;

    public Reader(Library library, String[] booksToBorrow) {
        this.library = library;
        this.booksToBorrow = booksToBorrow;
    }

    @Override
    public void run() {
        for (String bookTitle : booksToBorrow) {
            if (library.borrowBook(bookTitle)) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                library.returnBook(new Book(bookTitle));
            } else {
                System.out.println(Thread.currentThread().getName() + " не может взять книгу: " + bookTitle);
            }
        }
    }
}
