package com.example.bookingsystem.servlet.event;

import com.example.bookingsystem.service.EventService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delete_event")
public class DeleteEventServlet extends HttpServlet {
    private EventService eventService;

    @Override
    public void init() throws ServletException {
        eventService = new EventService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long eventId = Long.parseLong(req.getParameter("id"));
        handleDelete(req, resp, eventId);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long eventId = Long.parseLong(req.getParameter("id"));
        handleDelete(req, resp, eventId);
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp, Long eventId) throws ServletException, IOException {
        try {
            eventService.delete(eventService.findById(eventId));
            req.setAttribute("message", "Event deleted successfully!");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        req.getRequestDispatcher("/WEB-INF/views/event_list.jsp").forward(req, resp);
    }
}
