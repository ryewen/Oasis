<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Upload</title>
<link rel="stylesheet" href="https://unpkg.com/purecss@0.6.1/build/pure-min.css" integrity="sha384-CCTZv2q9I9m3UOxRLaJneXrrqKwUNOzZ6NGEUMwHtShDJ+nCoiXJCAgi05KfkLGY" crossorigin="anonymous">
<link href="/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="main">
	<span>Upload&nbsp&nbsp<a href="/home" class="pure-button upButton">Home</a></span>
	<form class="pure-form pure-form-stacked"  enctype="multipart/form-data" action="/uploadForm" method="post">
    <fieldset>

        <label for="file">File</label>
        <input id="file" type="file" name="file">

        <label for="novelname">Novel Name</label>
        <input id="novelname" type="text" name="filename" placeholder="Novel Name">

        <button type="submit" class="pure-button pure-button-primary">Upload</button>
    </fieldset>
	</form>
	</div>
</body>
</html>