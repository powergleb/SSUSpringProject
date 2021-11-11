<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 14.10.2021
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action = "register/register-new-doctor" method="POST">
    <label>Name:
        <input type="text" name="name"><br />
    </label>
    <label>Second name:
        <input type="text" name="secondName"><br />
    </label>
    <label>Patronymic:
            <input type="text" name="patronymic"><br />
    </label>

    <label>Phone number:
        <input type="text" name="phone"><br />
    </label>
     <label>Address:
            <input type="text" name="address"><br />
        </label>

    <label>Email:
        <input type="text" name="mail"><br />
    </label>

    <label>Login:
        <input type="text" name="login"><br />
    </label>

    <label>Password:
        <input type="password" name="pass"><br />
    </label>

    <label> Speciality
    <select name = "speciality">
        <option>THERAPIST</option>
        <option>DENTIST</option>
        <option>SURGEON</option>
    </select>
    </label>

    <button type="submit">Register</button>
</form>
</body>
</html>
