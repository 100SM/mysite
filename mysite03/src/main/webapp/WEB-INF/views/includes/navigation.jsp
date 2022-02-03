<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="navigation">
	<ul>
		<li><a href="${pageContext.request.contextPath}"><spring:message code="includes.navigation.label.myName" /></a></li>
		<li><a href="${pageContext.request.contextPath}/guestbook"><spring:message code="includes.navigation.label.guestbook" /></a></li>
		<li><a href="${pageContext.request.contextPath}/board"><spring:message code="includes.navigation.label.board" /></a></li>
	</ul>
</div>