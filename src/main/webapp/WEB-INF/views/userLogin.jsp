<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<title>ログイン画面</title>
</head>
<body>
	<jsp:include page="userHeader.jsp" />
	<h3>ログイン</h3>
	<div class="center-block">
		<form:form modelAttribute="userLoginForm"
			action="${pageContext.request.contextPath}/login">
			<table border="">
				<tr>
					<th>メールアドレス:</th>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<th>パスワード：</th>
					<td><form:input path="password" type="password" /></td>
				</tr>
			</table>
			<input type="submit" value="ログイン">
			<br>
		</form:form>
	</div>
	<div class="center-block">
		<a href="${pageContext.request.contextPath}/register/toUserRegister">新規登録はこちら</a>
	</div>
</body>
</html>