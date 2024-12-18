package org.example.Glava13;
import java.sql.*;

public class DataSelector {
    public static void findFilmsByYearRange(int startYear, int endYear) {
        String query = "SELECT * FROM films WHERE EXTRACT(YEAR FROM release_date) BETWEEN ? AND ?;";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, startYear);
            pstmt.setInt(2, endYear);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Фильм: " + rs.getString("title") + ", Дата Выхода: " + rs.getDate("release_date") + ", Страна: " + rs.getString("country"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getActorsByFilmTitle(String filmTitle) {
        String query = "SELECT a.name, a.birth_date " +
                "FROM actors a " +
                "JOIN film_actors fa ON a.id = fa.actor_id " +
                "JOIN films f ON fa.film_id = f.id " +
                "WHERE f.title = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, filmTitle);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Актеры в фильме: \"" + filmTitle + "\":");
                while (rs.next()) {
                    String actorName = rs.getString("name");
                    String birthDate = rs.getDate("birth_date").toString();
                    System.out.println(" - " + actorName + " (Born: " + birthDate + ")");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void getFilmsByActorName(String actorName) {
        String query = "SELECT f.title, f.release_date, f.country " +
                "FROM films f " +
                "JOIN film_actors fa ON f.id = fa.film_id " +
                "JOIN actors a ON fa.actor_id = a.id " +
                "WHERE a.name = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, actorName);

            try (ResultSet rs = pstmt.executeQuery()) {
                System.out.println("Фильмы с участием \"" + actorName + "\":");
                while (rs.next()) {
                    String filmTitle = rs.getString("title");
                    String releaseDate = rs.getDate("release_date").toString();
                    String country = rs.getString("country");
                    System.out.println(" - " + filmTitle + " (Дата выхода: " + releaseDate + ", Страна: " + country + ")");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
