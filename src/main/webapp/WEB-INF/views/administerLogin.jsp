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
<jsp:include page="adminHeader.jsp"/><br>
<div align="center">
  <h2>ログイン</h2> 
  <form:form modelAttribute="loginAdminForm" action="${pageContext.request.contextPath}/adminLogin/fromLogintoMenu">
  	<form:errors path="mailAddress" cssStyle="color:red"/><br>
  	メールアドレス:<form:input path="mailAddress"/><br>
  	
  	<form:errors path="password" cssStyle="color:red"/>
 	パスワード:<form:password path="password"/><br>
 	<input type="submit" value="ログイン">
  </form:form>
</div>

</body>
</html>