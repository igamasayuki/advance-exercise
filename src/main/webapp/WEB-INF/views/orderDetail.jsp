<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/adminHeader.css" />
<meta charset="UTF-8">
<title>注文詳細画面</title>
</head>
<body>
	<jsp:include page="adminHeader.jsp" />
	<div align="center">
		<h2>注文詳細</h2>
	</div>
	<table border="1" align="center">
		<tr>
			<th>注文No</th>
			<td><c:out value="${order.orderNumber}" /></td>
		</tr>
		<tr>
		<tr>
			<th>メールアドレス</th>
			<td><c:out value="${order.deliveryEmail}" /></td>
		</tr>

		<tr>
			<th>住所</th>
			<td><c:out value="${order.deliveryAddress}" /></td>
		</tr>

		<tr>
			<th>TEL</th>
			<td><c:out value="${order.deliveryTel}" /></td>
		</tr>


	</table>
	<br>
	<table border="1" align="center">
		<tr>
			<th>商品</th>
			<th>価格</th>
			<th>×</th>
			<th>個数</th>
			<th>金額</th>
		</tr>
		<tr>
			<td><c:out value="${item.name}" /></td>
			<td><fmt:formatNumber pattern="\###,###" value="${item.price}" /></td>
			<td>×</td>
			<td>カウントする</td>
			<td>priceの合計</td>
		</tr>
	</table>
	<br>

	<table border="1" align="center">
		<tr>
			<th>小計</th>
			<td><fmt:formatNumber pattern="\###,###" value="${order.id}" />円</td>
		</tr>
		<tr>
			<th>税</th>
			<td><fmt:formatNumber pattern="\###,###" value="${order.id}" />円</td>
		</tr>
		<tr>
			<th>支払方法</th>
			<td><c:out value="${order.id}" /></td>
		</tr>
		<tr>
			<th>送料一律</th>
			<td><fmt:formatNumber pattern="\###,###" value="500" />円</td>
		</tr>
		<tr>
			<th>総計</th>
			<td><fmt:formatNumber pattern="\###,###"
					value="${order.totalPrice}" />円</td>
		</tr>
	</table>
	<br>

	<table border="1" align="center">
		<tr>
			<th>現在のステータス</th>
			<th>ステータス変更</th>
		</tr>
		<tr>
			<td><c:choose>
					<c:when test="${order.status == 0}">未購入</c:when>
					<c:when test="${order.status == 1}">未入金</c:when>
					<c:when test="${order.status == 2}">入金済み</c:when>
					<c:when test="${order.status == 3}">発送済み</c:when>
					<c:when test="${order.status == 4}">キャンセル</c:when>
				</c:choose></td>
			<td><select name="birthMonth">
					<option value="0">未購入</option>
					<option value="1">未入金</option>
					<option value="2">入金済み</option>
					<option value="3">発送済み</option>
					<option value="4">キャンセル</option>
			</select>
			<input class="btn" type="submit" value="更新">
			</td>
		</tr>
		
	</table>
	<br>

	<a href="${pageContext.request.contextPath}/viewOrderList/">注文一覧に戻る
	</a>

</body>
</html>