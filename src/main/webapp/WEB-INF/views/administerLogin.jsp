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
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="adminHeader.jsp" /><br>
	<h3>ログイン</h3>
	<form:form modelAttribute="loginAdminForm"
		action="${pageContext.request.contextPath}/adminLogin">
		<div class="center-block">
			<form:errors path="email" cssStyle="color:red" element="div" />
			<br> メールアドレス:
			<form:input path="mailAddress" />
			<br>
			
			パスワード:
			<form:password path="password" />
			<br>
		</div>
		<div class="center-block">
		<input type="submit" value="ログイン">
		</div>
	</form:form>

</body>
</html>