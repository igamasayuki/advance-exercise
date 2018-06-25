<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/adminRegister.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/include.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/administerHeader.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="administerMenu.jsp"/>
<jsp:include page="adminHeader.jsp"/>
<table border="1">
<form:form modelAttribute="registerAdminUserForm" action="${pageContext.request.contextPath}/admin/registerAdminUser">
<tr>
<td id="registerAdminUser"colspan="3">新規管理者登録</td>
</tr>
<tr>
<td>名前:<br><form:errors path="name" cssStyle="color:red" /></td>
<td><form:input path="name"/></td>
</tr>
<tr>
<td>メールアドレス:<br><form:errors path="email" cssStyle="color:red"/></td>
<td><form:input path="email"/></td>
</tr>
<tr>
<td>パスワード:<br><form:errors path="password" cssStyle="color:red"/></td>
<td><form:password path="password"/></td>
</tr>
<tr>
<td>確認パスワード:<br><form:errors path="checkPassword" cssStyle="color:red" /></td>
<td><form:password path="checkPassword"/></td>
</tr>
<tr>
<td><input type="submit" value="送信"><br><br><input type="reset" value="リセット"></td>
<td><div style="color:red;"><c:out value="${succsess}"/></div></td>
</tr>
</form:form>
</table>
</body>
</html>