<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${bookname}</title>
<link href="/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="main">
	<div id="titleDiv"><span id="title">${bookname}:${index}</span><span class="right"><a href="/book?bookcode=${bookcode}">Index</a>&nbsp&nbsp<a href="/home">Home</a></span></div>
	<div>${text}</div>
	<div>
		<c:choose>
			<c:when test="${left != -1}">
				<span><a href="/viewBook?bookcode=${bookcode}&index=${left}">previous:${left}</a></span>
			</c:when>
			<c:otherwise>
				<span>previous:nothing</span>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${right != -1}">
				<span class="right2"><a href="/viewBook?bookcode=${bookcode}&index=${right}">next:${right}</a></span>
			</c:when>
			<c:otherwise>
				<span class="right2"><span>next:nothing</span></span>
			</c:otherwise>
		</c:choose>
	</div>
	</div>
</body>
</html>