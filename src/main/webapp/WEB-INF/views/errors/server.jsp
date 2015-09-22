<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>server.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>
</head>
<body>
	<h1>서버 장애 입니다.</h1>
	<!-- 현재 디렉터리 확인 -->
	<h1>$requestURI = ${pageContext.request.requestURI}</h1>
	<img src="http://localhost:8080/Member/img/server.jpg" alt="서버장애" />
</body>
</html>

