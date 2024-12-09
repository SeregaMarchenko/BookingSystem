<%@ page import="com.example.bookingsystem.model.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Users</title>
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
        input[type="password"],
        input[type="email"],
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
    <h1>Manage Users</h1>
    <form action="create_user" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required autocomplete="off">
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required autocomplete="off">
        <br>
        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required autocomplete="off">
        <br>
        <label for="role">Role:</label>
        <select id="role" name="role" required>
            <option value="user">User</option>
            <option value="admin">Admin</option>
        </select>
        <br>
        <button type="submit">Create User</button>
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
    <h2>User List</h2>
    <ul>
        <%
            List<User> users = (List<User>) request.getAttribute("users");
            if (users != null && !users.isEmpty()) {
                for (User user : users) {
        %>
        <li>
            <div>
                <%= user.getUsername() %> - <%= user.getEmail() %>
            </div>
            <div>
                <form class="inline-form" action="delete_user" method="post">
                    <input type="hidden" name="id" value="<%= user.getId() %>">
                    <button type="submit">Delete</button>
                </form>
                <form class="inline-form" action="<%= user.isBlocked() ? "unblock_user" : "block_user" %>" method="post">
                    <input type="hidden" name="id" value="<%= user.getId() %>">
                    <button type="submit"><%= user.isBlocked() ? "Unblock" : "Block" %></button>
                </form>
            </div>
        </li>
        <%
            }
        } else {
        %>
        <li>No users found.</li>
        <%
            }
        %>
    </ul>
</div>
</body>
</html>
