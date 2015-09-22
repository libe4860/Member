<%@ tag import="java.security.InvalidParameterException"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="empty" %>
<%@ attribute name="var" required="true" rtexprvalue="false" %>
<%@ attribute name="scope" %>
<%@ variable name-from-attribute="var"  
 			 scope="AT_BEGIN"
 			 alias="avar"
 			 variable-class="java.lang.String"
%>
<%
	if (scope == null) {
		jspContext.removeAttribute("avar"); //pageContext지우기
		request.removeAttribute(var);
		session.removeAttribute(var);
		application.removeAttribute(var);
	}else {
		jspContext.setAttribute("avar", var);
		switch (scope){
		case "page" :
			jspContext.removeAttribute("avar");
			break;
		case "request" :
			request.removeAttribute(var);
			break;
		case "session" :
			session.removeAttribute(var);
			break;
		case "application" :
			application.removeAttribute(var);
			break;
		default :
			throw new InvalidParameterException("scope attribute error...");
				
		}
	}
%>
