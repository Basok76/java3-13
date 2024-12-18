package org.example.Glava13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class DataInsert {
    public static void insertMovie(String title, String releaseDate, String country) {
        String sql = "INSERT INTO films (title, release_date, country) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setDate(2, java.sql.Date.valueOf(releaseDate)); // Формат даты: "yyyy-MM-dd"
            pstmt.setString(3, country);
            pstmt.executeUpdate();
            System.out.println("Movie inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertActor(String fullName, String birthDate) {
        String sql = "INSERT INTO actors (name, birth_date) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fullName);
            pstmt.setDate(2, java.sql.Date.valueOf(birthDate));
            pstmt.executeUpdate();
            System.out.println("Actor inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void linkActorToMovie(int movieId, int actorId) {
        String sql = "INSERT INTO film_actors (film_id, actor_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, movieId);
            pstmt.setInt(2, actorId);
            pstmt.executeUpdate();
            System.out.println("Actor linked to movie successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}