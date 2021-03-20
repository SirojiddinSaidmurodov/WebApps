<%--
  Created by IntelliJ IDEA.
  User: Sirojiddin
  Date: 21.03.2021
  Time: 0:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload a photo</title>
</head>
<body>
<form method="post" action="photo" enctype="multipart/form-data">
    <input type="file" name="photo" id="photo" alt="your photo" accept="image/*">
    <input type="submit" value="send!">
</form>
</body>
</html>
