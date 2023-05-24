package com.aircorp.aircorp;

import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "populate", value = "/populate")
public class Populate extends HttpServlet {
    private final DatabaseConnection dbConnection = new DatabaseConnection();
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            dbConnection.populateDB();
        } catch (SQLException e) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + e.toString() + "</h1>");
            out.println("</body></html>");

            return;
        }

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Populated DB!</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}