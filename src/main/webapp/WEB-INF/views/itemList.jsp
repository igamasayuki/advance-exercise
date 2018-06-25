<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<meta charset="UTF-8">
<style type="text/css">
a{
text-decoration: none;
}
</style>
<title>商品一覧</title>
</head>
<body>
	<jsp:include page="userHeader.jsp" />
	<div style="width:100px;height:100px;background-color:yellow;margin-right:auto;margin-left:auto;">
	<jsp:include page="slide.jsp" />
	</div>
	<hr>
	<h3>商品一覧</h3>
	
	<div class="center-block">
		<form:form action="${pageContext.request.contextPath}/user/findItem"
			method="post">
			<input type="text" name="word"> <input type="submit"
				value="検索する">
		</form:form>
	<hr>
</div>
	<c:choose>
		<c:when test="${itemList == null}">
			<div class="center-block">
				<c:out value="商品がありません。" />
			</div>
		</c:when>
		<c:otherwise>
			<div style="margin-right:auto;margin-left:auto;width:auto;height:800px;">
				<c:forEach var="item" items="${itemList}">
					<span style="float:left;padding-left:30px;margin-bottom:30px;">
							<a href="${pageContext.request.contextPath}/user/item_detail?id=${item.id}">
						<c:out value="${item.name}" /></a><br>
						<a href="${pageContext.request.contextPath}/user/item_detail?id=${item.id}">
								<img src="${pageContext.request.contextPath}/img/<c:out value="${item.imagePath}"/>">
							</a><br>
						<fmt:formatNumber pattern="\###,###" value="${item.price}" />
					</span>
				</c:forEach>
			</div>
		</c:otherwise>
	</c:choose>
<div style="width:auto;height:250px;background-color:#80808014;text-align:center">
<span style="margin-right:30px;position:relative; top:50px;">当サイトについて</span>
<span style="margin-right:30px;position:relative; top:50px;">プライバシーポリシー</span>
<span style="margin-right:30px;position:relative; top:50px;">特定商取引法に基づく表記</span>
<span style="margin-right:30px;position:relative; top:50px;">お問い合わせ</span>
<h1 style="position:relative; top:50px;">キャベツ王国</h1>
</div>
</body>
</html>