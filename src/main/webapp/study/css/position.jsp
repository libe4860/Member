<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>position.jsp</title>
<%@ include file="/WEB-INF/views/common.jspf" %>
<style type="text/css">
	.box { 
		width: 100px; 
		height: 100px;
		margin: 10px;
		background: rgb(10, 200, 100); 
		text-align: center;
		transition-duration: 0.1s;
	}
	.moveright {
		top: 200px;
		left: 200px;
		
	}
	.absolute {
		position: absolute;
	}
	.fixed {
		position: fixed;
	}
	.relative {
		position: relative
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('.box').click(function(){
			//alert("box click.. count=" + $('.box').size()); //size()대신 length 같은 결과
			$(this).clearQueue().animate({'margin-left':'+=500px'});
		});
	});
</script>
</head>
<body>
<h1>position : static(default) | relative | absolute | fixed</h1>
<h2>left right top bottom</h2>

<div class="box moveright absolute">box1 : absolute</div>
<div class="box moveright fixed">box2 : fixed</div>
<div class="box moveright">box3</div>
<div class="box moveright relative">box4 : relative</div>

<div class="w3-row">
	<div class="w3-col w3-half w3-green w3-padding">
		<img src="http://www.placehold.it/200x300" alt="" width="100%;"/>
	</div>
	<div class="w3-col w3-half w3-blue w3-padding">
		<img src="http://www.placehold.it/200x300" alt="" width="100%;"/>
	</div>
</div>

</body>
</html>






