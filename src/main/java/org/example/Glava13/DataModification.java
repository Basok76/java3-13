package org.example.Glava13;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataModification {
    public static void deleteOldFilms(Connection conn, int years) {
        String query = "DELETE FROM films WHERE release_date < CURRENT_DATE - INTERVAL '1 year' * ?;";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, years);
            int rowsDeleted = pstmt.executeUpdate();
            System.out.println("Deleted " + rowsDeleted + " old films.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
