package Glava8;

import org.example.Glava8.B.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class zadanieBtest {

    @Test
    public void testSymbolCreation() {
        Symbol symbol = new Symbol('A');
        assertEquals('A', symbol.getValue());
    }

    @Test
    public void testWordCreation() {
        Word word = new Word("Hello");
        assertEquals("Hello", word.toString());
    }

    @Test
    public void testSentenceCreation() {
        Sentence sentence = new Sentence("This is a test.");
        assertEquals(4, sentence.getWordCount());
        assertEquals("This is a test.", sentence.toString());
    }

    @Test
    public void testParagraphCreation() {
        Paragraph paragraph = new Paragraph("This is a test. Another sentence!");
        List<Sentence> sentences = paragraph.getSentences();
        assertEquals(2, sentences.size());
        assertEquals("This is a test.", sentences.get(0).toString());
        assertEquals("Another sentence!", sentences.get(1).toString());
    }

    @Test
    public void testTextProcessorSorting() {
        String text = """
            This is the first sentence. A short one. This is another slightly longer sentence.
            """;

        TextProcessor processor = new TextProcessor(text);
        List<Sentence> sortedSentences = processor.getAllSentencesSortedByWordCount();

        assertEquals(3, sortedSentences.size());
        assertEquals("A short one.", sortedSentences.get(0).toString());
        assertEquals("This is the first sentence.", sortedSentences.get(1).toString());
        assertEquals("This is another slightly longer sentence.", sortedSentences.get(2).toString());
    }

    @Test
    public void testTextProcessorHandlesEmptyText() {
        TextProcessor processor = new TextProcessor("");
        List<Sentence> sortedSentences = processor.getAllSentencesSortedByWordCount();
        assertTrue(sortedSentences.isEmpty());
    }
}
