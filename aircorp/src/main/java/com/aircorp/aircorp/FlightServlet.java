package com.aircorp.aircorp;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

@WebServlet(name = "flightServlet", value = "/flights")
public class FlightServlet extends HttpServlet {
    DatabaseConnection dbConnection = new DatabaseConnection();

    public void init() {

    }

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        try {
            // Connect to the database
            Connection connection = dbConnection.getConnection();

            // Execute a query to retrieve all credit cards
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Flights;");
            ResultSet resultSet = statement.executeQuery();

            // Loop through the result set and create credit card objects
            while (resultSet.next()) {
                String id = resultSet.getString("flight_id");
                int seats = resultSet.getInt("total_seats");
                int price = resultSet.getInt("seat_price");
                Date date = resultSet.getDate("flight_date");
                Flight flight = new Flight(id, seats, price, date);
                flights.add(flight);
            }

            // Close the database connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Flight> flights = getAllFlights();

        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();
        for(Flight flight : flights) {
            json.put(flight.getId(), new JSONObject().put("seats", flight.getSeats()).put("id", flight.getId()).put("seatPrice", flight.getSeatPrice()).put("date", flight.getDate()));
        }

        out.println(json);
    }

    public void destroy() {
        dbConnection.closeConnection();
    }
}