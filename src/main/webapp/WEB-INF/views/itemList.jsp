<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<meta charset="UTF-8">
<title>商品一覧</title>
</head>
<body>
	<jsp:include page="userHeader.jsp" />
	<h3>商品一覧</h3>
	<div class="center-block">
		<form action="${pageContext.request.contextPath}/user/findItem"
			method="post">
			<input type="text" name="word"> <input type="submit"
				value="検索する">
		</form>
</div>
	<c:choose>
		<c:when test="${itemList == null}">
			<div class="center-block">
				<c:out value="商品がありません。" />
			</div>
		</c:when>
		<c:otherwise>
			<table border="1">
				<tr>
					<td colspan="2">商品名</td>
					<td>価格</td>
				</tr>

				<c:forEach var="item" items="${itemList}">
					<tr>
						<td><a
							href="${pageContext.request.contextPath}/item_detail/item_detail?id=${item.id}"><c:out
									value="${item.name}" /></a></td>
						<td><img
							src="${pageContext.request.contextPath}/img/<c:out value="${item.imagePath}"/>"></td>
						<td><fmt:formatNumber pattern="\###,###"
								value="${item.price}" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>

	</c:choose>

</body>
</html>