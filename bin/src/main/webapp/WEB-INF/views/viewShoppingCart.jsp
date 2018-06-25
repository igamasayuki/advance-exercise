<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカートの中身</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<jsp:include page="userHeader.jsp" />
	<div class="link-right" align="right">
	<a href="${pageContext.request.contextPath}/user/viewShoppingCart">カートの中身を表示する</a>
	</div>
<h3>ショッピングカート一覧</h3>
<div class="center-block">
<c:choose>
		<c:when test="${orderItemList == null}">
			<div class="center-block">
				<c:out value="商品がありません。" />
			</div>
		</c:when>
		<c:otherwise>
 <table border ="1">
            <tr>
                <th colspan="2">商品名</th>
                <th>価格</th>
                <th>個数</th>
                <th></th>
            </tr>
            <c:forEach var="orderItem" items="${orderItemList}">
            <tr>
				<td><a href="${pageContext.request.contextPath}/user/item_detail?id=${orderItem.item.id}">
					<img src="${pageContext.request.contextPath}/img/<c:out value="${orderItem.item.imagePath}"/>"alt="商品画像"></a></td>
                <td><a href="${pageContext.request.contextPath}/user/item_detail?id=${orderItem.item.id}"><c:out value="${orderItem.item.name}"/></a></td>
                <td><fmt:formatNumber pattern="\###,###" value="${orderItem.item.price}" /></td>
                <td><c:out value="${orderItem.quantity}"/></td>
                <td>
                    <form action="viewShoppingCart.html" method="post">
                        <input type="hidden" name="item.id" value="1">
                        <input type="submit" value="削除">
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table><br>
	    <div class="center-block"><a href="${pageContext.request.contextPath}/userPayment/">決済へ</a></div>
        </c:otherwise>
</c:choose>
</div>

</body>
</html>