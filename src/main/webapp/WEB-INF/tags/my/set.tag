<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag body-content="empty" %>
<%@ attribute name="var" required="true" rtexprvalue="false"%>
<%@ attribute name="value" required="true" type="java.lang.Object" %>
<%@ variable name-from-attribute="var" 
          alias="setvar" 
          variable-class="java.lang.Object"
          scope="AT_END"
          %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   jspContext.setAttribute("setvar", value);
%>
<%-- <c:set var="setvar" value="${value}"/> --%>