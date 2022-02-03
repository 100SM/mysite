<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("newline", "\n");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="user.join.label.guestbook" /></title>
<link href="${pageContext.request.contextPath}/assets/css/main.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div id="site-introduction">
					<form action="${pageContext.request.contextPath}/guestbook/add" method="post">
						<input type='hidden' name='a' value='add'>
						<table border=1 width=500>
							<tr>
								<td><spring:message code="user.join.label.name" /></td>
								<td><input type="text" name="name"></td>
								<td><spring:message code="user.join.label.password" /></td>
								<td><input type="password" name="password"></td>
							</tr>
							<tr>
								<td colspan=4><textarea name="message" cols=60 rows=5></textarea></td>
							</tr>
							<tr>
								<td colspan=4 align=right><input type="submit" VALUE="등록"></td>
							</tr>
						</table>
					</form>
					<c:set var="count" value="${fn:length(list) }" />
					<c:forEach items="${list}" var="vo" varStatus="status">
						<br>
						<table width=510 border=1>
							<tr>
								<td>[${count-status.index}]</td>
								<td>${vo.name}</td>
								<td>${vo.regDate}</td>
								<td><a
									href="${pageContext.request.contextPath}/guestbook/delete/${vo.no}"><spring:message code="user.join.label.delete" /></a></td>
							</tr>
							<tr>
								<td colspan=4>${fn:replace(vo.message,newline,"<br/>") }</td>
							</tr>
						</table>
					</c:forEach>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>