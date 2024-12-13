package Glava7;
import org.example.Glava7.AnagramChecker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AnagramCheckerTest {

    @Test
    public void testAreAnagrams_TrueCase() {
        String word1 = "слово";
        String word2 = "волос";
        assertTrue(AnagramChecker.areAnagrams(word1, word2), "Слова должны быть анаграммами.");
    }

    @Test
    public void testAreAnagrams_FalseCase() {
        String word1 = "слово";
        String word2 = "стол";
        assertFalse(AnagramChecker.areAnagrams(word1, word2), "Слова не должны быть анаграммами.");
    }

    @Test
    public void testAreAnagrams_EmptyStrings() {
        String word1 = "";
        String word2 = "";
        assertTrue(AnagramChecker.areAnagrams(word1, word2), "Пустые строки должны быть анаграммами.");
    }

    @Test
    public void testAreAnagrams_DifferentLengths() {
        String word1 = "слово";
        String word2 = "слов";
        assertFalse(AnagramChecker.areAnagrams(word1, word2), "Слова разной длины не могут быть анаграммами.");
    }

    @Test
    public void testAreAnagrams_SingleCharacter() {
        String word1 = "а";
        String word2 = "а";
        assertTrue(AnagramChecker.areAnagrams(word1, word2), "Одинаковые одиночные символы должны быть анаграммами.");
    }

    @Test
    public void testAreAnagrams_CaseInsensitive() {
        String word1 = "СЛОВО";
        String word2 = "слово";
        assertTrue(AnagramChecker.areAnagrams(word1, word2), "Слова должны быть анаграммами, игнорируя регистр.");
    }
}