package com.example.bookingsystem.servlet.admin;

import com.example.bookingsystem.model.Event;
import com.example.bookingsystem.model.User;
import com.example.bookingsystem.service.EventService;
import com.example.bookingsystem.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private UserService userService;
    private EventService eventService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        eventService = new EventService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> users = userService.findAll();
            List<Event> events = eventService.findAll();
            req.setAttribute("users", users);
            req.setAttribute("events", events);
            req.getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
