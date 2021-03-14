<%--
  Created by IntelliJ IDEA.
  User: Sirojiddin
  Date: 14.03.2021
  Time: 7:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<h1>Login</h1>
<form action="login" method="get">
    <p>
        <label for="login">Login:</label>
        <input id="login" name="login" type="text">
    </p>
    <p>
        <label for="password">Password:</label>
        <input id="password" name="password" type="password">
    </p>
    <p>
        <input type="submit" value="ok">
    </p>
</form>
</body>
</html>
