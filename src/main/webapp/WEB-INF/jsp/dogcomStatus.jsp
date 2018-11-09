<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Dogcom Status</title>
<link rel="stylesheet" href="https://unpkg.com/purecss@0.6.1/build/pure-min.css" integrity="sha384-CCTZv2q9I9m3UOxRLaJneXrrqKwUNOzZ6NGEUMwHtShDJ+nCoiXJCAgi05KfkLGY" crossorigin="anonymous">
<link href="/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div id="main">
	<div class="pure-menu custom-restricted-width">
    	<span>Dogcom Status&nbsp&nbsp<a href="/home" class="pure-button upButton">Home</a></span>

    	<ul class="pure-menu-list">
        	<li class="pure-menu-item">${status}</li>
        	<li class="pure-menu-item"><a href="/closeDogcom" class="pure-button buttonColor funcDiv">close</a></li>
    	</ul>
	</div>
	</div>
</body>
</html>