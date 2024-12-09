<%@ page import="com.example.bookingsystem.model.Event" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Events</title>
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
        form {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin: 10px 0 5px;
        }
        input[type="text"],
        input[type="date"],
        input[type="number"],
        select {
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
        .message {
            text-align: center;
            margin-top: 10px;
        }
        .message .error {
            color: red;
        }
        .message .success {
            color: green;
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
        a {
            color: #333;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Events</h1>

    <form action="user_home" method="get">
        <button type="submit">Go Back</button>
    </form>

    <!-- Search Form -->
    <form method="get" action="event_list">
        <label for="searchQuery">Search:</label>
        <input type="text" id="searchQuery" name="searchQuery" autocomplete="off">
        <button type="submit">Search</button>
    </form>

    <!-- Sort Form -->
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
</div>
</body>
</html>
