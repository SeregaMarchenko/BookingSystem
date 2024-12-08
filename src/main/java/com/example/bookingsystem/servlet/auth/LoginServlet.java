package com.example.bookingsystem.servlet.auth;

import com.example.bookingsystem.model.User;
import com.example.bookingsystem.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);  // Перенаправление на страницу логина
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = null;
        try {
            user = userService.validateUser(username, password);
            if (user != null) {
                if (user.isBlocked()) {
                    req.setAttribute("errorMessage", "Your account is blocked. Please contact support.");
                    req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
                } else {
                    req.getSession().setAttribute("user", user);
                    if ("admin".equals(user.getRole())) {
                        resp.sendRedirect("admin");
                    } else {
                        resp.sendRedirect("user_home");
                    }
                }
            } else {
                req.setAttribute("errorMessage", "Invalid username or password.");
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            req.setAttribute("errorMessage", "Error logging in: " + e.getMessage());
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
