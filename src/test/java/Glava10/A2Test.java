package Glava10;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import org.example.Glava10.A2.A2;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class A2Test {

    @Test
    public void testReplaceSubstringInLines() {
        List<String> inputLines = Arrays.asList(
                "Мороз и солнце; день чудесный!",
                "Ещё ты дремлешь, друг прелестный —",
                "Пора, красавица, проснись:"
        );
        String target = "о";
        String replacement = "О";

        // Ожидаемый результат
        List<String> expectedLines = Arrays.asList(
                "МОрОз и сОлнце; день чудесный!",
                "Ещё ты дремлешь, друг прелестный —",
                "ПОра, красавица, прОснись:"
        );

        List<String> actualLines = A2.replaceSubstringInLines(inputLines, target, replacement);
        assertEquals(expectedLines, actualLines);
    }

    @Test
    public void testReplaceSubstringNotFound() {
        List<String> inputLines = Arrays.asList(
                "Мороз и солнце; день чудесный!",
                "Ещё ты дремлешь, друг прелестный —"
        );
        String target = "зима";
        String replacement = "лето";
        List<String> expectedLines = inputLines;
        List<String> actualLines = A2.replaceSubstringInLines(inputLines, target, replacement);
        assertEquals(expectedLines, actualLines);
    }

    @Test
    public void testReplaceSubstringWithEmptyReplacement() {
        List<String> inputLines = Arrays.asList(
                "Под голубыми небесами",
                "Великолепными коврами"
        );
        String target = "и";
        String replacement = "";
        List<String> expectedLines = Arrays.asList(
                "Под голубым небесам",
                "Велколепным коврам"
        );
        List<String> actualLines = A2.replaceSubstringInLines(inputLines, target, replacement);
        assertEquals(expectedLines, actualLines);
    }
}