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
        availableBooks.add(new Book("Book 1"));
        availableBooks.add(new Book("Book 2"));
        availableBooks.add(new Book("Book 3"));
        availableBooks.add(new Book("Book 4"));
        library = new Library(availableBooks);
    }

    @Test
    void testBorrowAndReturnBooks() {
        // Проверим, что книга может быть взята и возвращена
        assertTrue(library.borrowBook("Book 1"));
        assertFalse(library.borrowBook("Book 1"));

        library.returnBook(new Book("Book 1"));
        assertTrue(library.borrowBook("Book 1"));
    }

    @Test
    void testMultipleReaders() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<Void> reader1 = () -> {
            new Reader(library, new String[]{"Book 1", "Book 3"}).run();
            return null;
        };
        Callable<Void> reader2 = () -> {
            new Reader(library, new String[]{"Book 2", "Book 4"}).run();
            return null;
        };
        Callable<Void> reader3 = () -> {
            new Reader(library, new String[]{"Book 1", "Book 4"}).run();
            return null;
        };
        List<Callable<Void>> tasks = List.of(reader1, reader2, reader3);
        List<Future<Void>> results = executorService.invokeAll(tasks);
        for (Future<Void> result : results) {
            result.get();
        }
        executorService.shutdown();
        assertTrue(library.isBookAvailable("Book 1"));
        assertTrue(library.isBookAvailable("Book 2"));
        assertTrue(library.isBookAvailable("Book 3"));
        assertTrue(library.isBookAvailable("Book 4"));
    }

    @Test
    void testBookAvailability() {
        assertTrue(library.isBookAvailable("Book 1"));
        assertTrue(library.isBookAvailable("Book 2"));
        library.borrowBook("Book 1");
        assertFalse(library.isBookAvailable("Book 1"));
        library.returnBook(new Book("Book 1"));
        assertTrue(library.isBookAvailable("Book 1"));
    }
}
