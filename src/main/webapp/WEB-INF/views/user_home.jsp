<!DOCTYPE html>
<html>
<head>
    <title>User Home</title>
</head>
<body>
<h1>Welcome, <%= request.getAttribute("username") %>!</h1>
<a href="profile">Profile</a>
<br>
<a href="events">Events</a>
<br>
<a href="logout">Logout</a>
</body>
</html>
