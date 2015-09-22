<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="test" required="true" type="java.lang.Boolean"%>
<%
	if(test==true){
%>
	<jsp:doBody/>
<% 
	}
%>
<%-- 
<%
	out.println("#######<br>");
%>
	<<jsp:doBody/>
	test = ${test}<br>
<% 
	out.println(test+"<br>");
	out.println("#######<br>");
%>
--%>