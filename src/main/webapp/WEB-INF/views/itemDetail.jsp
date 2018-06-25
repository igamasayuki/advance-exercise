<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
a{
	text-decoration: none;
}

</style>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<meta charset="UTF-8">
<title>商品詳細</title>
</head>
<body>
<jsp:include page="userHeader.jsp" />
<div class="link-right" align="right">
	</div>
		<h3>商品詳細</h3>
		<c:if test="${item != null}">
			<table border="1" style="width:800px;height:400px;">

				<tr>
					<td rowspan="3"><img style="width:530px;height:394px;" src="${pageContext.request.contextPath}/img/<c:out value="${item.imagePath}"/>"></td>
					<td>商品名</td>
					<td><c:out
								value="${item.name}" /></td>
				</tr>
				<tr>
					<td>価格</td>
					<td><fmt:formatNumber pattern="\###,###" value="${item.price}"/></td>
				</tr>
				<tr>
					<td>商品説明</td>
					<td><c:out value="${item.description}" /></td>
				</tr>
			</table>
		</c:if>
		<br><br>

		<div class="center-block">
		<form:form modelAttribute="itemForm" action="${pageContext.request.contextPath}/user/addItem">
			<form:errors path="itemForm.*" />
			個数　：<form:input path="quantity"/>
			<form:errors path="quantity" cssStyle="color:red" element="div" />
			<br> <br>


		<input type="hidden" name="id" value="${item.id}">
		
		<input type="submit" value="カートに入れる" style="background-color:blue;
		width:200px;height:40px;color:white;border-style:none;">
		</form:form>
		<br><br>
		
			 <a href="${pageContext.request.contextPath}/user/viewItemList"
			 style="width:200px;height:40px;display:block;
			 background-color:red;color:white;text-align:center;
			 margin-right:auto;margin-left:auto;"> 商品一覧に戻る</a>
</div>
</body>
</html>