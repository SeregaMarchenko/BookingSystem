package com.example.bookingsystem.dao;

import com.example.bookingsystem.model.User;
import com.example.bookingsystem.util.DatabaseUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public void save(User user) throws SQLException {
        String query = "INSERT INTO users (username, password, email, role, is_blocked) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, hashPassword(user.getPassword()));
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole());
            statement.setBoolean(5, user.isBlocked());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(Long id) throws SQLException {
        String query = "SELECT * FROM users WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setRole(resultSet.getString("role"));
                    user.setBlocked(resultSet.getBoolean("is_blocked"));
                    return user;
                }
            }
        }
        return null;
    }

    public User findByUsername(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setEmail(resultSet.getString("email"));
                    user.setRole(resultSet.getString("role"));
                    user.setBlocked(resultSet.getBoolean("is_blocked"));
                    return user;
                }
            }
        }
        return null;
    }

    public List<User> findAll() throws SQLException {
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(resultSet.getString("role"));
                user.setBlocked(resultSet.getBoolean("is_blocked"));
                users.add(user);
            }
        }
        return users;
    }

    public void update(User user) throws SQLException {
        String query = "UPDATE users SET username = ?, password = ?, email = ?, role = ?, is_blocked = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, hashPassword(user.getPassword()));
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getRole());
            statement.setBoolean(5, user.isBlocked());
            statement.setLong(6, user.getId());
            statement.executeUpdate();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Long userId) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.executeUpdate();
        }
    }

    public void blockById(Long userId) throws SQLException {
        String sql = "UPDATE users SET is_blocked = TRUE WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.executeUpdate();
        }
    }

    public void unblockById(Long userId) throws SQLException {
        String sql = "UPDATE users SET is_blocked = FALSE WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, userId);
            stmt.executeUpdate();
        }
    }

    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
