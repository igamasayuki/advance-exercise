<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>決済確認画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="userHeader.jsp"/>
<div class="center-block">
	<div class="link-right" align="right">
	<a href="${pageContext.request.contextPath}/user/viewShoppingCart">カートの中身を表示する</a>
	</div>
	<h3>ご注文内容</h3>
<hr>
<table border="1">
	<tr>
		<th>商品名</th>
		<th>価格</th>
		<th>個数</th>
		<th>税抜き価格</th>
		<th>小計</th>
	</tr>
	<c:forEach var="orderItem" items="${order.orderItemList}">
		<tr>
			<td><c:out value="${orderItem.item.name}"/></td>
			<td><fmt:formatNumber pattern="\###,###" value="${orderItem.item.price}"/></td>
			<td><c:out value="${orderItem.quantity}"/></td>
			<td><fmt:formatNumber pattern="\###,###" value="${orderItem.item.price * orderItem.quantity}"/></td>
			<td><fmt:formatNumber pattern="\###,###" value="${orderItem.item.price * orderItem.quantity * (1.08)}"/></td>
		</tr>
	</c:forEach>
	<tr>
		<td>消費税</td>
		<td colspan="4"><fmt:formatNumber pattern="\###,###" value="${order.totalPrice * 0.08}"/></td>
	</tr>
	<tr>
		<td>送料一律</td>
		<td colspan="4"><fmt:formatNumber pattern="\###,###" value="${500}"/></td>
	</tr>
	<tr>
		<td>総計</td>
		<td colspan="4"><fmt:formatNumber pattern="\###,###" value="${order.totalPrice * (1.08) + 500}"/></td>
	</tr>
</table>
<h3>お届け先</h3>
	<hr>
	お名前:<c:out value="${order.deliveryName}"/><br>
	メールアドレス:<c:out value="${order.deliveryEmail}"/><br>
	郵便番号:<c:out value="${order.deliveryZipCode}"/><br>
	住所:<c:out value="${order.deliveryAddress}"/><br>
	電話番号:<c:out value="${order.deliveryTel}"/><br>
	<form:form action="${pageContext.request.contextPath}/userPayment/closeOut">
		<input type="hidden" name="orderId" value="${order.id}">
		<input type="submit" value="確定" style="background-color:red;color:white;
		width:200px;height:40px;">
	</form:form>
</div>
</body>
</html>