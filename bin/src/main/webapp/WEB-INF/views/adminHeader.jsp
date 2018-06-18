<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/adminHeader.css" />
<title>Insert title here</title>
</head>
<body>
<div id="userHeader" align="right">
	<p>こんにちは管理者さん</p>
	<p><a href="${pageContext.request.contextPath}/">ログアウトまだできない</a></p>
</div>
			
<div id="linkHeader" align="left">
	<h1 align ="left"><a href="../user/itemList.html"><img src="../img/rakus.jpg" width="50"
	height="50" alt="ロゴ画像">ＥＣサイトラクス</a></h1>
<div id="title" align="center">
</div>

</body>
</html>