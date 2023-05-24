package com.aircorp.aircorp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ThreadLocalRandom;

public class DatabaseConnection {
    private static final String DB_NAME = "AirCorpDB";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "admin";
    private static final String DB_HOST = "127.0.0.1";
    private static final int DB_PORT = 5432; // порт базы данных

    private Connection connection;

    public DatabaseConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME,
                    DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void populateDB() throws SQLException {
        Statement statement = connection.createStatement();

        statement.execute("DROP TABLE Users;");
        statement.execute("DROP TABLE Flights;");
        statement.execute("DROP TABLE BookedSeat;");
        statement.execute("CREATE TABLE Users (user_id VARCHAR(100) NOT NULL);");
        statement.execute("CREATE TABLE Flights (flight_id VARCHAR(10) NOT NULL, total_seats INT, seat_price INT, flight_date DATE);");
        statement.execute("CREATE TABLE BookedSeat (flight_id VARCHAR(10) NOT NULL, user_id VARCHAR(100) NOT NULL, purchase_date DATE);");

        for(int i = 0; i < 100; ++i) {
            String stmt = "INSERT INTO Flights (flight_id, total_seats, seat_price, flight_date) VALUES ( '" +
                    RandomUtils.randomString(2) + "-" + ThreadLocalRandom.current().nextInt(10, 200) + "', " +
                    ThreadLocalRandom.current().nextInt(100, 500) + ", " +
                    ThreadLocalRandom.current().nextInt(100, 500) + ", " +
                    "'2023-" + ThreadLocalRandom.current().nextInt(6, 12) + "-" + ThreadLocalRandom.current().nextInt(1, 28) + "');";

            statement.execute(stmt);
        }

        statement.close();
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}