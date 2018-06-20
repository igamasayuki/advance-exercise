<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/include.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/administerHeader.css" />
<meta charset="UTF-8">
<title>注文一覧画面</title>
</head>
<body>
<jsp:include page="adminHeader.jsp"/>
<jsp:include page="administerMenu.jsp"/>
<div align ="center">
    <h2>注文一覧画面</h2>
        
        
        <table border="1">
<tr>
<th>注文番号</th>
<th>日付</th>
<th>利用者名</th>
<th>現在のステータス</th>
<th>総計(税込)</th>
</tr>


<c:forEach var="orderList" items="${orderList}">
<tr>
<td>
<a href="./orderDetail.jsp"><c:out value="${orderList.orderNumber}"/></a>
</td>
<td>
<fmt:formatDate pattern="yyyy/MM/dd" value="${orderList.orderDate}"/>
</td>
<td>
<c:out value="${orderList.deliveryName}"/>
</td>
<td>
<c:out value="${orderList.status}"/>
</td>
<td>
<fmt:formatNumber pattern="\###,###" value="${orderList.totalPrice}"/>
</td>
</tr>
</c:forEach>
</table>
<br>     
        <a href="administerMenu.jsp">メニューに戻れない</a>

</div>

</body>
</html>