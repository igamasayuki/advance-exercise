<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/adminHeader.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="adminHeader.jsp"/>
<form:form modelAttribute="registerAdminUserForm" action="${pageContext.request.contextPath}/registerAdmin/registerAdminUser">

<form:errors path="name" cssStyle="color:red" />
名前:<form:input path="name"/><br>

<form:errors path="email" cssStyle="color:red"/><br>
メールアドレス:<form:input path="email"/><br>

<form:errors path="password" cssStyle="color:red"/><br>
パスワード:<form:password path="password"/><br>

<form:errors path="checkPassword" cssStyle="color:red" /><br>
確認パスワード:<form:password path="checkPassword"/><br>
<input type="submit" value="送信">
</form:form>
</body>
</html>