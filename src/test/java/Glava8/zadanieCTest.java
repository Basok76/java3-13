package Glava8;
import org.example.Glava8.C.TextEncryptor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class zadanieCTest {
    @Test
    public void testEncryptShortText() {
        String input = "Привет";
        String expectedOutput = "т ирП ве ";
        String actualOutput = TextEncryptor.encrypt(input);
        assertEquals(expectedOutput, actualOutput, "Шифрование короткого текста выполнено неверно.");
    }

    @Test
    public void testEncryptLongerText() {
        String input = "Привет Мир!";
        String expectedOutput = "  вМ теП ир! ир ";
        String actualOutput = TextEncryptor.encrypt(input);
        assertEquals(expectedOutput, actualOutput, "Шифрование длинного текста выполнено неверно.");
    }

    @Test
    public void testEncryptEmptyString() {
        String input = "";
        String expectedOutput = "";
        String actualOutput = TextEncryptor.encrypt(input);
        assertEquals(expectedOutput, actualOutput, "Шифрование пустой строки выполнено неверно.");
    }

    @Test
    public void testEncryptSingleCharacter() {
        String input = "A";
        String expectedOutput = "A";
        String actualOutput = TextEncryptor.encrypt(input);
        assertEquals(expectedOutput, actualOutput, "Шифрование одной буквы выполнено неверно.");
    }

    @Test
    public void testEncryptTextWithSpaces() {
        String input = "Hello World";
        String expectedOutput = "  loW oH rld le ";
        String actualOutput = TextEncryptor.encrypt(input);
        assertEquals(expectedOutput, actualOutput, "Шифрование текста с пробелами выполнено неверно.");
    }

    @Test
    public void testEncryptTextWithSpecialCharacters() {
        String input = "123!@#";
        String expectedOutput = "# 321 !@ ";
        String actualOutput = TextEncryptor.encrypt(input);
        assertEquals(expectedOutput, actualOutput, "Шифрование текста со специальными символами выполнено неверно.");
    }
}