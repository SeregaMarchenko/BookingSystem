package com.example.bookingsystem.dao;

import com.example.bookingsystem.model.Event;
import com.example.bookingsystem.model.Ticket;
import com.example.bookingsystem.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {

    public void save(Ticket ticket) throws SQLException {
        String query = "INSERT INTO tickets (event_id, seat_number, is_booked) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, ticket.getEventId());
            statement.setInt(2, ticket.getSeatNumber());
            statement.setBoolean(3, ticket.isBooked());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    ticket.setId(generatedKeys.getLong(1));
                }
            }
        }
    }

    public Ticket findById(Long id) throws SQLException {
        String query = "SELECT * FROM tickets WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setId(resultSet.getLong("id"));
                    ticket.setEventId(resultSet.getLong("event_id"));
                    ticket.setUserId(resultSet.getLong("user_id"));
                    ticket.setSeatNumber(resultSet.getInt("seat_number"));
                    ticket.setBooked(resultSet.getBoolean("is_booked"));
                    return ticket;
                }
            }
        }
        return null;
    }

    public List<Ticket> findAll() throws SQLException {
        String query = "SELECT * FROM tickets";
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(resultSet.getLong("id"));
                ticket.setEventId(resultSet.getLong("event_id"));
                ticket.setUserId(resultSet.getLong("user_id"));
                ticket.setSeatNumber(resultSet.getInt("seat_number"));
                ticket.setBooked(resultSet.getBoolean("is_booked"));
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    public void update(Ticket ticket) throws SQLException {
        String query = "UPDATE tickets SET event_id = ?, user_id = ?, seat_number = ?, is_booked = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, ticket.getEventId());
            statement.setLong(2, ticket.getUserId());
            statement.setInt(3, ticket.getSeatNumber());
            statement.setBoolean(4, ticket.isBooked());
            statement.setLong(5, ticket.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Ticket ticket) throws SQLException {
        String query = "DELETE FROM tickets WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, ticket.getId());
            statement.executeUpdate();
        }
    }

    public List<Ticket> findByEventId(Long eventId) throws SQLException {
        String query = "SELECT * FROM tickets WHERE event_id = ?";
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, eventId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Ticket ticket = new Ticket();
                    ticket.setId(resultSet.getLong("id"));
                    ticket.setEventId(resultSet.getLong("event_id"));
                    ticket.setUserId(resultSet.getLong("user_id"));
                    ticket.setSeatNumber(resultSet.getInt("seat_number"));
                    ticket.setBooked(resultSet.getBoolean("is_booked"));
                    tickets.add(ticket);
                }
            }
        }
        return tickets;
    }

    public List<Ticket> findBookedTicketsByUserId(Long userId) {
        String sql = "SELECT * FROM tickets WHERE user_id = ? AND is_booked = TRUE";
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getLong("id"));
                ticket.setEventId(rs.getLong("event_id"));
                ticket.setUserId(rs.getLong("user_id"));
                ticket.setSeatNumber(rs.getInt("seat_number"));
                ticket.setBooked(rs.getBoolean("is_booked"));
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public Event findEventByTicketId(Long ticketId) {
        String sql = "SELECT e.* FROM events e JOIN tickets t ON e.id = t.event_id WHERE t.id = ?";
        Event event = null;
        try (Connection connection = DatabaseUtil.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, ticketId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                event = new Event();
                event.setId(rs.getLong("id"));
                event.setName(rs.getString("name"));
                event.setDate(rs.getDate("date"));
                event.setLocation(rs.getString("location"));
                event.setTicketQuantity(rs.getInt("ticket_quantity"));
                event.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    public List<Ticket> findAvailableTicketsByEventId(Long eventId) throws SQLException {
        String sql = "SELECT * FROM tickets WHERE event_id = ? AND is_booked = false";
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = DatabaseUtil.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setLong(1, eventId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setId(rs.getLong("id"));
                ticket.setEventId(rs.getLong("event_id"));
                ticket.setSeatNumber(rs.getInt("seat_number"));
                ticket.setBooked(rs.getBoolean("is_booked"));
                tickets.add(ticket);
            }
        }
        return tickets;
    }
}
