<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/include.css" />
<meta charset="UTF-8">
<title>管理者ログイン画面</title>
</head>
<body style="background-image:url(${pageContext.request.contextPath}/img/kyabetuK.jpg);
background-size:cover;">
	<jsp:include page="adminHeader.jsp" /><br>
	<div style="float:left;">
		<form:form modelAttribute="loginAdminForm"
			action="${pageContext.request.contextPath}/adminLogin">
			<table border="1" style="position:absolute;top:150px;left:10px;">
				<tr>
					<th colspan="2">管理者ログイン<br>
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
			</table>
			
			<br>
		</form:form>
	</div>
</body>
</html>