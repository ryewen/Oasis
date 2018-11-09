<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>History</title>
<link rel="stylesheet" href="https://unpkg.com/purecss@0.6.1/build/pure-min.css" integrity="sha384-CCTZv2q9I9m3UOxRLaJneXrrqKwUNOzZ6NGEUMwHtShDJ+nCoiXJCAgi05KfkLGY" crossorigin="anonymous">
<link href="/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="main">
	<div class="pure-menu custom-restricted-width">
    	<span>${username}'s History&nbsp&nbsp<a href="/home" class="pure-button upButton">Home</a></span>
		<p>Amount:${amount}</p>

    	<ul class="pure-menu-list">
        	<c:forEach var="history" items="${histories}">
        		<li class="pure-menu-item"><a href="/viewBook?bookcode=${history.bookcode}&index=${history.history.lastIndex}" class="pure-button buttonColor funcDiv">${history.history.bookname}:${history.history.lastIndex}</a></li>
        	</c:forEach>
    	</ul>
	</div>
	</div>
</body>
</html>