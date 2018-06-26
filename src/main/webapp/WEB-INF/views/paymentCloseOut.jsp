<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>決済完了画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css"></head>
</head>
<style type="text/css">
a{
	text-decoration: none;
}
</style>
<body>
<jsp:include page="userHeader.jsp"/>
<h1 align="center">決済が完了しました!</h1>
<h3>この度はご注文ありがとうございます。</h3>
<h3>お支払先は、お送りしたメールに記載してありますのでご確認してください。</h3>
<div class="center-block">
	<a href="${pageContext.request.contextPath}/user/viewItemList">一覧画面へ戻る</a>
</div>
</body>
</html>