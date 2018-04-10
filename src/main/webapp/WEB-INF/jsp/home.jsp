<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Oasis Home</title>
<link href="/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="main">
	<p>Hello, ${username}!</p>
	<table>
		<tr><td><div class="funcDiv"><a href="/upload" class="func">Upload txt</a></div></td></tr>
		<tr><td><div class="funcDiv"><a href="/viewAll" class="func">View Books</a></div></td></tr>
		<tr><td><div class="funcDiv"><a href="/history" class="func">View History</a></div></td></tr>
		<c:if test="${admin == 1}">
		<tr><td><div class="funcDiv"><a href="/createUser" class="func">Create User</a></div></td></tr>
		</c:if>
	</table>
	</div>
</body>
</html>