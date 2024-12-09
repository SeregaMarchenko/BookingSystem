<%@ page import="com.example.bookingsystem.model.Ticket" %>
<%@ page import="com.example.bookingsystem.model.Event" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.bookingsystem.model.User" %>
<!DOCTYPE html>
<html>
<head>
  <title>User Profile</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }
    .container {
      background-color: white;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      width: 400px;
      max-width: 100%;
    }
    h1 {
      color: #333;
      text-align: center;
    }
    label {
      display: block;
      margin: 10px 0 5px;
    }
    input[type="text"],
    input[type="password"],
    input[type="email"] {
      width: 100%;
      padding: 10px;
      margin-bottom: 10px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    button {
      background-color: #4CAF50;
      color: white;
      padding: 10px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      width: 100%;
      margin-top: 10px;
    }
    button:hover {
      background-color: #45a049;
    }
    .error-message {
      color: red;
      margin-top: 10px;
      text-align: center;
    }
    ul {
      list-style-type: none;
      padding: 0;
    }
    li {
      background-color: #f9f9f9;
      margin: 10px 0;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    .unbook-form {
      display: inline;
    }
    .unbook-form button {
      background-color: #f44336;
      margin-top: 0;
      margin-left: 10px;
    }
    .unbook-form button:hover {
      background-color: #d32f2f;
    }
  </style>
</head>
<body>
<div class="container">
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
  <div class="error-message">
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
      <form class="unbook-form" action="unbook_ticket" method="post">
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
</div>
</body>
</html>
