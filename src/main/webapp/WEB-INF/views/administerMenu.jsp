<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/adminMenu.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="menu">
    <h2>管理者メニュー画面</h2>
        <a href="${pageContext.request.contextPath}/item_registration/show_view">商品を登録</a><br><br>
        <a href="${pageContext.request.contextPath}/adminItemlist/">商品一覧</a><br><br>
        <a href="${pageContext.request.contextPath}/orderList">注文一覧</a><br><br>
        <a href="${pageContext.request.contextPath}/registerAdmin/viewRegisterAdmin">新規登録</a>
        <br>
        <br>
        <br>
        <a href="${pageContext.request.contextPath}/adminLogout/viewAdminLogout">ログアウト</a>

</div>

</body>
</html>