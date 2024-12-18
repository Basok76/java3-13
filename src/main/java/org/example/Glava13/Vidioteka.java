package org.example.Glava13;
import java.sql.*;
public class Vidioteka {

    public static void createTables() {
        String createFilmsTable = "CREATE TABLE IF NOT EXISTS films (" +
                "id SERIAL PRIMARY KEY," +
                "title VARCHAR(255) NOT NULL UNIQUE," +
                "release_date DATE NOT NULL," +
                "country VARCHAR(255) NOT NULL);";

        String createActorsTable = "CREATE TABLE IF NOT EXISTS actors (" +
                "id SERIAL PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "birth_date DATE NOT NULL);";

        String createFilmActorsTable = "CREATE TABLE IF NOT EXISTS film_actors (" +
                "film_id INT REFERENCES films(id) ON DELETE CASCADE," +
                "actor_id INT REFERENCES actors(id) ON DELETE CASCADE," +
                "PRIMARY KEY (film_id, actor_id));";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createFilmsTable);
            stmt.execute(createActorsTable);
            stmt.execute(createFilmActorsTable);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
