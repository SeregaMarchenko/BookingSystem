package com.example.bookingsystem.servlet.ticket;

import com.example.bookingsystem.model.Ticket;
import com.example.bookingsystem.service.TicketService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/unbook_ticket")
public class UnbookTicketServlet extends HttpServlet {
    private TicketService ticketService;

    @Override
    public void init() throws ServletException {
        ticketService = new TicketService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ticketIdStr = req.getParameter("ticketId");
        String userIdStr = req.getParameter("userId");
        if (ticketIdStr != null && userIdStr != null && !userIdStr.isEmpty()) {
            try {
                Long ticketId = Long.parseLong(ticketIdStr);
                Long userId = Long.parseLong(userIdStr);
                Ticket ticket = ticketService.findById(ticketId);
                if (ticket != null && userId.equals(ticket.getUserId())) {
                    ticket.setUserId(null);
                    ticket.setBooked(false);
                    ticketService.update(ticket);
                    req.getSession().setAttribute("message", "Ticket unbooked successfully!");
                } else {
                    req.getSession().setAttribute("errorMessage", "Ticket not found or does not belong to the user.");
                }
            } catch (NumberFormatException e) {
                req.getSession().setAttribute("errorMessage", "Invalid ticket or user ID format.");
            } catch (SQLException e) {
                req.getSession().setAttribute("errorMessage", "Error unbooking ticket: " + e.getMessage());
            }
        } else {
            req.getSession().setAttribute("errorMessage", "Invalid ticket ID or user not logged in.");
        }
        resp.sendRedirect("profile");
    }
}
