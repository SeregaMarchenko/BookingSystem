package com.example.bookingsystem.servlet.auth;

import com.example.bookingsystem.model.User;
import com.example.bookingsystem.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        try {
            if (userService.isUsernameTaken(username)) {
                req.setAttribute("errorMessage", "Username already taken. Please choose another one.");
                req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
            } else {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setRole("user");

                userService.save(user);
                resp.sendRedirect("login");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error registering user: " + e.getMessage(), e);
        }
    }
}
