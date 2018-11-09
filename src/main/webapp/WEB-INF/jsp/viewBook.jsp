<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>${bookname}</title>
<link rel="stylesheet" href="https://unpkg.com/purecss@0.6.1/build/pure-min.css" integrity="sha384-CCTZv2q9I9m3UOxRLaJneXrrqKwUNOzZ6NGEUMwHtShDJ+nCoiXJCAgi05KfkLGY" crossorigin="anonymous">
<link href="/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="main">
	<table class="titleDiv">
	<tr>
		<td><span id="title">${bookname}:${index}</span></td>
		<td align="right"><span id="navigation"><a href="/book?bookcode=${bookcode}" class="pure-button upButton">Index</a>&nbsp&nbsp<a href="/home" class="pure-button upButton">Home</a></span></td>
	</tr>
	</table>
	<div>${text}</div>
	<table class="titleDiv">
	<tr>
		<td>
		<c:choose>
			<c:when test="${left != -1}">
				<span id="leftIndex"><a href="/viewBook?bookcode=${bookcode}&index=${left}" class="pure-button upButton">previous:${left}</a></span>
			</c:when>
			<c:otherwise>
				<span id="leftIndex">previous:nothing</span>
			</c:otherwise>
		</c:choose>
		</td>
		<td align="right">
		<c:choose>
			<c:when test="${right != -1}">
				<span id="rightIndex"><a href="/viewBook?bookcode=${bookcode}&index=${right}" class="pure-button upButton">next:${right}</a></span>
			</c:when>
			<c:otherwise>
				<span id="rightIndex"><span>next:nothing</span></span>
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
	</table>
	</div>
</body>
</html>