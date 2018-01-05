<%--
  Created by IntelliJ IDEA.
  User: anhtuan
  Date: 17/12/2017
  Time: 02:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title>Hanoi University of Science and Technology </title>
    <style>
        tr:first-child{
            font-weight: bold;
            background: #C6C9C4;
        }
    </style>
</head>
<body>
    <h2>List Employees</h2>
    <table>
        <tr>
            <td>Name</td><td>Joining Date</td><td>Salary</td><td>SSN</td><td></td>
        </tr>
        <c:forEach items="${employees}" var="employee">
            <tr>
                <td>${employee.name}</td>
                <td>${employee.joiningDate}</td>
                <td>${employee.salary}</td>
                <td><a href="<c:url value="/edit/employee?id=${employee.id}"/>">${employee.ssn}</a></td>
                <td><a href="<c:url value="/delete/employee?id=${employee.id}"/>">delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href="<c:url value="/new"/>">Add new Employee</a>
</body>
</html>
