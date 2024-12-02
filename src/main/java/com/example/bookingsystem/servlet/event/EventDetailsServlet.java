package com.example.bookingsystem.servlet.event;

import com.example.bookingsystem.model.Event;
import com.example.bookingsystem.model.Ticket;
import com.example.bookingsystem.service.EventService;
import com.example.bookingsystem.service.TicketService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/event_details")
public class EventDetailsServlet extends HttpServlet {
    private EventService eventService;
    private TicketService ticketService;

    @Override
    public void init() throws ServletException {
        eventService = new EventService();
        ticketService = new TicketService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventIdStr = req.getParameter("eventId");
        if (eventIdStr != null && !eventIdStr.isEmpty()) {
            try {
                Long eventId = Long.parseLong(eventIdStr);
                Event event = eventService.findById(eventId);
                if (event != null) {
                    req.setAttribute("event", event);

                    List<Ticket> availableTickets = ticketService.getAvailableTicketsByEventId(eventId);
                    req.setAttribute("availableTickets", availableTickets);

                    req.getRequestDispatcher("/WEB-INF/views/event_details.jsp").forward(req, resp);
                } else {
                    req.setAttribute("errorMessage", "Event not found.");
                    req.getRequestDispatcher("/WEB-INF/views/event_list.jsp").forward(req, resp);
                }
            } catch (NumberFormatException | SQLException e) {
                req.setAttribute("errorMessage", "Invalid event ID.");
                req.getRequestDispatcher("/WEB-INF/views/event_list.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMessage", "Event ID not provided.");
            req.getRequestDispatcher("/WEB-INF/views/event_list.jsp").forward(req, resp);
        }
    }
}
