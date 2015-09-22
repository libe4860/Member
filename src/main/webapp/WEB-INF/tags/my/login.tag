<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="empty" %>
<%@ attribute name="test" required="true" type="java.lang.Boolean"%>
<%@ attribute name="cls" %>

<%
	if(test==true){
%>
	<button class="${cls}">My:login Log out</button>
<% 
	}else{
%>
	<button class="${cls} btn-primary">My:login Log In</button>
<%
	}
%>
