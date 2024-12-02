package com.example.bookingsystem.servlet.event;

import com.example.bookingsystem.model.Event;
import com.example.bookingsystem.service.EventService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/event_list")
public class EventListServlet extends HttpServlet {
    private EventService eventService;

    @Override
    public void init() throws ServletException {
        eventService = new EventService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String sortBy = req.getParameter("sortBy");
            String searchQuery = req.getParameter("searchQuery");

            List<Event> events;
            if (searchQuery != null && !searchQuery.isEmpty()) {
                events = eventService.findByName(searchQuery);
            } else if (sortBy != null && !sortBy.isEmpty()) {
                events = eventService.sortEvents(sortBy);
            } else {
                events = eventService.findAll();
            }

            req.setAttribute("events", events);
            req.getRequestDispatcher("/WEB-INF/views/events.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
