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
	<a href="${pageContext.request.contextPath}/user/viewItemList"
	class="block" id="itemList"
	style="width:200px;height:40px;background-color:red;display:block;
			 text-decoration:none;margin-right:auto;margin-left:auto;
			 color:white;border-style:outset;
			 border-color:buttonface;">商品一覧へ戻る</a>
</div>
<jsp:include page="footter.jsp"/>
</body>
</html>