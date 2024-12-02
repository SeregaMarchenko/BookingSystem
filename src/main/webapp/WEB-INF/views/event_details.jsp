<%@ page import="com.example.bookingsystem.model.Event" %>
<%@ page import="com.example.bookingsystem.model.Ticket" %>
<%@ page import="com.example.bookingsystem.model.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <title>Event Details</title>
</head>
<body>
<%
  Event event = (Event) request.getAttribute("event");
  User user = (User) session.getAttribute("user");  // Получаем пользователя из сессии
  if (event != null) {
%>
<h1>Event: <%= event.getName() %></h1>
<h2>Date: <%= event.getDate() %></h2>
<h2>Location: <%= event.getLocation() %></h2>
<h2>Available Tickets:</h2>
<ul>
  <%
    List<Ticket> availableTickets = (List<Ticket>) request.getAttribute("availableTickets");
    for (Ticket ticket : availableTickets) {
  %>
  <li>Seat Number: <%= ticket.getSeatNumber() %>
    <form action="book_ticket" method="post" style="display:inline;">
      <input type="hidden" name="ticketId" value="<%= ticket.getId() %>">
      <input type="hidden" name="eventId" value="<%= event.getId() %>">  <!-- Передаем eventId для возврата -->
      <input type="hidden" name="userId" value="<%= user != null ? user.getId() : "" %>">  <!-- Передаем userId -->
      <input type="submit" value="Book">
    </form>
  </li>
  <%
    }
  %>
</ul>
<%
} else {
%>
<p style="color:red;">Event details are not available.</p>
<%
  }
%>
</body>
</html>
