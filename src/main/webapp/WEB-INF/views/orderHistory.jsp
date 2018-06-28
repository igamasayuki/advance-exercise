<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<jsp:include page="userHeader.jsp"/>
	<h3>購入履歴</h3>
	
	<div>
		<c:choose>
			<c:when test="${orderList == null}">
				<div class="center-block">
					<c:out value="購入履歴がありません。"/>
				</div>
			</c:when>
			<c:otherwise>
				<div style="margin-right:auto;margin-left:auto;width:auto;height:auto;">
					<table border="1" style="border-collapse: collapse;">
						<tr>
							<th>注文日時</th>
							<th>注文状況</th>
							<th>商品名</th>
							<th>商品価格</th>
							<th>購入個数</th>
							<th>総計(送料込)</th>
						</tr>
							<c:forEach var="order" items="${orderList}">
								<c:forEach var="orderItem" items="${order.orderItemList}" varStatus="num" >
									<tr>
										<c:if test="${num.index==0}">
											<td rowspan="${order.itemListSize}">
												<fmt:formatDate pattern="yyyy/MM/dd" value="${order.orderDate}"/>
											</td>
											<td  rowspan="${order.itemListSize}">
												<c:choose>
													<c:when test="${order.status==1}">未入金</c:when>
													<c:when test="${order.status==2}">入金済み</c:when>
													<c:when test="${order.status==3}">発送済み</c:when>
												</c:choose>
											</td>
										</c:if>
											<td><c:out value="${orderItem.item.name}" /></td>
											<td><fmt:formatNumber value="${orderItem.item.price}" type="CURRENCY" currencySymbol="¥" groupingUsed="ture" maxFractionDigits="0"/></td>
											<td><c:out value="${orderItem.quantity}" /></td>												
										<c:if test="${num.index==0}">
											<td rowspan="${order.itemListSize}">
												<fmt:formatNumber value="${order.totalPrice}" type="CURRENCY" currencySymbol="¥" groupingUsed="ture" maxFractionDigits="0"/>
											</td>
										</c:if>
									</tr>
							</c:forEach>
						</c:forEach>
					</table>
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
</body>
</html>