<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 14.10.2021
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Doctors</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>
<table>
    <tr>
        <th>Id</th>
        <th>Full name</th>
        <th>Phone number</th>
        <th>Email</th>
        <th>Login</th>
        <th>Password</th>
        <th>Speciality</th>
    </tr>
    <c:forEach var="doctor" items="${doctorsList}">
        <tr>
            <td>${doctor.id}</td>
            <td>${doctor.name}</td>
            <td>${doctor.secondName}</td>
            <td>${doctor.Patronymic}</td>
            <td>${doctor.phone}</td>
            <td>${doctor.mail}</td>
            <td>${doctor.login}</td>
            <td>${doctor.pass}</td>
            <td>${doctor.speciality}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
