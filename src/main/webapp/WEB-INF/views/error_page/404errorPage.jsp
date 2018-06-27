<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404エラー画面</title>
</head>
<body>


<h3>404 Not Found</h3>
<div class="center-block">
<img src="${pageContext.request.contextPath}/img/king-kyabetu.png"><br>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<pre>
<h4>お探しのページは見つかりませんでした。</h4>

お探しのページは一時的にアクセスができない状況にあるか、
移動もしくは削除された可能性があります。

</pre>
<br>
<a href="${pageContext.request.contextPath}/user/viewItemList"
			 			style="background-color:red;color:white;
						width:200px;height:40px;">商品一覧画面に戻る</a><br>
						<br>
<a href="javascript:history.go(-1)">[戻る]</a>
</div>

<jsp:include page="../footter.jsp"/>
</body>
</html>