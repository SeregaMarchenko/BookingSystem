<%@ page import="com.example.bookingsystem.model.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Users</title>
</head>
<body>
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
<div style="color: red;">
    <%= errorMessage %>
</div>
<%
} else if (message != null) {
%>
<div style="color: green;">
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
        <%= user.getUsername() %> - <%= user.getEmail() %>
        <form action="delete_user" method="post" style="display:inline;">
            <input type="hidden" name="id" value="<%= user.getId() %>">
            <button type="submit">Delete</button>
        </form>
        <form action="<%= user.isBlocked() ? "unblock_user" : "block_user" %>" method="post" style="display:inline;">
            <input type="hidden" name="id" value="<%= user.getId() %>">
            <button type="submit"><%= user.isBlocked() ? "Unblock" : "Block" %></button>
        </form>
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
</body>
</html>
