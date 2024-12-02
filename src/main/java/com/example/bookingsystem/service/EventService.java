package com.example.bookingsystem.service;

import com.example.bookingsystem.dao.EventDao;
import com.example.bookingsystem.dao.TicketDao;
import com.example.bookingsystem.model.Event;
import com.example.bookingsystem.model.Ticket;

import java.sql.SQLException;
import java.util.List;

public class EventService {
    private EventDao eventDao;
    private TicketDao ticketDao;

    public EventService() {
        eventDao = new EventDao();
        ticketDao = new TicketDao();
    }

    public void save(Event event) throws SQLException {
        eventDao.save(event);
        createTicketsForEvent(event);
    }

    public Event findById(Long id) throws SQLException {
        Event event = eventDao.findById(id);
        return event;
    }

    public List<Event> findAll() throws SQLException {
        List<Event> events = eventDao.findAll();
        return events;
    }

    public void update(Event event) throws SQLException {
        eventDao.update(event);
    }

    public void delete(Event event) throws SQLException {
        eventDao.delete(event);
    }

    // Новый метод поиска по названию
    public List<Event> findByName(String name) throws SQLException {
        List<Event> events = eventDao.findByName(name);
        return events;
    }

    // Новый метод сортировки событий
    public List<Event> sortEvents(String sortBy) throws SQLException {
        List<Event> events = eventDao.sortEvents(sortBy);
        return events;
    }

    private void createTicketsForEvent(Event event) throws SQLException {
        for (int i = 1; i <= event.getTicketQuantity(); i++) {
            Ticket ticket = new Ticket();
            ticket.setEventId(event.getId());
            ticket.setSeatNumber(i);
            ticket.setBooked(false);
            ticketDao.save(ticket);
        }
    }
}
