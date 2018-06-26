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
.imgA:hover{
	transform: scale(1.5);
}

.imgA{
-webkit-transition: all 0.8s;
	transition: all 0.8s;
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
			<table  style="width:1000px;height:400px;
			background-color:white;">

				<tr>
					<td rowspan="5" style="width:560px;height:400px;">
					<img class="imgA"
					src="${pageContext.request.contextPath}/img/<c:out value="${item.imagePath}"/>"></td>
					<td style="text-align:left;">商品名</td>
					<td style="text-align:left;"><c:out
								value="${item.name}" /></td>
				</tr>
				<tr>
					<td style="text-align:left;">価格</td>
					<td style="text-align:left;"><fmt:formatNumber pattern="\###,###" value="${item.price}"/></td>
				</tr>
				<tr>
					<td style="text-align:left;">商品説明</td>
					<td style="text-align:left;"><c:out value="${item.description}" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:left;">
						<form:form modelAttribute="itemForm" action="${pageContext.request.contextPath}/user/addItem">
						<form:errors path="itemForm.*" />
						個数　：<form:input path="quantity"/>
						<form:errors path="quantity" cssStyle="color:red" element="div" />
						<br><br>
						<input type="hidden" name="id" value="${item.id}">
						<input type="submit" value="カートに入れる" style="background-color:blue;
						width:200px;height:40px;color:white;">
						</form:form>
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:left;">
						<a href="${pageContext.request.contextPath}/user/viewItemList"
			 			style="width:200px;height:40px;display:block;
			 			background-color:red;color:white;text-align:center;
			 			border-style:outset;border-color:buttonface;
			 			border-image:initial;"> 商品一覧に戻る</a>
					</td>
				</tr>
			</table>
		</c:if>
		<jsp:include page="footter.jsp"/>
</body>
</html>