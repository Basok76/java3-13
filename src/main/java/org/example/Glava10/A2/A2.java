package org.example.Glava10.A2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class A2 {
    public static List<String> replaceSubstringInLines(List<String> lines, String target, String replacement) {
        return lines.stream()
                .map(line -> {
                    return line.replace(target, replacement);
                })
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Usage: java PoemSubstringReplacer <inputFile> <outputFile> <targetSubstring> <replacementSubstring>");
            return;
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];
        String targetSubstring = args[2];
        String replacementSubstring = args[3];

        try {
            List<String> lines = Files.readAllLines(Paths.get(inputFilePath));
            List<String> updatedLines = replaceSubstringInLines(lines, targetSubstring, replacementSubstring);
            Files.write(Paths.get(outputFilePath), updatedLines);
            System.out.println("Substring replacement completed. Output written to: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }
}
