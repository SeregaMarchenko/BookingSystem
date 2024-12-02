package com.example.bookingsystem.servlet.user;

import com.example.bookingsystem.model.Ticket;
import com.example.bookingsystem.model.User;
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

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private UserService userService;
    private TicketService ticketService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        ticketService = new TicketService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("user", user);

        List<Ticket> bookedTickets = null;
        try {
            bookedTickets = ticketService.getBookedTicketsByUserId(user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("tickets", bookedTickets);

        req.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        try {
            String newUsername = req.getParameter("username");
            String newPassword = req.getParameter("password");
            String newEmail = req.getParameter("email");

            if (newUsername != null && !newUsername.isEmpty()) {
                user.setUsername(newUsername);
            }
            if (newPassword != null && !newPassword.isEmpty()) {
                user.setPassword(newPassword);
            }
            if (newEmail != null && !newEmail.isEmpty()) {
                user.setEmail(newEmail);
            }

            userService.update(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("profile");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
