package org.example.Glava13;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            Vidioteka.createTables(conn);
            DataInsert.insertActor(conn, "Leonardo DiCaprio", "1974-11-11");
            DataInsert.insertActor(conn, "Keanu Reeves", "1964-09-02");

            DataInsert.linkActorToMovie(conn, 1, 1);
            DataInsert.linkActorToMovie(conn, 2, 2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
