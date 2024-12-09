<!DOCTYPE html>
<html>
<head>
    <title>User Home</title>
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
            width: 300px;
            text-align: center;
        }
        h1 {
            color: #333;
        }
        a {
            display: block;
            margin: 10px 0;
            color: #4CAF50;
            text-decoration: none;
            padding: 10px;
            border: 1px solid #4CAF50;
            border-radius: 4px;
            transition: background-color 0.3s, color 0.3s;
        }
        a:hover {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Welcome, <%= request.getAttribute("username") %>!</h1>
    <a href="profile">Profile</a>
    <a href="events">Events</a>
    <a href="logout">Logout</a>
</div>
</body>
</html>
