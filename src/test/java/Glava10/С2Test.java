package Glava10;
import org.example.Glava10.C2.C2;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;
public class С2Test {
    @Test
    void testFileProcessing() throws IOException {
        String sourceCode = "public class TestClass {\n" +
                "    public void testMethod() {\n" +
                "        public int a = 5;\n" +
                "    }\n" +
                "}\n";

        File tempSourceFile = File.createTempFile("sourceCode", ".java");
        File tempOutputFile = new File("output/output.java"); // Путь к выходному файлу

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempSourceFile))) {
            writer.write(sourceCode);
        }

        if (tempOutputFile.exists()) {
            tempOutputFile.delete();
        }

        String[] args = {tempSourceFile.getAbsolutePath()};
        C2.main(args);
        assertTrue(tempOutputFile.exists(), "Output file should be created");
        try (BufferedReader reader = new BufferedReader(new FileReader(tempOutputFile))) {
            String line;
            StringBuilder outputContent = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                outputContent.append(line).append("\n");
            }
            assertTrue(outputContent.toString().contains("private"), "public should be replaced by private");
            assertFalse(outputContent.toString().contains("public"), "public should not remain in output");
        }
        tempSourceFile.delete();
        tempOutputFile.delete();
    }
}
