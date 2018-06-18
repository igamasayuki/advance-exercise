<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<mete charset="UTF-8">
<title>商品一覧</title>
</head>
<body>

<h3>商品一覧</h3>

<form action="${pageContext.request.contextPath}/findItem">
<input type="text" name="word">
<input type="submit" value="検索する">
</form>

<table border="1">
<tr>
<td colspan="2">商品名</td>
<td>価格</td>
</tr>

<tr>
<c:forEach var="itemList" items="${itemList}">
<td>
<c:out value="${itemList.name}"/>
</td>
<td>
<c:out value="${itemList.imagePath}"/>
</td>
<td>
<c:out value="${itemList.price}"/>
</td>
</c:forEach>
</tr>
</table>
</body>
</html>