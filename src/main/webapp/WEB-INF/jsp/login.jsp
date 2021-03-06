<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Login</title>
<link rel="stylesheet" href="https://unpkg.com/purecss@0.6.1/build/pure-min.css" integrity="sha384-CCTZv2q9I9m3UOxRLaJneXrrqKwUNOzZ6NGEUMwHtShDJ+nCoiXJCAgi05KfkLGY" crossorigin="anonymous">
<link href="/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="main">
	<form class="pure-form pure-form-stacked center" action="/loginForm" method="post">
    <fieldset>
        <legend>Login</legend>

        <label for="username">Username</label>
        <input id="username" type="text" name="username" placeholder="Username">

        <label for="password">Password</label>
        <input id="password" type="password" name="password" placeholder="Password">

        <button type="submit" class="pure-button pure-button-primary">Sign in</button>
    </fieldset>
	</form>
	</div>
</body>
</html>