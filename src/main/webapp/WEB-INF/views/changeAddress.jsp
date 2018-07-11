<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
<meta charset="UTF-8">
<title>お届け先住所変更画面</title>
</head>
<body>
	<h2>住所変更</h2>
	<form:form modelAttribute="userRegistrationForm"
		action="${pageContext.request.contextPath}/userPayment/">
		<table>
			<tr>
				<th>郵便番号</th>
				<td>〒<form:input path="zipCode"
						onKeyUp="AjaxZip3.zip2addr(this,'','address1','address1');" /><br></td>
			</tr>
			<tr>
				<th rowspan="3">住所</th>
				<td>都道府県市区町村<form:input path="address1" /><br> <form:errors
						path="address1" cssStyle="color:red" element="div" /></td>
			</tr>
			<tr>
				<td>番地<form:input path="address2" /><br> <form:errors
						path="address2" cssStyle="color:red" element="div" /></td>
			</tr>
			<tr>
				<td>建物名<form:input path="address3" /></td>
			</tr>
		</table>
		<input type="submit" value="変更">
	</form:form>
</body>
</html>