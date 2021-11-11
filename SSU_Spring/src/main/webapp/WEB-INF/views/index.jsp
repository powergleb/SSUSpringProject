<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Home page</title>
    </head>

    <body>


        <div>
            <button onclick="location.href='add-patient'">Add</button>
        </div>
        <div>
                    <button onclick="location.href='delete-patient'">Delete</button>
                </div>
                <div>
                            <button onclick="location.href='update-patient'">Update</button>
                        </div>
                        <div>
                                    <button onclick="location.href='list-patient'">ListPatient</button>
                                </div>

                        <div><button onclick="location.href='register/patient'">Register client</button> </div>
                               <div> <button onclick="location.href='register/doctor'">Register doctor</button> </div>
                               <div> <button onclick="location.href='register/admin'">Register admin</button> </div>
                               <div> <button onclick="location.href='doctors'">Get doctors</button>    </div>
    </body>
</html>