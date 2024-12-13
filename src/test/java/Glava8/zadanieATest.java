package Glava8;

import org.example.Glava8.A.AlphabetNumbers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class zadanieATest {
    @Test
    public void testIsLetter() {
        assertTrue(AlphabetNumbers.isLetter('а'));
        assertTrue(AlphabetNumbers.isLetter('я'));
        assertTrue(AlphabetNumbers.isLetter('ё'));
        assertFalse(AlphabetNumbers.isLetter('1'));
        assertFalse(AlphabetNumbers.isLetter('.'));
        assertTrue(AlphabetNumbers.isLetter('a'));
        assertTrue(AlphabetNumbers.isLetter('z'));
        assertFalse(AlphabetNumbers.isLetter(' '));
    }

    @Test
    public void testGetLetterPosition() {
        assertEquals(1, AlphabetNumbers.getLetterPosition('a'));
        assertEquals(26, AlphabetNumbers.getLetterPosition('z'));
        assertEquals(1, AlphabetNumbers.getLetterPosition('а'));
        assertEquals(32, AlphabetNumbers.getLetterPosition('я'));
        assertEquals(7, AlphabetNumbers.getLetterPosition('ё'));
        assertEquals(-1, AlphabetNumbers.getLetterPosition('1'));
        assertEquals(-1, AlphabetNumbers.getLetterPosition('.'));
    }
}
