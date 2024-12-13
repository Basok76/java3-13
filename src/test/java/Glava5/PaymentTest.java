package Glava5;

import org.example.Glava5.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment();
    }

    @Test
    void testAddItem() {
        payment.addItem("Книга", 500.0, 2);
        payment.addItem("Ручка", 50.0, 5);
        double expectedTotal = 1250.0;
        assertEquals(expectedTotal, payment.calculateTotal(), 0.01, "Общая сумма должна быть корректной.");
    }


    @Test
    void testCalculateTotal() {
        payment.addItem("Книга", 500.0, 2);
        payment.addItem("Ручка", 50.0, 5);
        payment.addItem("Ноутбук", 50000.0, 1);

        double expectedTotal = 2 * 500.0 + 5 * 50.0 + 1 * 50000.0;
        assertEquals(expectedTotal, payment.calculateTotal(), 0.01, "Общая сумма должна быть правильной.");
    }

    @Test
    void testRemoveItem() {
        payment.addItem("Книга", 500.0, 2);
        payment.addItem("Ручка", 50.0, 5);

        payment.removeItem("Ручка");

        double expectedTotal = 500.0 * 2;
        assertEquals(expectedTotal, payment.calculateTotal(), 0.01, "Сумма должна быть после удаления корректной.");
    }

    @Test
    void testRemoveNonExistentItem() {
        payment.addItem("Книга", 500.0, 2);
        payment.removeItem("Ноутбук");

        double expectedTotal = 500.0 * 2;
        assertEquals(expectedTotal, payment.calculateTotal(), 0.01, "Сумма не должна измениться после удаления отсутствующего товара.");
    }

    @Test
    void testEmptyCart() {
        assertEquals(0, payment.calculateTotal(), "Сумма в пустой корзине должна быть 0.");
    }

    @Test
    void testShowItemsEmptyCart() {
        assertDoesNotThrow(() -> payment.showItems(), "Метод showItems должен корректно работать для пустой корзины.");
    }
}
