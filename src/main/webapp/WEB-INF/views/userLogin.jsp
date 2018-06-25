<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<style type="text/css">

</style>
<title>ログイン画面</title>
</head>
<body style="background-image:url(${pageContext.request.contextPath}/img/yasai.jpg);
background-size:cover;">
	<jsp:include page="userHeader.jsp" />
	<div style="float:left;">
		<form:form modelAttribute="userLoginForm"
			action="${pageContext.request.contextPath}/user/login">
		<form:errors path="email" cssStyle="color:red" element="div" />
			<table border="1" style="position:absolute;top:100px;left:10px;">
				<tr>
					<th colspan="2">ログイン</th>
				</tr>
				<tr>
					<th>メールアドレス:</th>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<th>パスワード：</th>
					<td><form:password path="password" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="ログイン"></td>
				</tr>
				<tr>
					<td colspan="2"><div class="center-block">
		<a href="${pageContext.request.contextPath}/user/toUserRegister">新規登録はこちら</a>
	</div></td>
				</tr>
			</table>
			
			<br>
		</form:form>
	</div>
	
</body>
</html>