package Glava11;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.Glava11.A2;
public class A2Test {
    @Test
    void testReverseNumber() {
        assertEquals(5, A2.reverseNumber(5));
        assertEquals(54321, A2.reverseNumber(12345));
        assertEquals(21, A2.reverseNumber(120));
        assertEquals(1221, A2.reverseNumber(1221));
    }
}
