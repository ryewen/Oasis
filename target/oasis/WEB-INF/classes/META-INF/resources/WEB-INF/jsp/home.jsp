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
		<tr><td><a href="/upload"><button>Upload txt</button></a></td></tr>
		<tr><td><a href="/viewAll"><button>View Books</button></a></td></tr>
		<tr><td><a href="/history"><button>View History</button></a></td></tr>
		<c:if test="${admin == 1}">
		<tr><td><a href="/createUser"><button>Create User</button></a></td></tr>
		</c:if>
	</table>
	</div>
</body>
</html>