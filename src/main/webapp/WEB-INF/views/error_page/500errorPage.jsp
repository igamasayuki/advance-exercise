<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script>
function com(x){
	if(x==null){
		document.getElementById("admin").id="non";
	}
	
	if(x=='admin'){
		document.getElementById("user").id="non";
	}
}
</script>
<style>
#non{
 display:none;
}
</style>
<meta charset="UTF-8">
<title>500エラー画面</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body onLoad="com(location.pathname.match(/admin/));">
<h3>以下のいずれかの理由でシステムの操作を中断しました。</h3>
<div class="center-block">
<pre>
・不正な操作が行われた
・長い時間操作を行わなかった
・現在システムメンテナンス中

しばらく経ってからもう一度操作してください。
</pre><br>

<img src="${pageContext.request.contextPath}/img/king-kyabetu.png"><br>

<a id="user" href="${pageContext.request.contextPath}/user/viewItemList"
			 			style="background-color:red;color:white;
						width:200px;height:40px;border-style:outset;border-color:buttonface;
			 			border-image:initial;">商品一覧画面に戻る</a>
			 			<br>
			 			<br>
<a id="admin" href="${pageContext.request.contextPath}/adminuserlogin/index"
			 			style="background-color:red;color:white;
						width:200px;height:40px;border-style:outset;border-color:buttonface;
			 			border-image:initial;">管理画面TOP</a>
			 			<br>
			 			<br>
<a href="javascript:history.go(-1)">[前の画面に戻る]</a>
</div>

<jsp:include page="../footter.jsp"/>
</body>
</html>