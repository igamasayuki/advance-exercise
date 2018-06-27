<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/include.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/administerHeader.css" />
<meta charset="UTF-8">
<title>注文一覧画面</title>
</head>
<body>
	<jsp:include page="administerMenu.jsp" />
	<jsp:include page="adminHeader.jsp" />
	<div align="center">
		<h2>注文一覧画面</h2>
	</div>
	<c:choose>
		<c:when test="${orderCheck}">
			<font size="5" color="red"> <br>
			<c:out value="注文がありません" /><br>
			</font>
		</c:when>
		<c:otherwise>

			<table border="1" align="center" style="width:600px;">
				<tr>
					<th>注文番号</th>
					<th>日付</th>
					<th>利用者名</th>
					<th>現在のステータス</th>
					<th>総計(税込)</th>
				</tr>

				<c:forEach var="order" items="${order}">
					<tr>
						<td><a
							href="${pageContext.request.contextPath}/admin/orderDetail?id=<c:out value= "${order.id}"/>">
								<c:out value="${order.orderNumber}" />
						</a></td>
						<td><fmt:formatDate pattern="yyyy/MM/dd"
								value="${order.orderDate}" /></td>
						<td><c:out value="${order.deliveryName}" /></td>
						<td><c:choose>
								<c:when test="${order.status == 0}">未購入</c:when>
								<c:when test="${order.status == 1}">未入金</c:when>
								<c:when test="${order.status == 2}">入金済み</c:when>
								<c:when test="${order.status == 3}">発送済み</c:when>
								<c:when test="${order.status == 9}">キャンセル</c:when>
							</c:choose></td>
						<td><fmt:formatNumber pattern="\###,###"
								value="${order.totalPrice}" /></td>
					</tr>
				</c:forEach>
			</table>



		</c:otherwise>
	</c:choose>

	<br>
	<a href="${pageContext.request.contextPath}/admin/fromLogintoMenu">TOPに戻る
	</a>


</body>
</html>