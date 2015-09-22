<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>transition.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>
<style type="text/css">
	.box{
		width: 400px;
		height: 400px;
		padding: 20px;
		transition: transform 1s, width 1s, height 1s, background-color 2s;
		
		background: yellow; 
	}
	
	.box:hover{
		width: 600px;
		height: 600px;
		background-color: green;
		transform: rotate(180deg);
		
	}
	
</style>
</head>
<body>
<div class="box">
<h1>transition : property 1s</h1>

<div>
	box
</div>
</div>
</body>
</html>

