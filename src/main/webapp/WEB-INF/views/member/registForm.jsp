<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>registForm.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>
<style type="text/css">
	.center { width: 400px; border-bottom: 3px solid #000; margin: 10px auto; padding-bottom: 10px; text-align: center; }
	.form { width: 400px; margin: 0 auto; padding: 20px; background: #777; border-radius: 15px;}
	.item { font-weight: bold; color: orange}
	label { color: #fff}
	input[type=submit] { display: block; height: 30px; margin: 0 auto; background: #333; border:1px solid #333; color: #fff; font-weight: bold; }
	.none { display: none; }
	.block { display: block; }
	label[for^=gender] { margin-left: 5px; margin-right: 10px; color: #fff}
	label[for^=hobby] { margin-left: 5px; margin-right: 10px; color: #fff}
	input[type=checkbox] { margin-top: -4px; vertical-align: middle }
/* 	label[for$=der1] : der1으로 끝나는 것 */
/* 	label[for^=gender] : gender로 시작하는것   */
/* 	label[for*=gender] : gender가 들어가있는 모든 것   */	
</style>
<script type="text/javascript">
	<%-- .ready : 준비 핸들러 / 셀렉터 : 객체를 찾는 것 --%> 
	$(document).ready(function(){
		
		$('form').slideToggle(1000).slideToggle(1000)
				 .fadeOut(500).fadeIn(1000, function(){ //form완료 후 label처리 
					$('label[for^=gender]').fadeOut(500).fadeIn(1000);
				 });
	});
	
</script>
</head>
<body>
	<h1 class="center"><a href="regist"><spring:message code="member.regist.title" /></a></h1>
	<div class="form">
	<form:form commandName="member" action="regist" method="post">
	
		<form:errors element="div"/>
		
		<!-- Email 입력 -->
		<div class="form-group">
			<label for="email"><spring:message code="member.regist.email"/></label>
			<form:input path="email" cssClass="form-control"/>
			<form:errors path="email" />
		</div>
		<!-- Password 입력 -->
		<div class="form-group">
			<label for="password"><spring:message code="member.regist.password"/></label>
			<form:input path="password" cssClass="form-control"/>
			<form:errors path="password" />
		</div>
		<!-- Name 입력 -->
		<div class="form-group">
			<label for="name"><spring:message code="member.regist.name"/></label>
			<form:input path="name" cssClass="form-control"/>
			<form:errors path="name" />
		</div>
		
		<!-- Gender -->
		<div class="form-group">
			<div class="item"><spring:message code="member.regist.gender"/></div>
			<form:radiobuttons path="gender" items="${gender}"/>
			<form:errors path="gender"/>
		</div>
		
		<!-- Hobby -->
		<div class="form-group">
			<div class="item"><spring:message code="member.regist.hobby" /></div>
			<form:checkboxes items="${hobby}" path="hobby" itemLabel="label" itemValue="code"/>
			<form:errors path="hobby"/>
		</div>
		
		<!-- Comment -->
		<div class="form-group">
			<div class="item"><spring:message code="member.regist.comment" /></div>
			<form:textarea path="comment" class="form-control" rows="10" />
			<form:errors path="comment" />
		</div>
		
		<!-- Email Reception true/false -->
		<div class="form-group">
			<label for="reception1" class="item"><spring:message code="member.regist.reception"/></label>
			<form:checkbox path="reception"/>
			<form:errors path="reception" />
		</div>
		
		<input type="submit" value="회원가입" />
	</form:form>
	</div>
	

	
</body>
</html>

