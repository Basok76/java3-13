package org.example.Glava10.C2;
import java.io.*;

public class C2 {
    public static void main(String[] args) {
        String sourceFileName = "src/main/java/org/example/Glava10/C2/sourceCode.java";
        File newDir = new File("output");
        if (!newDir.exists()) {
            if (newDir.mkdir()) {
                System.out.println("Создана директория: " + newDir.getPath());
            } else {
                System.out.println("Не удалось создать директорию!");
                return;
            }
        }

        File outputFile = new File(newDir, "output.java");

        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replaceAll("\\bpublic\\b", "private");
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Обработанный файл записан: " + outputFile.getPath());
        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
