<%@ page import="java.util.List" %>
<%@ page import="com.example.bookingsystem.model.Event" %>
<!DOCTYPE html>
<html>
<head>
    <title>Events</title>
</head>
<body>
<h1>Events</h1>

<form action="user_home" method="get">
    <button type="submit">Go Back</button>
</form>

<!-- Форма поиска -->
<form method="get" action="event_list">
    <label for="searchQuery">Search:</label>
    <input type="text" id="searchQuery" name="searchQuery" autocomplete="off">
    <button type="submit">Search</button>
</form>

<!-- Форма сортировки -->
<form method="get" action="event_list">
    <label for="sortBy">Sort by:</label>
    <select id="sortBy" name="sortBy">
        <option value="name">Name</option>
        <option value="date">Date</option>
        <option value="location">Location</option>
        <option value="ticket_quantity">Ticket Quantity</option>
    </select>
    <button type="submit">Sort</button>
</form>

<ul>
    <%
        List<Event> events = (List<Event>) request.getAttribute("events");
        if (events != null && !events.isEmpty()) {
            for (Event event : events) {
    %>
    <li>
        <a href="event_details?eventId=<%= event.getId() %>"><%= event.getName() %></a> - <%= event.getDate() %> - <%= event.getLocation() %> - <%= event.getTicketQuantity() %> tickets available
    </li>
    <%
        }
    } else {
    %>
    <li>No events available.</li>
    <%
        }
    %>
</ul>
</body>
</html>
