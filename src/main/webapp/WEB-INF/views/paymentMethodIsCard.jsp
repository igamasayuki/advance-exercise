<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>カード情報入力画面</title>
</head>
<body>
	<h2>カード情報入力画面</h2>
	<form:form modelAttribute="cardDetailInfoForm"
		action="${pageContext.request.contextPath}/userPaymentApi/callPaymentApi">
		<table>
			<tr>
				<th>請求金額</th>
				<td><fmt:formatNumber value="${amount}" type="CURRENCY"
						currencySymbol="¥" groupingUsed="true" maxFractionDigits="0" /></td>
			</tr>
			<tr>
				<th>カード番号</th>
				<td><form:input path="cardNumber" /></td>
			</tr>
			<tr>
				<th>カード有効期限(年)</th>
				<td><form:input path="cardExpYear" /></td>
			</tr>
			<tr>
				<th>カード有効期限(月)</th>
				<td><form:input path="cardExpMonth" /></td>
			</tr>
			<tr>
				<th>セキュリティコード</th>
				<td><form:input path="cardCvv" /></td>
			</tr>
			<tr>
				<th>カード名義人</th>
				<td><form:input path="nominee" /></td>
			</tr>
		</table>
		<br>
		<form:hidden path="clientId" value="${clientId}" />
		<form:hidden path="orderNumber" value="${orderNumber}" />
		<!-- <input type="hidden" name="clientId" value="${clientId}"> -->
		<input type="hidden" name="payStatus" value="1">
		<input type="submit" value="確定">
	</form:form>
</body>
</html>