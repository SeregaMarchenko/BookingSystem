<%@ page import="com.example.bookingsystem.model.Ticket" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.bookingsystem.model.Event" %>
<%@ page import="com.example.bookingsystem.model.User" %>
<!DOCTYPE html>
<html>
<head>
  <title>User Profile</title>
</head>
<body>
<h1>User Profile</h1>
<form action="edit_profile" method="post">
  <label for="username">Username:</label>
  <input type="text" id="username" name="username" value="<%= ((User)request.getAttribute("user")).getUsername() %>" required autocomplete="off">
  <br>
  <label for="password">Password:</label>
  <input type="password" id="password" name="password" value="<%= ((User)request.getAttribute("user")).getPassword() %>" required autocomplete="off">
  <br>
  <label for="email">Email:</label>
  <input type="email" id="email" name="email" value="<%= ((User)request.getAttribute("user")).getEmail() %>" required autocomplete="off">
  <br>
  <button type="submit">Update</button>
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
<h2>Booked Tickets</h2>
<ul>
  <%
    List<Ticket> tickets = (List<Ticket>) request.getAttribute("tickets");
    if (tickets != null && !tickets.isEmpty()) {
      for (Ticket ticket : tickets) {
        Event event = (Event) request.getAttribute("event_" + ticket.getEventId());
  %>
  <li>
    Event: <%= event != null ? event.getName() : "Unknown Event" %> - Seat: <%= ticket.getSeatNumber() %> - Booked: <%= ticket.isBooked() %>
    <form action="unbook_ticket" method="post" style="display:inline;">
      <input type="hidden" name="ticketId" value="<%= ticket.getId() %>">
      <input type="hidden" name="eventId" value="<%= event != null ? event.getId() : "" %>">
      <input type="hidden" name="userId" value="<%= ((User)request.getAttribute("user")).getId() %>">
      <button type="submit">Unbook</button>
    </form>
  </li>
  <%
    }
  } else {
  %>
  <li>You have no booked tickets.</li>
  <%
    }
  %>
</ul>
</body>
</html>
