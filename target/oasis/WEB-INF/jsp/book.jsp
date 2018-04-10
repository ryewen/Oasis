<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Home</title>
<link href="/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="main">
	<p>${bookname}&nbsp&nbsp<a href="/home">Home</a></p>
	<table>
	<c:forEach var="numRow" items="${numRows}">
		<tr>
			<c:forEach var="num" items="${numRow}">
				<td><a href="/viewBook?bookcode=${bookcode}&index=${num}">${num}</a></td>
			</c:forEach>
		</tr>
	</c:forEach>
	</table>
	</div>
</body>
</html>