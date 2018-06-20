<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/administerHeader.css" />
<title>Insert title here</title>
</head>
<body>
<div id="header">
<div id="userHeader">
<p>こんにちは<c:out value="${name}"/>さん</p>
	<p><a href="${pageContext.request.contextPath}/adminlogout">ログアウト</a></p>
	</div>
	<h1 align ="left"><a href="${pageContext.request.contextPath}/itemList/viewItemList"><img src="${pageContext.request.contextPath}/img/rakus.jpg" width="50"
	
	height="50" alt="ロゴ画像">ＥＣサイトラクス</a></h1></div>
</body>
</html>