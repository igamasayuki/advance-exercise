<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカートの中身</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
<style type="text/css">
th{
height:30px;
}
a.block{
	text-decoration:none;
	display:block;
}

span#payment{
	width:200px;
	height:40px;
	background-color:lime;
	color:white;
}
</style>
</head>
<body>
	<jsp:include page="userHeader.jsp" />
	<h3>ショッピングカート</h3>
	<div class="center-block">
		<c:choose>
			<c:when test="${orderItemList == null}">
				<div class="center-block">
					<c:out value="商品がありません。" />
				</div>
			</c:when>
			<c:otherwise>
				<table style="width:1000px;background-color:white;margin-bottom:0px;">
					<tr>
						<th style="width:390px;"></th>
						<th style="width:230px;
						vertical-align:bottom;">商品名</th>
						<th style="width:170px;
						vertical-align:bottom;">価格</th>
						<th style="width:110px;
						vertical-align:bottom;">個数</th>
						<th style="width:60px;"></th>
					</tr>
					</table>
					<c:forEach var="orderItem" items="${orderItemList}">
					<hr>
					<table style="width:1000px;background-color:white;">
						<tr>
							<td style="width:390px;height:220px;"><a
								href="${pageContext.request.contextPath}/user/item_detail?id=${orderItem.item.id}">
									<img
									src="${pageContext.request.contextPath}/img/<c:out value="${orderItem.item.imagePath}"/>"
									alt="商品画像"
									style="max-width:300px;max-height:300px;">
							</a></td>
							<td style="width:230px;font-size:32px;"><a
								href="${pageContext.request.contextPath}/user/item_detail?id=${orderItem.item.id}"><c:out
										value="${orderItem.item.name}" /></a></td>
							<td style="width:170px;font-size:32px;"><fmt:formatNumber pattern="\###,###"
									value="${orderItem.item.price}" /></td>
							<td style="width:110px;font-size:32px;"><c:out value="${orderItem.quantity}" /></td>
							<td style="width:60px;font-size:32px;"><form:form
									action="${pageContext.request.contextPath}/user/deletionOrderItem">
									<input type="hidden" name="id" value="${orderItem.id}">
									<input type="submit" value="削除" style="background-color:blue;
		width:60px;height:40px;color:white;">
								</form:form></td>
						</tr>
					</table>
					<hr>
					</c:forEach>
					
				
				<br>
					<div class="center-block">
					<a href="${pageContext.request.contextPath}/userPayment/"
					 style="width:200px;height:40px;background-color:lime;display:block;
					 text-decoration:none;margin-right:auto;margin-left:auto;
					 color:white;margin:auto;border-style:outset;
					 border-color:buttonface;">
					決済へ</a>
					</div>
				
			</c:otherwise>
		</c:choose>
		<div class="center-block">
			<a href="${pageContext.request.contextPath}/user/viewItemList"
			class="block" id="itemList"
			style="width:200px;height:40px;background-color:red;display:block;
					 text-decoration:none;margin-right:auto;margin-left:auto;
					 color:white;border-style:outset;
					 border-color:buttonface;">商品一覧へ</a>
		</div>
	</div>
<jsp:include page="footter.jsp"/>
</body>
</html>