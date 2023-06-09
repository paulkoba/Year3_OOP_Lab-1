package com.aircorp.aircorp;

import java.io.*;
import java.sql.*;
import java.time.Instant;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "purchaseTicket", value = "/purchaseTicket")
public class PurchaseTicket extends HttpServlet {
    DatabaseConnection dbConnection = new DatabaseConnection();
    FlightManager flightManager = new FlightManager();
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String user = request.getParameter("user_id");
        String flight = request.getParameter("flight");

        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        response.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter out = response.getWriter();

        if(user == null || flight == null) {
            out.println("Invalid user id or flight id provided.");
            return;
        }

        if(!flightManager.canPurchaseTicket(flight)) {
            out.println("No seats available");
            return;
        }

        Connection connection = dbConnection.getConnection();
        try {
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO BookedSeat (flight_id, user_id, purchase_date) VALUES (?, ?, ?)");
            stmt.setString(1, flight);
            stmt.setString(2, user);
            stmt.setDate(3, new java.sql.Date((new java.util.Date()).getTime()));

            stmt.execute();

            out.println("Success");
        } catch (SQLException e) {
            out.println("Failure: " + e);
        }
    }

    public void destroy() {
        flightManager.destroy();
        dbConnection.closeConnection();
    }
}