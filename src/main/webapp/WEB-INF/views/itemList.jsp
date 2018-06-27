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
	<div style="width:400px;height:300px;background-color:white;margin-right:auto;margin-left:auto;">
	<jsp:include page="slide.jsp" />
	</div>
	<hr>
	<h3>商品一覧</h3>
	
	<div class="center-block">
		<form:form action="${pageContext.request.contextPath}/user/findItem"
			method="post">
			<input type="text" name="word"> 
			<input type="submit"
			value="" 
			style="background-image:url(${pageContext.request.contextPath}/img/serch2.jpeg);
			background-size:cover;width:30px;height:30px;">
			
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
			<table style="width:1250px;background-color:white;">
			<tr>
				<c:forEach var="item" items="${itemList}" begin="${begin}" end="${end/2}">
					<td style="width:300px;">
							<a href="${pageContext.request.contextPath}/user/item_detail?id=${item.id}">
						<c:out value="${item.name}" /></a><br>
						<a href="${pageContext.request.contextPath}/user/item_detail?id=${item.id}">
								<img src="${pageContext.request.contextPath}/img/<c:out value="${item.imagePath}"/>"
								style="max-width:300px;max-height:300px;">
							</a><br>
						<fmt:formatNumber pattern="\###,###" value="${item.price}" />
					</td>
				</c:forEach>
				</tr>
			</table>
			<table style="width:1250px;background-color:white">
			<tr>
				<c:forEach var="item" items="${itemList}" begin="${(end/2)+1}" end="${end}">
					<td style="width:300px;">
							<a href="${pageContext.request.contextPath}/user/item_detail?id=${item.id}">
						<c:out value="${item.name}" /></a><br>
						<a href="${pageContext.request.contextPath}/user/item_detail?id=${item.id}">
								<img src="${pageContext.request.contextPath}/img/<c:out value="${item.imagePath}"/>"
								style="max-width:300px;max-height:300px;">
							</a><br>
						<fmt:formatNumber pattern="\###,###" value="${item.price}" />
					</td>
				</c:forEach>
				</tr>
			</table>
			</div>
			<c:forEach var="number" items="${numberList}">
			<form:form action="${pageContext.request.contextPath}/user/viewItemList"
			modelAttribute="pagingForm">
			<form:hidden path="paging" value="${number}"/>
			<input type="submit" value='<c:out value="${number}"/>'
			style="background-color:palegreen;color:white;float:left;">
			</form:form>
			</c:forEach>
			
		</c:otherwise>
	</c:choose>
	<div class="center-block">
	<a href="javascript:history.go(-1)">[戻る]</a>
	</div>
<jsp:include page="footter.jsp"/>
</body>
</html>