<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/adminRegister.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="includeHeader">
<jsp:include page="adminHeader.jsp"/>
</div>

<div id="include">
<jsp:include page="administerMenu.jsp"/>
</div>

<table border="1">
<form:form modelAttribute="registerAdminUserForm" action="${pageContext.request.contextPath}/registerAdmin/registerAdminUser">
<tr>
<td><form:errors path="name" cssStyle="color:red" /></td>
<td>名前:</td>
<td><form:input path="name"/></td>
</tr>
<tr>
<td><form:errors path="email" cssStyle="color:red"/></td>
<td>メールアドレス:</td>
<td><form:input path="email"/></td>
</tr>
<tr>
<td><form:errors path="password" cssStyle="color:red"/></td>
<td>パスワード:</td>
<td><form:password path="password"/></td>
</tr>
<tr>
<td><form:errors path="checkPassword" cssStyle="color:red" /></td>
<td>確認パスワード:</td>
<td><form:password path="checkPassword"/></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="送信"></td>
<td></td>
</tr>
</form:form>
</table>
</body>
</html>