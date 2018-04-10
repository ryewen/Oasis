<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create User</title>
<link href="/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="main">
	<p>Create User</p>
	<form action="/createUserForm" method="post">
		<table>
			<tr><th>Username</th><td><input type="text" name="username" /></td></tr>
			<tr><th>Password</th><td><input type="password" name="password" /></td></tr>
			<tr><th>Authority</th><td><select name="authority"><option value="user">User</option><option value="admin">Admin</option></select></td></tr>
			<tr><th></th><td><input type="submit" value="submit" /></td></tr>
		</table>
	</form>
	</div>
</body>
</html>