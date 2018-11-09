<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Book Home</title>
<link rel="stylesheet" href="https://unpkg.com/purecss@0.6.1/build/pure-min.css" integrity="sha384-CCTZv2q9I9m3UOxRLaJneXrrqKwUNOzZ6NGEUMwHtShDJ+nCoiXJCAgi05KfkLGY" crossorigin="anonymous">
<link href="/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="main">
	<span>${bookname}&nbsp&nbsp<a href="/viewAll" class="pure-button upButton">View Books</a>&nbsp&nbsp<a href="/home" class="pure-button upButton">Home</a></span>
	<table>
	<c:forEach var="numRow" items="${numRows}">
		<tr>
			<c:forEach var="num" items="${numRow}">
				<td><div><a href="/viewBook?bookcode=${bookcode}&index=${num}" class="pure-button indexButton">${num}</a></div></td>
			</c:forEach>
		</tr>
	</c:forEach>
	</table>
	</div>
</body>
</html>