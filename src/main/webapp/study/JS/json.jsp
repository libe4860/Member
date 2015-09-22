<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>json.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>
<style type="text/css">
	p {
		
		transition : transform 1s; 
	}
</style>
<script type="text/javascript">
<!-- 자바스크립트 객체표기방법(자바스크립트 리터럴) 변수의 타입은 없지만 값은 있는 객체 -->
// 	var member = {
// 		email : "xxx@webapp.com", 
// 		password : "1234",
// 		name : "홍길동",
// 		gender : "male",
// 		hobby : ["programming", "reading"],
// 		comment : "열공하세요",
// 		reception : true
// 	};
	
$(document).ready(function(){
	$('button').on("click", function(){
		
		//Ajax
		$.getJSON("member.json", function(member){
			console.log(member);
			var message = "email = " + member.email + "<br>" +
						  "name = " + member.name + "<br>" +
						  "password = " + member.password + "<br>" + 
						  "gender = " + member.gender + "<br>" +
						  "hobby = " + member.hobby + "<br>" +
						  "reception = " + member.reception;
	// 		$('p.one').text(message);
	// 		$('p.one').html(message);
	// 		append : 누를때마다 계속 뒤에 추가되는 것
	//  	$('p').append(message);
		  	$('p').prepend(message);
		  	
		  	//.each : 자바스크립트 for문 대신 쓰는 것 
		  	$.each(member.hobby, function(index, value){
		  		console.log("hobby[" + index + "] = " + member.hobby[index]);
		  		console.log("hobby[" + index + "] = " + value);
		  	});
		  	
		  	for(var i=0; i<member.hobby.length; i++){
		  		console.log("hobby[" + i + "] = " + member.hobby[i]);
		  	}
		});
	});
	
	$('p').on("click", function(){
// 		$(this).css("transform", "translate(50px,0)");
		$(this).clearQueue().animate({"margin-left":"+=50px"});
	});
});
</script>
</head>
<body>
<h1>json</h1>
<button>member print1</button>
<button>member print2</button>
<p>
	pring 1 :
</p>
<p>
	print 2 :
</p>
</body>
</html>

