<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload</title>
<link href="/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="main">
	<p>Upload&nbsp&nbsp<a href="/home">Home</a></p>
	<form method="POST" enctype="multipart/form-data" action="/uploadForm">
	<table>
		<tr><th>File</th><td><input type="file" name="file" /></td></tr>
		<tr><th>Filename</th><td><input type="text" name="filename" /></td></tr>
		<tr><th></th><td><input type="submit" value="upload" /></td></tr>
	</table>
	</form>
	</div>
</body>
</html>