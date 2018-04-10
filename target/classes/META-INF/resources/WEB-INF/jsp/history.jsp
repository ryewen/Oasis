<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>History</title>
<link href="/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="main">
	<p>${username}'s History&nbsp&nbsp<a href="/home">Home</a></p>
	<p>Amount:${amount}</p>
	<table>
	<c:forEach var="history" items="${histories}">
		<tr><td><div class="bookDiv"><a href="/viewBook?bookcode=${history.bookcode}&index=${history.history.lastIndex}" class="func">${history.history.bookname}:${history.history.lastIndex}</a></div></td></tr>
	</c:forEach>
	</table>
	</div>
</body>
</html>