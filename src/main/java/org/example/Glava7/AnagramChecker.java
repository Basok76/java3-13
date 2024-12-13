package org.example.Glava7;

import java.util.function.Function;
import java.util.Arrays;

public class AnagramChecker {

    public static void main(String[] args) {
        String word1 = "слово";
        String word2 = "волос";

        boolean areAnagrams = areAnagrams(word1, word2);
        if (areAnagrams) {
            System.out.println("Слова являются анаграммой ");
        } else {
            System.out.println("Слова не являются анаграммой");
        }
    }

    public static boolean areAnagrams(String word1, String word2) {
        Function<String, String> sortLetters = word -> {
            word = word.toLowerCase();
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            return new String(chars);
        };

        return sortLetters.apply(word1).equals(sortLetters.apply(word2));
    }
}