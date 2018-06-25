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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/userHeader.css" />
<title>Insert title here</title>
</head>
<body>
<div id="header">
<div id="userHeader" align="right">

<sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
							<sec:authentication var="userName" property="principal.user.name" />
								<c:out value="${userName}" />&nbsp;さんこんにちは
						</sec:authorize>

	<p><a href="${pageContext.request.contextPath}/user/logout">ログアウト</a></p>
	<p><a href="${pageContext.request.contextPath}/user/login">ログイン</a></p>
</div>
			
<div id="linkHeader" align="left">
	<h1 align ="left"><a href="${pageContext.request.contextPath}/user/viewItemList"><img src="${pageContext.request.contextPath}/img/rakus.jpg" width="50"
	
	height="50" alt="ロゴ画像">ＥＣサイトラクス</a></h1></div>
<div id="title" align="center">
</div>
</div>
</body>
</html>