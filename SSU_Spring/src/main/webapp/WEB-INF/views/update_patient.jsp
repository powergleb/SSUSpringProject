<html>
<title>Hospital Spring App</title>
<head>

</head>
<body>
<div align="center">
<h1 color="red">Update user form</h1>
<form action="update-patient" method="post" >
			<table>
			    <tr>
            	    <td>Enter user id</td>
            		 <td><input type="text" name="id" /></td>
            	</tr>
				<tr>
					<td>Select parameter to change</td>
					<td>
					    <select name="parameter">
					        <option value="name">name</option>
					        <option value="phone">phone</option>
					        <option value="mail">mail</option>
					        <option value="login">login</option>
					        <option value="pass">pass</option>
					    </select>

					</td>
				</tr>
				<tr>
					<td>New parameter value</td>
					<td><input type="text" name="newValue" /></td>
				</tr>
				</table>
				<hr>
			<input type="submit" value="Update"/></form>
</div>
</body>
</html>

