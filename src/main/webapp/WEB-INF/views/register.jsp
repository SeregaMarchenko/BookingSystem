<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h1>Register</h1>
<form action="register" method="post">
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
    <button type="submit">Register</button>
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
</body>
</html>
