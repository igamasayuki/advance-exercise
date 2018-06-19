<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css">
<meta charset="UTF-8">
<title>商品詳細</title>
</head>
<body>
<jsp:include page="userHeader.jsp" />
	<div align="center">
		<h3>商品詳細</h3>
		<c:if test="${item != null}">
			<table border="1">

				<tr>
					<td rowspan="3"><img src="${pageContext.request.contextPath}/img/<c:out value="${item.imagePath}"/>"></td>
					<td>商品名</td>
					<td><c:out
								value="${item.name}" /></td>
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
		</c:if>
		<br><br>

		<form action="${pageContext.request.contextPath}/×××" method="post">
			個数　：　<select path="quantity" /><br> <br>
		 <input type="submit" value="カートに入れる">
		</form>
		<br><br>
			 <a href="${pageContext.request.contextPath}/viewItemList">商品一覧に戻る</a>
	</div>
</body>
</html>