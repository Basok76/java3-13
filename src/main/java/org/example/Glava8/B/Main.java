package org.example.Glava8.B;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String text = "Java is good language. Java is nice. Java is good.";

        TextProcessor processor = new TextProcessor(text);
        List<Sentence> sortedSentences = processor.getAllSentencesSortedByWordCount();

        System.out.println("Sentences sorted by word count:");
        for (Sentence sentence : sortedSentences) {
            System.out.println(sentence);
        }
    }
}
