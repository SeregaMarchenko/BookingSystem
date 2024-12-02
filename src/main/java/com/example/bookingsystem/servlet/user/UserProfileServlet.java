package com.example.bookingsystem.servlet.user;

import com.example.bookingsystem.model.User;
import com.example.bookingsystem.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/edit_profile")
public class UserProfileServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
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
            if (newPassword != null && !newPassword.isEmpty() && newPassword != user.getPassword()) {
                user.setPassword(newPassword);
            }
            if (newEmail != null && !newEmail.isEmpty()) {
                user.setEmail(newEmail);
            }

            userService.update(user);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("user");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
