<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="header">
	<h1>${siteVo.title }</h1>
	<ul>
		<c:choose>
			<c:when test='${empty authUser }'>
				<li><a href="${pageContext.request.contextPath}/user/login"><spring:message code="includes.header.label.login" /></a>
				<li>
				<li><a href="${pageContext.request.contextPath}/user/join"><spring:message code="includes.header.label.join" /></a>
				<li>
			</c:when>
			<c:otherwise>
				<li><a href="${pageContext.request.contextPath}/user/update"><spring:message code="includes.header.label.update" /></a>
				<li>
				<li><a href="${pageContext.request.contextPath}/user/logout"><spring:message code="includes.header.label.logout" /></a>
				<li>
				<c:if test="${authUser.role eq 'ADMIN' }">
					<li><a href="${pageContext.request.contextPath}/admin"><spring:message code="includes.header.label.admin" /></a>	<li>
				</c:if>
				<li>
				${authUser.name}<spring:message code="includes.header.label.hello" /></li>

			</c:otherwise>
		</c:choose>
	</ul>
</div>