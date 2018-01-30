<%--
  Created by IntelliJ IDEA.
  User: anhtuan
  Date: 21/01/2018
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login page</title>
    <link href="/static/css/main.css" rel="stylesheet">
    <script src="/static/js/main.js"></script>
</head>
<body>
<c:url var="loginUrl" value="/login"/>
<form action="${loginUrl}" method="post">
    <input type="text" id="username" name="username" placeholder="Enter SSO Id"><br>
    <input type="password" id="password" name="password" placeholder="Enter password"><br>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit" value="Log in">
</form>
</body>
</html>
