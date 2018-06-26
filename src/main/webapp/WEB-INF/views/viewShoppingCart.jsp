<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカートの中身</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<style type="text/css">
th{
height:30px;
}
a.block{
	text-decoration:none;
	display:block;
}

span#payment{
	width:200px;
	height:40px;
	background-color:lime;
	color:white;
}
</style>
</head>
<body>
	<jsp:include page="userHeader.jsp" />
	<h3>ショッピングカート一覧</h3>
	<div class="center-block">
		<c:choose>
			<c:when test="${orderItemList == null}">
				<div class="center-block">
					<c:out value="商品がありません。" />
				</div>
			</c:when>
			<c:otherwise>
				<table border="1">
					<tr>
						<th colspan="2">商品名</th>
						<th>価格</th>
						<th>個数</th>
						<th></th>
					</tr>
					<c:forEach var="orderItem" items="${orderItemList}">
						<tr>
							<td><a
								href="${pageContext.request.contextPath}/user/item_detail?id=${orderItem.item.id}">
									<img
									src="${pageContext.request.contextPath}/img/<c:out value="${orderItem.item.imagePath}"/>"
									alt="商品画像">
							</a></td>
							<td><a
								href="${pageContext.request.contextPath}/user/item_detail?id=${orderItem.item.id}"><c:out
										value="${orderItem.item.name}" /></a></td>
							<td><fmt:formatNumber pattern="\###,###"
									value="${orderItem.item.price}" /></td>
							<td><c:out value="${orderItem.quantity}" /></td>
							<td><form:form
									action="${pageContext.request.contextPath}/user/deleteOrderItem">
									<input type="hidden" name="id" value="${orderItem.id}">
									<input type="submit" value="削除" style="background-color:blue;
		width:60px;height:40px;color:white;">
								</form:form></td>
						</tr>
					</c:forEach>
				</table>
				<br>
					<div class="center-block">
					<a href="${pageContext.request.contextPath}/userPayment/"
					 style="width:200px;height:40px;background-color:lime;display:block;
					 text-decoration:none;margin-right:auto;margin-left:auto;
					 color:white;margin:auto;border-style:outset;
					 border-color:buttonface;">
					決済へ</a>
					</div>
				
			</c:otherwise>
		</c:choose>
		<div class="center-block">
			<a href="${pageContext.request.contextPath}/user/viewItemList"
			class="block" id="itemList"
			style="width:200px;height:40px;background-color:red;display:block;
					 text-decoration:none;margin-right:auto;margin-left:auto;
					 color:white;border-style:outset;
					 border-color:buttonface;">商品一覧へ</a>
		</div>
	</div>

</body>
</html>