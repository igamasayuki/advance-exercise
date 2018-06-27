<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>500エラー画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<h3>以下のいずれかの理由でシステムの操作を中断しました。</h3>
<div class="center-block">
<pre>
・不正な操作が行われた
・長い時間操作を行わなかった
・現在システムメンテナンス中

しばらく経ってからもう一度操作してください。
</pre><br>

<img src="${pageContext.request.contextPath}/img/king-kyabetu.png"><br>

<a href="${pageContext.request.contextPath}/user/viewItemList"
			 			style="background-color:red;color:white;
						width:200px;height:40px;border-style:outset;border-color:buttonface;
			 			border-image:initial;">商品一覧画面に戻る</a>
			 			<br>
			 			<br>
<a href="javascript:history.go(-1)">[前の画面に戻る]</a>
</div>

<jsp:include page="../footter.jsp"/>
</body>
</html>