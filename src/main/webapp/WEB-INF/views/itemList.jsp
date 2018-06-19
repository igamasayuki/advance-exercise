<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
</head>
<body>
<jsp:include page="header/header.jsp"/><br>
<div align ="center">
	<h3>商品一覧</h3>

	<form action="${pageContext.request.contextPath}/findItem"
		method="post">
		<input type="text" name="word">
		<input type="submit" value="検索する">
	</form>

	<c:if test="${findItemList == null}">
		<table border="1">
			<tr>
				<td colspan="2">商品名</td>
				<td>価格</td>
			</tr>

			<c:forEach var="item" items="${itemList}">
				<tr>
					<td>
					<a href="${pageContext.request.contextPath}/item_detail/item_detail?id=${item.id}"><c:out value="${item.name}" /></a>
					</td>
					<td><c:out value="${item.imagePath}" /></td>
					<td><c:out value="${item.price}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${findItemList != null}">
		<table border="1">
			<tr>
				<td colspan="2">商品名</td>
				<td>価格</td>
			</tr>

			<tr>
				<c:forEach var="findItem" items="${findItemList}">
					<td><c:out value="${findItem.name}" /></td>
					<td><c:out value="${findItem.imagePath}" /></td>
					<td><c:out value="${findItem.price}" /></td>
				</c:forEach>
			</tr>
		</table>
	</c:if>
</div>
</body>
</html>