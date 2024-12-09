<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
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
            margin-bottom: 20px;
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
        a:last-of-type {
            margin-top: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Admin Dashboard</h1>
    <a href="manage_users">Manage Users</a>
    <a href="manage_events">Manage Events</a>
    <a href="logout">Logout</a>
</div>
</body>
</html>
