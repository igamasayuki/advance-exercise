<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>						
<div style="width:auto;height:80px;background-color:orange">
<div style="float:right">
<a href="${pageContext.request.contextPath}/user/logout">ログアウト</a>
<br>
<a href="${pageContext.request.contextPath}/user/login">ログイン</a>
<br>
</div>
<div style="float:right;">
<a href="${pageContext.request.contextPath}/user/viewShoppingCart" style="padding-right:30px;">カートの中身を表示する</a>
</div>
<div style="float:left">
<h1><a href="${pageContext.request.contextPath}/user/viewItemList">
<img src="${pageContext.request.contextPath}/img/rakus.jpg" width="50"height="50" alt="ロゴ画像">ＥＣサイトラクス</a></h1>
</div>
</div>
</body>
</html>