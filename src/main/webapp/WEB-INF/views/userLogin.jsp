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
			<table border="1" style="position:absolute;top:100px;left:10px;">
				<tr>
					<th colspan="2">ログイン<br>
					<form:errors path="email" cssStyle="color:red" element="div" /></th>
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
					<td colspan="2"><input type="submit" value="ログイン"
					style="background-color:navy;color:white;
					width:200px;border-radius:10px;"></td>
				</tr>
				<tr>
					<td colspan="2">
						<a href="${pageContext.request.contextPath}/user/toUserRegister"
						style="background-color:green;color:white;
					width:300px;border-radius:10px;">新規登録はこちら</a>
					</td>
				</tr>
			</table>
			
			<br>
		</form:form>
	</div>
	
</body>
</html>