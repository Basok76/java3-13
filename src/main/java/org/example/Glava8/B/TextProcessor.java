package org.example.Glava8.B;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextProcessor {
    private List<Paragraph> paragraphs;

    public TextProcessor(String text) {
        paragraphs = new ArrayList<>();
        if (text == null || text.trim().isEmpty()) {
            return;
        }
        String[] paragraphArray = text.trim().replaceAll("\\s+", " ").split("\\n+");
        for (String paragraph : paragraphArray) {
            paragraphs.add(new Paragraph(paragraph));
        }
    }


    public List<Sentence> getAllSentencesSortedByWordCount() {
        List<Sentence> allSentences = new ArrayList<>();
        for (Paragraph paragraph : paragraphs) {
            allSentences.addAll(paragraph.getSentences());
        }
        allSentences.sort(Comparator.comparingInt(Sentence::getWordCount));
        System.out.println("Sorted sentences:");
        for (Sentence sentence : allSentences) {
            System.out.println(sentence + " (" + sentence.getWordCount() + " words)");
        }
        return allSentences;
    }
}
