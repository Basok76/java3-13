package Glava9;


import org.example.Glava9.InvalidNumberFormatException;
import org.example.Glava9.Main;
import org.junit.jupiter.api.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    void testReadValidNumbersFromFile() throws Exception {
        File tempFile = createTempFile("US 1234.56\nDE 7890,12\nFR 3456.78");
        Main.readNumbersFromFile(tempFile.getAbsolutePath());
    }

    @Test
    void testReadEmptyFile() throws Exception {
        File tempFile = createTempFile("");
        Main.readNumbersFromFile(tempFile.getAbsolutePath());
    }

    @Test
    void testReadInvalidFileFormat() {
        String invalidContent = "US 1234.56\nINVALID 7890.12\nFR -3456.78";
        try {
            File tempFile = createTempFile(invalidContent);
            assertThrows(InvalidNumberFormatException.class, () -> {
                Main.readNumbersFromFile(tempFile.getAbsolutePath());
            });
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    private File createTempFile(String content) throws IOException {
        File tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(content);
        }
        return tempFile;
    }
}
