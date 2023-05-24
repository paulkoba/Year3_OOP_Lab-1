package com.aircorp.aircorp;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@Getter @Setter @AllArgsConstructor
class BookedFlight {
    private String flightId;
    private String date;
}

@WebServlet(name = "userInfo", value = "/userInfo")
public class UserInfo extends HttpServlet {
    private final DatabaseConnection dbConnection = new DatabaseConnection();
    public void init() {

    }

    public List<BookedFlight> getAllFlightsOfUserByID(String ID) {
        List<BookedFlight> flights = new ArrayList<>();
        try {
            // Connect to the database
            Connection connection = dbConnection.getConnection();

            // Execute a query to retrieve all credit cards
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM BookedSeat WHERE user_id = ?;");
            statement.setString(1, ID);
            ResultSet resultSet = statement.executeQuery();
            // Loop through the result set and create credit card objects
            while (resultSet.next()) {
                String id = resultSet.getString("flight_id");
                String date = resultSet.getString("purchase_date");
                flights.add(new BookedFlight(id, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.toString());
        }
        return flights;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<BookedFlight> flights = getAllFlightsOfUserByID(request.getParameter("user_id"));

        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();
        for(BookedFlight flight : flights) {
            json.put(flight.getFlightId(), new JSONObject().put("id", flight.getFlightId()).put("date", flight.getDate()));
        }

        out.println(json);
    }

    public void destroy() {
        dbConnection.closeConnection();
    }
}