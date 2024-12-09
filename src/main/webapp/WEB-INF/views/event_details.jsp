<%@ page import="com.example.bookingsystem.model.Event" %>
<%@ page import="com.example.bookingsystem.model.Ticket" %>
<%@ page import="com.example.bookingsystem.model.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
  <title>Event Details</title>
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
    h1, h2 {
      color: #333;
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
      display: flex;
      justify-content: space-between;
      align-items: center;
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
  </style>
</head>
<body>
<div class="container">
  <%
    Event event = (Event) request.getAttribute("event");
    User user = (User) session.getAttribute("user"); // Получаем пользователя из сессии
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
    <li>
      Seat Number: <%= ticket.getSeatNumber() %>
      <form action="book_ticket" method="post" style="display:inline;">
        <input type="hidden" name="ticketId" value="<%= ticket.getId() %>">
        <input type="hidden" name="eventId" value="<%= event.getId() %>">
        <input type="hidden" name="userId" value="<%= user != null ? user.getId() : "" %>">
        <button type="submit">Book</button>
      </form>
    </li>
    <%
      }
    %>
  </ul>
  <%
  } else {
  %>
  <p class="error-message">Event details are not available.</p>
  <%
    }
  %>
  <form action="event_list" method="get">
    <button type="submit">Go Back</button>
  </form>
</div>
</body>
</html>
