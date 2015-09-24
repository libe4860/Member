<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>
<style type="text/css">
	@keyframes lank {
		from{
			transform: rotateX(180deg);
		}
		to{
			transform: rotateX(0deg);
		}
	}
	
	.lank {
		animation: lank 1s;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('h1').click(function(){			
			$.getJSON('member', function(data){
				console.log(data);
				$('#memberList').html('');
				$.each(data, function(index, m){
					console.log(m.name + " " + m.email);
// 					$('#memberList').append('<li class="lank">' + m.name + m.email + m.regdate + '</li>');
					$('<li class="lank">' + m.name + m.email + m.regdate + '</li>').css('animation-delay', index + 's')
																				   .appendTo('#memberList');
				});
			});
		});
		
		$('#memberList').dblclick(function(){
			$(this).html('');
		});
	});
</script>
</head>
<body>
<h1>View</h1>
<ol id="memberList">
	<li></li>
</ol>
</body>
</html>

