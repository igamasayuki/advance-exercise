<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<script src="https://ajaxzip3.github.io/ajaxzip3.js" charset="UTF-8"></script>
<meta charset="UTF-8">
<title>ユーザ登録画面</title>
</head>
<body>
	<h2>新規利用者登録画面</h2>
	<h4>お客様の情報を入力し、「お客様情報を登録する」ボタンをクリックしてください。</h4>
	<form:form modelAttribute="userRegistrationForm"
		action="${pageContext.request.contextPath}/register/insert">
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
				(ハイフン抜き　例：XXXyyyy)</th>
				<td>〒<form:input path="zipCode"
						onKeyUp="AjaxZip3.zip2addr(this,'','address','address');" /><br>
					<form:errors path="zipCode" cssStyle="color:red" element="div" /></td>
			</tr>
			<tr>
				<th rowspan="3">住所</th>
				<td><form:input path="address" /><br> <form:errors
						path="address" cssStyle="color:red" element="div" /></td>
			</tr>
			<tr>
				<td><form:input path="address" /><br> <form:errors
						path="address" cssStyle="color:red" element="div" /></td>
			</tr>
			<tr>
				<td><form:input path="address" /></td>
			</tr>
			<tr>
				<th>電話番号</th>
				<td><form:input path="telephone" />-<form:input
						path="telephone" />-<form:input path="telephone" /><br> <form:errors
						path="telephone" cssStyle="color:red" element="div" /></td>
			</tr>
		</table>
		<input type="submit" value="お客様情報を登録する">
		<input type="reset" value="入力内容をクリアする">
	</form:form>
</body>
</html>