<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/include.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/administerHeader.css" />
<meta charset="UTF-8">
<title>商品一覧</title>
</head>
<body>
<jsp:include page="administerMenu.jsp"/>
<jsp:include page="adminHeader.jsp"/>
<div id="adminItemList">
<h3>商品一覧</h3>
<form:form action="${pageContext.request.contextPath}/adminItemList/index">
	<input type="text" name="keyword" value="${keyword}">
	<input type="submit" value="検索する">
</form:form>

<c:choose>
	<c:when test="${items!=null}">
		<table border="1">
			<tr>
				<th colspan="2">商品名</th>
				<th>価格</th>
				<th>商品説明</th>
				<th></th>
			</tr>
			<c:forEach var="item" items="${items}">
				<tr>
					<td><img src="${pageContext.request.contextPath}/img/<c:out value="${item.imagePath}"/>"></td>
					<td><c:out value="${item.name}"/></td>
					<td><fmt:formatNumber pattern="\###,###" value="${item.price}"/></td>
					<td><c:out value="${item.description}"/></td>
					<td>
						<form:form action="${pageContext.request.contextPath}/itemEditing/?id=${item.id}">
							<input type="submit" value="編集">
						</form:form>
						<c:choose>
							<c:when test="${item.deleted}">
								<form:form action="${pageContext.request.contextPath}/itemDeletion/delete?id=${item.id}">
									<input type="hidden" name="deleted" value="false">
									<input type="submit" value="再表示">
								</form:form>
							</c:when>
							<c:otherwise>
								<form:form action="${pageContext.request.contextPath}/itemDeletion/delete?id=${item.id}">
									<input type="hidden" name="deleted" value="true">
									<input type="submit" value="削除">
								</form:form>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
	<c:otherwise>
		商品がありません。
	</c:otherwise>
</c:choose>
</div>
</body>
</html>