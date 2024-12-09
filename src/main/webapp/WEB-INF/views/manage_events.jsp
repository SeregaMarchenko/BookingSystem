<%@ page import="com.example.bookingsystem.model.Event" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Events</title>
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
        input[type="number"] {
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
        .inline-form {
            display: inline;
        }
        .inline-form button {
            background-color: #f44336;
            margin-top: 0;
            margin-left: 10px;
        }
        .inline-form button:hover {
            background-color: #d32f2f;
        }
    </style>
</head>
<body>
<div class="container">
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
        String message = (String) session.getAttribute("message");
        if (errorMessage != null) {
    %>
    <div class="message error">
        <%= errorMessage %>
    </div>
    <%
    } else if (message != null) {
    %>
    <div class="message success">
        <%= message %>
    </div>
    <%
            session.removeAttribute("message");
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
            <div>
                <%= event.getName() %> - <%= event.getDate() %> - <%= event.getLocation() %> - <%= event.getTicketQuantity() %> tickets available
            </div>
            <div>
                <form class="inline-form" action="delete_event" method="post">
                    <input type="hidden" name="id" value="<%= event.getId() %>">
                    <button type="submit">Delete</button>
                </form>
            </div>
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
</div>
</body>
</html>
