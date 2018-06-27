<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/administerHeader.css" />
<title>Insert title here</title>
</head>
<body>
<div id="header" style="width:1200px;">
<div id="userHeader">
<sec:authorize access="hasRole('ROLE_ADMIN') and isAuthenticated()">
							<sec:authentication var="userName" property="principal.adminUser.name" />
								<c:out value="${userName}" />&nbsp;さんこんにちは<br>
								<p><a href="${pageContext.request.contextPath}/adminlogout">ログアウト</a></p>
						</sec:authorize>
	
	</div>
	<a href="${pageContext.request.contextPath}/admin/viewAdminTop">
	<img src="${pageContext.request.contextPath}/img/king-kyabetu.png" alt="きゃべつ王国"></a></div>
</body>
</html>