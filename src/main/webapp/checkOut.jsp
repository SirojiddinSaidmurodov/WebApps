<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: Sirojiddin
  Date: 19.03.2021
  Time: 12:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check out</title>
</head>
<body>
<%
    Cookie[] cookies = request.getCookies();
    Arrays.stream(cookies).forEach(cookie -> System.out.println(cookie.getName() + "  " + cookie.getValue()));
    String userName = null;
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.jsp");
    }

    for (Cookie cookie : cookies) {
        if (cookie.getName().equals("user")) {
            userName = cookie.getValue();
            session.setAttribute("user", userName);
            break;
        }
    }
%>
<h2>Hello, <%=userName %>! </h2>
<a href="logout.jsp">Logout</a>
</body>
</html>
