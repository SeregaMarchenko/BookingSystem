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

@WebServlet("/book_ticket")
public class BookTicketServlet extends HttpServlet {
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
                if (ticket != null) {
                    ticket.setUserId(userId);  // Привязка билета к пользователю
                    ticket.setBooked(true);  // Обновляем статус билета на забронированный
                    ticketService.update(ticket);
                    req.setAttribute("message", "Ticket booked successfully!");
                } else {
                    req.setAttribute("errorMessage", "Ticket not found.");
                }
            } catch (SQLException e) {
                req.setAttribute("errorMessage", "Error booking ticket: " + e.getMessage());
            }
        } else {
            req.setAttribute("errorMessage", "Invalid ticket ID or user not logged in.");
        }
        req.getRequestDispatcher("/event_details?eventId=" + req.getParameter("eventId")).forward(req, resp);
    }
}
