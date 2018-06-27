<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="userHeader.jsp"/>
	<h3>購入履歴</h3>
	
	<div>
		<c:choose>
			<c:when test="${orderList == null}">
				<div class="center-block">
					<c:out value="購入履歴がありません。"/>
				</div>
			</c:when>
			<c:otherwise>
				<div style="margin-right:auto;margin-left:auto;width:auto;height:800px;">
					<table border="1">
						<tr>
							<th>注文日時</th>
							<th>注文状況</th>
							<th>商品名</th>
							<th>商品価格</th>
							<th>購入個数</th>
							<th>総計(送料込)</th>
						</tr>
						<tr>
							<c:forEach var="order" items="${orderList}">
								<td rowspan="${order.itemListSize}">
									<c:out value="${order.orderDate}"/>
								</td>
								<td rowspan="${order.itemListSize}">
									<c:choose>
										<c:when test="${order.status==1}">未入金</c:when>
										<c:when test="${order.status==2}">入金済</c:when>
										<c:when test="${order.status==3}">発送済</c:when>
									</c:choose>
								</td>
								<c:forEach var="orderItem" items="${order.orderItemList}">
									<td><c:out value="${orderItem.item.name}"/></td>
									<td><c:out value="${orderItem.item.prce}"/></td>
									<td><c:out value="${orderItem.quantity}"/></td>
								</c:forEach>
								<td>
									<c:out value="${order.totalPrice}"/>
								</td>
							</c:forEach>
						</tr>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>