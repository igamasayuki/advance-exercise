<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404エラー画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="../userHeader.jsp" />

<h3>404 Not Found</h3>
<div class="center-block">
申し訳ございません。お探しのページは見つかりませんでした。

以下のリンクからログインし直してください。

<br>
<img src="${pageContext.request.contextPath}/img/cabbage.jpg"><br>
<a href="${pageContext.request.contextPath}/user/viewItemList">商品一覧へ</a>
</div>
</body>
</html>