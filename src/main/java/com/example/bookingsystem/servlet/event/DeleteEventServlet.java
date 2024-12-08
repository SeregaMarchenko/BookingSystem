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
        doDelete(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doDelete(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventIdStr = req.getParameter("id");
        if (eventIdStr != null && !eventIdStr.isEmpty()) {
            try {
                Long eventId = Long.parseLong(eventIdStr);
                eventService.delete(eventService.findById(eventId));
                req.getSession().setAttribute("message", "Event deleted successfully!");
                resp.sendRedirect("manage_events"); // Перенаправление на страницу управления событиями
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "Invalid event ID format.");
                req.getRequestDispatcher("/WEB-INF/views/manage_events.jsp").forward(req, resp);
            } catch (SQLException e) {
                req.setAttribute("errorMessage", "Error deleting event: " + e.getMessage());
                req.getRequestDispatcher("/WEB-INF/views/manage_events.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMessage", "Event ID is missing.");
            req.getRequestDispatcher("/WEB-INF/views/manage_events.jsp").forward(req, resp);
        }
    }
}
