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

@WebServlet("/delete_user")
public class DeleteUserServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
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
        String userIdStr = req.getParameter("id");
        if (userIdStr != null && !userIdStr.isEmpty()) {
            try {
                Long userId = Long.parseLong(userIdStr);
                User user = userService.findById(userId);
                if (user != null) {
                    userService.deleteById(userId);
                    req.getSession().setAttribute("message", "User deleted successfully!");
                } else {
                    req.setAttribute("errorMessage", "User not found.");
                }
                resp.sendRedirect("manage_users"); // Перенаправление на страницу управления пользователями
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "Invalid user ID format.");
                req.getRequestDispatcher("/WEB-INF/views/manage_users.jsp").forward(req, resp);
            } catch (SQLException e) {
                req.setAttribute("errorMessage", "Error deleting user: " + e.getMessage());
                req.getRequestDispatcher("/WEB-INF/views/manage_users.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMessage", "User ID is missing.");
            req.getRequestDispatcher("/WEB-INF/views/manage_users.jsp").forward(req, resp);
        }
    }
}