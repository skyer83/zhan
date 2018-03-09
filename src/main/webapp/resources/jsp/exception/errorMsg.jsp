<%@ page language="java" session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	Object e = request.getAttribute("javax.servlet.error.exception");
	request.setAttribute("wsmException", e);
%>
<c:if test="${!empty wsmException}">
	<h3>错误码：</h3><%=request.getAttribute("javax.servlet.error.status_code")%>
	<h3>错误页面：</h3><%=request.getAttribute("javax.servlet.error.request_uri")%>
	<h3>错误信息：</h3><%=e%>
	<%--
	<h3>错误堆栈信息：</h3>
	<c:forEach var="trace" items="${e.stackTrace}">
		<p>${trace}</p>
	</c:forEach>
	--%>
</c:if>