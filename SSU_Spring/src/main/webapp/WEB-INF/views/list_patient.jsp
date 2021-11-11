<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
    Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Students</title>
    </head>

    <body>
        <div>
            <h1>ListPatient</h1>
        </div>

        <table>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Login</th>
                <th>Email</th>
                <th>Address</th>
            </tr>

            <c:forEach items="${list}" var="personList">
                <tr>
                    <td>${personList.id}</td>
                    <td>${personList.name}</td>
                    <td>${personList.login}</td>
                    <td>${personList.mail}</td>
                    <td>${personList.address}</td>
                </tr>
            </c:forEach>

        </table>
    </body>
</html>