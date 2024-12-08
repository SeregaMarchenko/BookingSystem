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

@WebServlet("/search")
public class EventSearchServlet extends HttpServlet {
    private EventService eventService;

    @Override
    public void init() throws ServletException {
        eventService = new EventService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        try {
            List<Event> events = eventService.findByName(query);
            req.setAttribute("events", events);
            req.getRequestDispatcher("/WEB-INF/views/events.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}