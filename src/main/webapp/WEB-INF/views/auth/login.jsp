<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Ocean View Resort</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
    <style>
        .err { color: #b00020; font-weight: bold; margin: 8px 0; }
        .hint { font-size: 12px; color: #666; margin-top: 10px; }
    </style>
</head>
<body>
<div class="container">
    <h2>Staff Login</h2>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <div class="err"><%= error %></div>
    <%
        }
    %>

    <form method="post" action="${pageContext.request.contextPath}/login">
        <label>Username</label>
        <input name="username" required>

        <label>Password</label>
        <input name="password" type="password" required>

        <button type="submit">Login</button>
    </form>

    <div class="hint">Default test user: <b>admin / admin123</b></div>
</div>
</body>
</html>