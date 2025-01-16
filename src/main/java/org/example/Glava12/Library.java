package org.example.Glava12;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Library {
    private final List<Book> books;
    private final Lock lock = new ReentrantLock();

    public Library(List<Book> books) {
        this.books = books;
    }

    public boolean borrowBook(String bookTitle) {
        lock.lock();
        try {
            for (Book book : books) {
                if (book.getTitle().equals(bookTitle) && !book.isReadingRoomOnly) {
                    books.remove(book);
                    System.out.println(Thread.currentThread().getName() + " взял книгу: " + bookTitle);
                    return true;
                } else if (book.getTitle().equals(bookTitle) && book.isReadingRoomOnly) {
                    System.out.println(Thread.currentThread().getName() + " не может взять книгу " + bookTitle + ", она доступна только в читальном зале.");
                    return false;
                }
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void returnBook(Book book) {
        lock.lock();
        try {
            books.add(book);
            System.out.println(Thread.currentThread().getName() + " вернул книгу: " + book.getTitle());
        } finally {
            lock.unlock();
        }
    }

    public boolean isBookAvailable(String bookTitle) {
        lock.lock();
        try {
            for (Book book : books) {
                if (book.getTitle().equals(bookTitle)) {
                    return !book.isReadingRoomOnly;  // Check if book is not restricted to the reading room
                }
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}
