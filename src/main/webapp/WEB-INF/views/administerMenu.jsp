<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
a{
text-decoration: none;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
function menu(){
	alert(1);
	alert(document.getElementById("menu").id);
	document.getElementById("menu").id="non";
}
</script>
<div id="menu" style="text-align:center;">
    <h2><input type="button" onClick="alert(1);" value="メニュー"
    style="background-color:black;color:white;border-style:none;"></h2>
		<a class="menuLink" href="${pageContext.request.contextPath}/admin/viewAdminTop">TOP</a><br><br>
        <a class="menuLink" href="${pageContext.request.contextPath}/admin/show_view">商品を登録</a><br><br>
        <a class="menuLink" href="${pageContext.request.contextPath}/admin/adminItemList">商品一覧</a><br><br>
        <a class="menuLink" href="${pageContext.request.contextPath}/admin/viewOrderList">注文一覧</a><br><br>
        <a class="menuLink" href="${pageContext.request.contextPath}/admin/viewRegisterAdmin">新規登録</a>
        <br>
        <br>
        <br>
        <a class="menuLink" href="${pageContext.request.contextPath}/adminlogout">ログアウト</a>
</div>

</body>
</html>