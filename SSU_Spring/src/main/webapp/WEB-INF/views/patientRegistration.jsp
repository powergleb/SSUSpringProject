<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Patient Registration</title>
</head>
<body>
<form action = "register/register-new-patient" method="POST">
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

    <button type="submit">Register</button>
</form>
</body>
</html>