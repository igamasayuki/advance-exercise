<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
</head>
<body>
	<h2>ログイン</h2>
	<form:form modelAttribute="userLoginForm"
		action="${pageContext.request.contextPath}/">
		<table border="">
			<tr>
				<th>メールアドレス:</th>
				<td><form:input path="email" /></td>
			</tr>
			<tr>
				<th>パスワード：</th>
				<td><form:input path="password" /></td>
			</tr>
		</table>
		<input type="submit" value="ログイン">
	</form:form>
	<a href="${pageContext.request.contextPath}/register/toUserRegister">新規登録はこちら</a>
</body>
</html>