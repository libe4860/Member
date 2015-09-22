<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>unit.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>
<style type="text/css">
	.box {
		margin: 1pt;
		background: red;
		height: 50pt;
	}
</style>
</head>
<body>
<h1>unit</h1>
<h2>
%  :							<br>							
px :							<br>
inch : 1 inch = 2.54cm = 25.4mm <br>
cm :							<br>
mm : 							<br>
pt : 1 pt = 1 / 72 inch			<br>
pc : 1 pc = 12pt				<br>
em : 1 em = 현재폰트 1배  			<br>
ex : 1 ex = 현재폰트 1/2배			<br>
</h2>

<div class="box" style="width:1inch">box</div>
<div class="box">box</div>
<div class="box">box</div>
<div class="box">box</div>

<p style="font-size:1em;">box em</p>
<p style="font-size:1ex;">box ex</p>
<p style="font-size:1in;">box in</p>
<p style="font-size:1cm;">box cm</p>
<p style="font-size:1mm;">box mm</p>
<p style="font-size:1pt;">box pt</p>
<p style="font-size:1pc;">box pc</p>
<p style="font-size:1%;">box %</p>

</body>
</html>

