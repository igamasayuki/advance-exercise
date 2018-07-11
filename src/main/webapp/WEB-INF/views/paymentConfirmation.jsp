<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>決済確認画面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/display.css">
</head>
<body>
	<jsp:include page="userHeader.jsp" />
	<div class="center-block">

		<h3>ご注文内容</h3>
		<hr>
		<c:set var="sumPrice" value="0" />
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
					<td><c:out value="${orderItem.item.name}" /></td>
					<td><fmt:formatNumber value="${orderItem.item.price}"
							type="CURRENCY" currencySymbol="¥" groupingUsed="true"
							maxFractionDigits="0" /></td>
					<td><c:out value="${orderItem.quantity}" /></td>
					<td><fmt:formatNumber
							value="${orderItem.item.price * orderItem.quantity}"
							type="CURRENCY" currencySymbol="¥" groupingUsed="true"
							maxFractionDigits="0" /></td>
					<c:set var="sumPrice"
						value="${sumPrice + orderItem.item.price * orderItem.quantity}" />
					<td><fmt:formatNumber
							value="${orderItem.item.price * orderItem.quantity * (1.08)}"
							type="CURRENCY" currencySymbol="¥" groupingUsed="true"
							maxFractionDigits="0" /></td>
				</tr>
			</c:forEach>
			<tr>
				<td>消費税</td>
				<td colspan="4"><fmt:formatNumber value="${sumPrice * 0.08}"
						type="CURRENCY" currencySymbol="¥" groupingUsed="true"
						maxFractionDigits="0" /></td>
			</tr>
			<tr>
				<td>送料一律</td>
				<td colspan="4"><fmt:formatNumber value="${500}"
						type="CURRENCY" currencySymbol="¥" groupingUsed="true"
						maxFractionDigits="0" /></td>
			</tr>
			<tr>
				<td>総計</td>
				<td colspan="4"><fmt:formatNumber value="${order.totalPrice}"
						type="CURRENCY" currencySymbol="¥" groupingUsed="true"
						maxFractionDigits="0" /></td>
			</tr>
		</table>
		<hr>
		<h3>お届け先</h3>
		<c:out value="${cardInfoError}" /><br>
		<form:form modelAttribute="cardDetailInfoForm" name="Form1"
			action="${pageContext.request.contextPath}/userPaymentApi/callPaymentApi">
			<table>
				<tr>
					<th>お名前:</th>
					<td><c:out value="${order.deliveryName}" /></td>
				</tr>
				<tr>
					<th>メールアドレス:</th>
					<td><c:out value="${order.deliveryEmail}" /></td>
				</tr>
				<tr>
					<th>郵便番号:</th>
					<td><c:out value="${order.deliveryZipCode}" /></td>
				</tr>
				<tr>
					<th>住所:</th>
					<td><c:out value="${order.deliveryAddress}" /></td>
				</tr>
				<tr>
					<th>電話番号:</th>
					<td><c:out value="${order.deliveryTel}" /></td>
				</tr>
				<tr>
					<th>お支払方法:</th>
					<td><input class="paymethod" type="radio" name="paymethod" checked onClick="changeDisabled()">銀行振込
					<input class="paymethod"  id="card" type="radio" name="paymethod" onClick="changeDisabled()">カード決済<br>
					<div  id="display" class="display">
					カード番号:<form:input type="number" path="card_number" id="paymethod" /><br>
					カード有効期限(年):<form:input type="number" path="card_exp_year" id="paymethod" /><br> 
					カード有効期限(月):<form:input type="number" path="card_exp_month" id="paymethod" /><br> 
					セキュリティコード:<form:input type="number" path="card_cvv" id="paymethod" /><br> 
					カード名義人:<form:input path="card_name" id="payMethod" />
			        <form:hidden path="payMethod" value="${1}" />
					</div>
					</td>
				</tr>
			</table>
			<br>
			<form:hidden path="user_id" value="${order.userId}" />
			<form:hidden path="order_number" value="${order.orderNumber}" />
			<form:hidden path="amount" value="${order.totalPrice}" />
			<input type="hidden" name="orderId" value="${orderId}">
			<input type="submit" value="確定"
				style="background-color: red; color: white; width: 200px; height: 40px;">
		</form:form>

		<br> <a
			href="${pageContext.request.contextPath}/userPayment/toChangeAddress">お届け先住所を変更する</a><br>
		<a href="javascript:history.go(-1)">[戻る]</a>
	</div>
	<jsp:include page="footter.jsp" />
	
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script>
	$(function(){
		
		
		$(".paymethod").click(function(){
			$("#display").slideToggle();
		//	if($("#card").prop('checked')){
		//		$(".display").css("display","inline-block");
	//		}else{
	//			$(".display").css("display","none");
	//		}
		});
	});
	</script>
</body>
</html>