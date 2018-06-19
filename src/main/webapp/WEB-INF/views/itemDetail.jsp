<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
</head>
<body>
<jsp:include page="header/header.jsp"/><br>
<div align ="center">
	<h2>商品詳細</h2>

	<table border="1">
		
			<tr>
				<td rowspan="3"><c:out value="${item.imagePath}" /></td>
				<td>商品名</td>
				<td><c:out value="${item.name}" /></td>
			</tr>
			<tr>
				<td>価格</td>
				<td><c:out value="${item.price}" /></td>
			</tr>
			<tr>
				<td>商品説明</td>
				<td><c:out value="${item.description}" /></td>
			</tr>
	</table>
	
	<form action="${pageContext.request.contextPath}/×××" method="post">
	<select path="quantity"/><br>
	<input type="submit" value="カートに入れる">
	</form>
	<a href="${pageContext.request.contextPath}/viewItemList">商品一覧に戻る</a>
</div>
</body>
</html>