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
div#non{
	display:none;
}
div#menuClose{
	float:left;
	background-color:yellow;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
function menu(){
	document.getElementById("non").id="menuClose";
	document.getElementById("menu").id="non";
}

function menuClose(){
	document.getElementById("non").id="menu";
	document.getElementById("menuClose").id="non";
}
</script>
<div id="menu" style="text-align:center;width:170px;">
    <h2><input type="button" onClick="menu()" value="メニュー"
    style="background-color:black;color:white;border-style:none;margin-bottom:50px;margin-top:40px;width:170px;"></h2>
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
<div id="non" style="text-align:center;width:170px;">
 <h2><input type="button" onClick="menuClose()" value="メニュー"
    style="background-color:black;color:white;border-style:none;width:170px;height:100px;
    "></h2>
</div>

</body>
</html>