<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
<script>
function reset(){
	document.form.name.value="";
	document.form.email.value="";
	document.form.password.value="";
	document.form.conPassword.value="";
	document.form.zipCode.value="";
	document.form.address1.value="";
	document.form.address2.value="";
	document.form.address3.value="";
	document.form.telephone1.value="";
	document.form.telephone2.value="";
	document.form.telephone3.value="";
}
</script>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>ユーザ登録画面</title>

</head>
<body>
<jsp:include page="userHeader.jsp" />
	<h3>新規利用者登録画面</h3>
	<div class="center-block">
	お客様の情報を入力し、「お客様情報を登録する」ボタンをクリックしてください。<br>
	<form:form modelAttribute="userRegistrationForm"
		action="${pageContext.request.contextPath}/user/register" name="form">
		<table border="1">
			<tr>
				<th>名前</th>
				<td><form:input path="name" /><br> <form:errors
						path="name" cssStyle="color:red" element="div" /></td>
			</tr>
			<tr>
				<th>メールアドレス</th>
				<td><form:input path="email" /><br> <form:errors
						path="email" cssStyle="color:red" element="div" /></td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td><form:input path="password" type="password" /><br> <form:errors
						path="password" cssStyle="color:red" element="div" /></td>
			</tr>
			<tr>
				<th>確認用パスワード</th>
				<td><form:input path="conPassword" type="password" /><br> <form:errors
						path="conPassword" cssStyle="color:red" element="div" /></td>
			</tr>
			<tr>
				<th>郵便番号<br>
				(ハイフン抜き・半角　例：123456)</th>
				<td>〒<form:input path="zipCode"
						onKeyUp="AjaxZip3.zip2addr(this,'','address1','address1');" /><br>
					<form:errors path="zipCode" cssStyle="color:red" element="div" /></td>
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
			<tr>
				<th>電話番号</th>
				<td><form:input path="telephone1" />-<form:input
						path="telephone2" />-<form:input path="telephone3"/><br> <form:errors
						path="telephone" cssStyle="color:red" element="div"/></td>
			</tr>
		</table>
		<input type="submit" value="お客様情報を登録する"
		style="width:200px;height:40px;background-color:red;color:white;
		border-style:outset;">
	</form:form>
	<input type="button" value="入力内容をクリアする" onClick="reset();"
		style="width:200px;height:40px;background-color:blue;color:white;
		border-style:outset;">
	</div>
	<jsp:include page="footter.jsp"/>
</body>
</html>