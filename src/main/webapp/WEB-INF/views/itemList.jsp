<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/administerHeader.css" />
<meta charset="UTF-8">
<title>商品一覧</title>
<jsp:include page="header/header.jsp"/>
</head>
<body>
<div align ="center">

	<h3>商品一覧</h3>

</div>
	<form action="${pageContext.request.contextPath}/findItem"
		method="post">
		<input type="text" name="word">
		<input type="submit" value="検索する">
	</form>
	<c:choose>
	<c:when test="${itemList == null}">
	<c:out value="商品がありません。"/>
	</c:when>
	<c:otherwise>
		<table border="1" align ="center">
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
		</c:otherwise>
		
</c:choose>
</body>
</html>