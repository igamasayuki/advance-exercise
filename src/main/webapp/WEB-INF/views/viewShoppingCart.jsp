<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカートの中身</title>
</head>
<body>
<h3>ショッピングカート一覧</h3>

 <table border ="1">
            <tr>
                <th colspan="1">商品名</th>
                <th>価格</th>
                <th>個数</th>
                <th></th>
            </tr>
            <c:forEach var="orderItem" items="${orderItem}">
            <tr>
				<td><a href="${pageContext.request.contextPath}/item_detail/item_detail?id=${orderItem.id}">
					<img src="${pageContext.request.contextPath}/img/<c:out value="${orderItem.imagePath}"/>"alt="商品画像"></a></td>
                <td><a href="itemDetail.html"><c:out value="${orderItem.name}"/></a></td>
                <td><fmt:formatNumber pattern="\###,###" value="${orderItem.price}" /></td>
                <td>1個</td>
                <td>
                    <form action="viewShoppingCart.html" method="post">
                        <input type="hidden" name="item.id" value="1">
                        <input type="submit" value="削除">
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table><br>

    <div class="center-block"><a href="${pageContext.request.contextPath}/決済画面のURL">決済へ</a></div>

</body>
</html>