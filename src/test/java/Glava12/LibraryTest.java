package Glava12;
import static org.junit.jupiter.api.Assertions.*;

import org.example.Glava12.Book;
import org.example.Glava12.Library;
import org.example.Glava12.Reader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.*;

public class LibraryTest {

    private Library library;
    private List<Book> availableBooks;

    @BeforeEach
    void setUp() {
        availableBooks = new ArrayList<>();
        availableBooks.add(new Book("Книга 1", false));  // Can be borrowed
        availableBooks.add(new Book("Книга 2", true));   // Reading Room Only
        availableBooks.add(new Book("Книга 3", false));  // Can be borrowed
        availableBooks.add(new Book("Книга 4", true));   // Reading Room Only
        library = new Library(availableBooks);
    }

    @Test
    void testBorrowAndReturnBooks() {
        // Test that a book can be borrowed and returned
        assertTrue(library.borrowBook("Книга 1"));
        assertFalse(library.borrowBook("Книга 1"));

        library.returnBook(new Book("Книга 1", false));
        assertTrue(library.borrowBook("Книга 1"));
    }

    @Test
    void testBorrowingRestrictedBook() {
        assertFalse(library.borrowBook("Книга 2"));
        assertFalse(library.borrowBook("Книга 4"));
    }

    @Test
    void testMultipleReaders() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<Void> reader1 = () -> {
            new Reader(library, new String[]{"Book 1", "Book 3"}).run();
            return null;
        };
        Callable<Void> reader2 = () -> {
            new Reader(library, new String[]{"Книга 2", "Книга 4"}).run();
            return null;
        };
        Callable<Void> reader3 = () -> {
            new Reader(library, new String[]{"Книга 1", "Книга 4"}).run();
            return null;
        };

        List<Callable<Void>> tasks = List.of(reader1, reader2, reader3);
        List<Future<Void>> results = executorService.invokeAll(tasks);
        for (Future<Void> result : results) {
            result.get();
        }

        executorService.shutdown();
        assertTrue(library.isBookAvailable("Книга 1"));
        assertTrue(library.isBookAvailable("Книга 3"));
        assertTrue(library.isBookAvailable("Книга 1"));
        assertTrue(library.isBookAvailable("Книга 3"));
    }

    @Test
    void testBookAvailability() {
        assertTrue(library.isBookAvailable("Книга 1"));
        assertFalse(library.isBookAvailable("Книга 2"));
        library.borrowBook("Книга 1");
        assertFalse(library.isBookAvailable("Книга 1"));
        library.returnBook(new Book("Книга 1", false));
        assertTrue(library.isBookAvailable("Книга 1"));
        assertFalse(library.isBookAvailable("Книга 2"));
    }
}
