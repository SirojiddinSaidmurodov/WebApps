<%@ page import="com.example.WebApps.services.PhotoServer" %>
<%@ page import="com.example.WebApps.services.UserServer" %>
<%@ page import="com.example.WebApps.domain.User" %>
<%@ page import="com.example.WebApps.domain.Photo" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Sirojiddin
  Date: 20.03.2021
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your photos</title>
</head>
<body>
<h2>Your photos</h2>
<a href="upload.jsp">Upload new photo</a>

<ul>
    <%

        PhotoServer server = new PhotoServer();
        UserServer userServer = new UserServer();
        ArrayList<Photo> photos = server.getPhotosList((User) session.getAttribute("user"));
        for (Photo photo : photos) {
            out.println("<li><a href=photo?id=" + photo.id + ">" + photo.name + "</a>  "
                    + "<button><a href=delete?id=" + photo.id + " >Delete</a></button>" + "</li>");
        }
    %>
</ul>

</body>
</html>
