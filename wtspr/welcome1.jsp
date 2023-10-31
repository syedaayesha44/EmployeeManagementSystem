<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Welcome</title>
  <link rel="stylesheet" href="styles.css">
</head>
<body>
  <div class="container">
    <%-- Retrieve the username from the request attribute --%>
    <% String username = (String) request.getAttribute("username"); %>
    <h1>Welcome to Our Website,</h1>
    <p>Thank you for visiting our website. We hope you enjoy your stay here.</p>
    <p>If you have any questions or need assistance, feel free to contact us.</p>
  </div>
</body>
</html>
