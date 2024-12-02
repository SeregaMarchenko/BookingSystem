package com.example.bookingsystem.servlet.user;

import com.example.bookingsystem.model.Event;
import com.example.bookingsystem.model.Ticket;
import com.example.bookingsystem.model.User;
import com.example.bookingsystem.service.EventService;
import com.example.bookingsystem.service.TicketService;
import com.example.bookingsystem.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserService userService;
    private EventService eventService;
    private TicketService ticketService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        eventService = new EventService();
        ticketService = new TicketService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("user", user);

        try {
            List<Ticket> bookedTickets = ticketService.getBookedTicketsByUserId(user.getId());
            req.setAttribute("tickets", bookedTickets);

            List<Event> events = eventService.findAll();
            req.setAttribute("events", events);

            req.getRequestDispatcher("/WEB-INF/views/user_home.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
