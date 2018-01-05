<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anhtuan
  Date: 15/12/2017
  Time: 02:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page: Learn Spring</title>
    <link rel="stylesheet" href="/static/css/main.css">
    <script src="/static/js/main.js"></script>
</head>
<body>
    <p id="demo">Hello World</p>
    <button type="button" onclick="myFunction()">Click here</button><br>
    <a href="<c:url value="/list"/>">List Employees of Company</a>
</body>
</html>
