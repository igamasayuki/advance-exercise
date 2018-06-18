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
<div align="center">
  <h2>ログイン</h2> 
  <form:form modelAttribute="loginForm" action="${pageContext.request.contextPath}/kakiki/adminUserMenu">
  	名前:<form:input path="mailAddress"/><br>
 	パスワード:<form:password path="password"/><br>
 	<input type="submit" value="ログイン">
  </form:form>
</div>

</body>
</html>