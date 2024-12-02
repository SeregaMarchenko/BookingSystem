<%@ page import="com.example.bookingsystem.model.Event" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Events</title>
</head>
<body>
<h1>Manage Events</h1>
<form action="create_event" method="post">
    <label for="name">Event Name:</label>
    <input type="text" id="name" name="name" required autocomplete="off">
    <br>
    <label for="date">Event Date:</label>
    <input type="date" id="date" name="date" required>
    <br>
    <label for="location">Location:</label>
    <input type="text" id="location" name="location" required autocomplete="off">
    <br>
    <label for="ticketQuantity">Ticket Quantity:</label>
    <input type="number" id="ticketQuantity" name="ticketQuantity" required>
    <br>
    <button type="submit">Create Event</button>
</form>
<form action="admin" method="get">
    <button type="submit">Go Back</button>
</form>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage != null) {
%>
<div style="color: red;">
    <%= errorMessage %>
</div>
<%
    }
%>
<h2>Event List</h2>
<ul>
    <%
        List<Event> events = (List<Event>) request.getAttribute("events");
        if (events != null && !events.isEmpty()) {
            for (Event event : events) {
    %>
    <li>
        <%= event.getName() %> - <%= event.getDate() %> - <%= event.getLocation() %> - <%= event.getTicketQuantity() %>
        tickets available
        <form action="delete_event" method="post" style="display:inline;">
            <input type="hidden" name="id" value="<%= event.getId() %>">
            <input type="submit" value="Delete"></form>
    </li>
    <%
        }
    } else {
    %>
    <li>No events found.</li>
    <%
        }
    %>
</ul>
</body>
</html>
