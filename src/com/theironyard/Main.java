package com.theironyard;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:h2:./main"); //java.sql has everything we need to connect to the database

        Statement stmt = conn.createStatement(); //created a statement object
        stmt.execute("CREATE TABLE IF NOT EXISTS players (name VARCHAR, health DOUBLE, score INT, is_alive BOOLEAN)"); //SQL query
        stmt.execute("INSERT INTO players VALUES ('Alice', 100, 10, true)");
        stmt.execute("INSERT INTO players VALUES ('Bob', 95, 10, true)");
        stmt.execute("UPDATE players SET health = 50 WHERE name = 'Alice'");
        stmt.execute("DELETE FROM players WHERE name = 'Bob'");

        ResultSet results = stmt.executeQuery("SELECT + FROM players");
        while (results.next()) { //loop that will loop over the select results
            String name = results.getString("name"); //put in column name which is "name" //get column name health, make sure it's a double and store it in variable health
            double health = results.getDouble("health");
            int score = results.getInt("score");
            boolean isAlive = results.getBoolean("is_alive");
            System.out.println(String.format("%s %s %s %s", name, health, score, isAlive));
        }

        stmt.execute("DROP TABLE players");

        conn.close();
    }
}
