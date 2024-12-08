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

@WebServlet("/unblock_user")
public class UnblockUserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userIdStr = req.getParameter("id");
        if (userIdStr != null && !userIdStr.isEmpty()) {
            try {
                Long userId = Long.parseLong(userIdStr);
                User user = userService.findById(userId);
                if (user != null) {
                    userService.unblockUserById(userId);
                    req.getSession().setAttribute("message", "User unblocked successfully!");
                } else {
                    req.setAttribute("errorMessage", "User not found.");
                }
                resp.sendRedirect("manage_users");
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "Invalid user ID format.");
                req.getRequestDispatcher("/WEB-INF/views/manage_users.jsp").forward(req, resp);
            } catch (SQLException e) {
                req.setAttribute("errorMessage", "Error unblocking user: " + e.getMessage());
                req.getRequestDispatcher("/WEB-INF/views/manage_users.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMessage", "User ID is missing.");
            req.getRequestDispatcher("/WEB-INF/views/manage_users.jsp").forward(req, resp);
        }
    }
}
