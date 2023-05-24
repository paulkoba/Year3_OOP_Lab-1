package com.aircorp.aircorp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class FlightManager {
    DatabaseConnection dbConnection = new DatabaseConnection();

    public Flight getFlightByID(String id) {
        try {
            // Connect to the database
            Connection connection = dbConnection.getConnection();

            // Execute a query to retrieve all credit cards
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Flights WHERE flight_id=?;");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            int seats = resultSet.getInt("total_seats");
            int price = resultSet.getInt("seat_price");
            Date date = resultSet.getDate("flight_date");
            return new Flight(id, seats, price, date);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't find flight with id " + id + ". " + e);
        }
    }

    public int getNumberOfSeatsBooked(String id) {
        try {
            // Connect to the database
            Connection connection = dbConnection.getConnection();

            // Execute a query to retrieve all credit cards
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM BookedSeat WHERE flight_id=?;");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Couldn't find flight with id " + id + ". " + e);
        }
    }

    public Boolean canPurchaseTicket(String id) {
        Flight flight = getFlightByID(id);
        int booked = getNumberOfSeatsBooked(id);

        return booked < flight.getSeats();
    }

    public void destroy() {
        dbConnection.closeConnection();
    }
}