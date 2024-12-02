package com.example.bookingsystem.service;


import com.example.bookingsystem.dao.TicketDao;
import com.example.bookingsystem.model.Event;
import com.example.bookingsystem.model.Ticket;

import java.sql.SQLException;
import java.util.List;

public class TicketService {
    private TicketDao ticketDao;

    public TicketService() {
        ticketDao = new TicketDao();
    }

    public void save(Ticket ticket) throws SQLException {
        ticketDao.save(ticket);
    }

    public Ticket findById(Long id) throws SQLException {
        return ticketDao.findById(id);
    }

    public List<Ticket> findAll() throws SQLException {
        return ticketDao.findAll();
    }

    public void update(Ticket ticket) throws SQLException {
        ticketDao.update(ticket);
    }

    public void delete(Ticket ticket) throws SQLException {
        ticketDao.delete(ticket);
    }

    public List<Ticket> findByEventId(Long eventId) throws SQLException {
        return ticketDao.findByEventId(eventId);
    }

    public void bookTicket(Long ticketId) throws SQLException {
        Ticket ticket = ticketDao.findById(ticketId);
        if (ticket != null) {
            ticket.setBooked(true);
            ticketDao.update(ticket);
        }
    }

    public Event getEventByTicketId(Long ticketId) {
        return ticketDao.findEventByTicketId(ticketId);
    }

    public List<Ticket> getBookedTicketsByUserId(Long userId) throws SQLException {
        return ticketDao.findBookedTicketsByUserId(userId);
    }

    public List<Ticket> getAvailableTicketsByEventId(Long eventId) throws SQLException {
        return ticketDao.findAvailableTicketsByEventId(eventId);
    }
}
