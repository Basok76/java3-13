package org.example.Glava13;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataInsert {
    public static void insertMovie(Connection conn, String title, String releaseDate, String country) {
        String sql = "INSERT INTO films (title, release_date, country) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setDate(2, java.sql.Date.valueOf(releaseDate)); // Проверить, что формат "yyyy-MM-dd"
            pstmt.setString(3, country);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Movie inserted successfully.");
            } else {
                System.out.println("No rows were inserted.");
            }
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void insertActor(Connection conn, String fullName, String birthDate) {
        String sql = "INSERT INTO actors (name, birth_date) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fullName);
            pstmt.setDate(2, java.sql.Date.valueOf(birthDate));
            pstmt.executeUpdate();
            System.out.println("Actor inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void linkActorToMovie(Connection conn, int movieId, int actorId) {
        String sql = "INSERT INTO film_actors (film_id, actor_id) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, movieId);
            pstmt.setInt(2, actorId);
            pstmt.executeUpdate();
            System.out.println("Actor linked to movie successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
