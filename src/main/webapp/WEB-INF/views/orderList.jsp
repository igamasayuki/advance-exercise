<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/adminHeader.css" />
<meta charset="UTF-8">
<title>注文一覧画面</title>
</head>
<body>
<jsp:include page="adminHeader.jsp"/>
<div align ="center">
    <h2>注文一覧画面</h2>
        
        
        <table border="1">
<tr>
<td>注文番号</td>
<td>日付</td>
<td>利用者名</td>
<td>現在のステータス</td>
<td>総計(税込)</td>
</tr>

<tr>
<c:forEach var="orderList" items="${orderList}">
<td>
<a href="./orderDetail.jsp"><c:out value="${orderList.orderNumber}"/></a>(決済の日付+連番)
</td>
<td>
<c:out value="${orderList.id}"/>(決済の日付)
</td>
<td>
<c:out value="${orderList.userId}"/>(userIdで結合して名前表示)
</td>
<td>
<c:out value="${orderList.status}"/>
</td>
<td>
<c:out value="${orderList.totalPrice}"/>
</td>
</c:forEach>
</tr>
</table>
        
        <a href="administerMenu.jsp">メニューに戻れない</a>

</div>

</body>
</html>