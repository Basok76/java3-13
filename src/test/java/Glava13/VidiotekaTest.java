package Glava13;

import org.example.Glava13.DataSelector;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VidiotekaTest {
    @Test
    public void testFindFilmsByYearRange() throws SQLException {

        int startYear = 1999;
        int endYear = 2010;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        DataSelector.findFilmsByYearRange(startYear, endYear);

        String output = outputStream.toString();
        assertTrue(output.contains("Inception"));
        assertTrue(output.contains("The Matrix"));
        assertTrue(output.contains("Дата Выхода"));
    }
    @Test
    public void testGetActorsByFilmTitle() throws SQLException {
        String filmTitle = "Inception";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        DataSelector.getActorsByFilmTitle(filmTitle);

        // Assert that the expected actor names are printed
        String output = outputStream.toString();
        assertTrue(output.contains("Leonardo DiCaprio"));
        assertTrue(output.contains("Born: 1974-11-11"));
    }
    @Test
    public void testGetFilmsByActorName() throws SQLException {
        String actorName = "Keanu Reeves";

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        DataSelector.getFilmsByActorName(actorName);

        String output = outputStream.toString();
        assertTrue(output.contains("The Matrix"));
        assertTrue(output.contains("Дата выхода"));
        assertTrue(output.contains("Страна"));
    }
}

