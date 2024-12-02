package com.example.bookingsystem.dao;

import com.example.bookingsystem.model.Event;
import com.example.bookingsystem.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventDao {


    public void save(Event event) throws SQLException {
        String query = "INSERT INTO events (name, date, location, ticket_quantity, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, event.getName());
            statement.setDate(2, new Date(event.getDate().getTime()));
            statement.setString(3, event.getLocation());
            statement.setInt(4, event.getTicketQuantity());
            statement.setString(5, event.getDescription());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    event.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    public Event findById(Long id) throws SQLException {
        String query = "SELECT * FROM events WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Event event = new Event();
                    event.setId(resultSet.getLong("id"));
                    event.setName(resultSet.getString("name"));
                    event.setDate(resultSet.getDate("date"));
                    event.setLocation(resultSet.getString("location"));
                    event.setTicketQuantity(resultSet.getInt("ticket_quantity"));
                    event.setDescription(resultSet.getString("description"));
                    return event;
                }
            }
        }
        return null;
    }

    public List<Event> findAll() throws SQLException {
        String query = "SELECT * FROM events";
        List<Event> events = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getLong("id"));
                event.setName(resultSet.getString("name"));
                event.setDate(resultSet.getDate("date"));
                event.setLocation(resultSet.getString("location"));
                event.setTicketQuantity(resultSet.getInt("ticket_quantity"));
                event.setDescription(resultSet.getString("description"));
                events.add(event);
            }
        }
        return events;
    }

    public void update(Event event) throws SQLException {
        String query = "UPDATE events SET name = ?, date = ?, location = ?, ticket_quantity = ?, description = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, event.getName());
            statement.setDate(2, new Date(event.getDate().getTime()));
            statement.setString(3, event.getLocation());
            statement.setInt(4, event.getTicketQuantity());
            statement.setString(5, event.getDescription());
            statement.setLong(6, event.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Event event) throws SQLException {
        String query = "DELETE FROM events WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, event.getId());
            statement.executeUpdate();
        }
    }

    public List<Event> findByName(String name) throws SQLException {
        String query = "SELECT * FROM events WHERE name LIKE ?";
        List<Event> events = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + name + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Event event = new Event();
                    event.setId(resultSet.getLong("id"));
                    event.setName(resultSet.getString("name"));
                    event.setDate(resultSet.getDate("date"));
                    event.setLocation(resultSet.getString("location"));
                    event.setTicketQuantity(resultSet.getInt("ticket_quantity"));
                    event.setDescription(resultSet.getString("description"));
                    events.add(event);
                }
            }
        }
        return events;
    }

    public List<Event> sortEvents(String sortBy) throws SQLException {
        String query = "SELECT * FROM events ORDER BY " + sortBy;
        List<Event> events = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getLong("id"));
                event.setName(resultSet.getString("name"));
                event.setDate(resultSet.getDate("date"));
                event.setLocation(resultSet.getString("location"));
                event.setTicketQuantity(resultSet.getInt("ticket_quantity"));
                event.setDescription(resultSet.getString("description"));
                events.add(event);
            }
        }
        return events;
    }
}
