<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/adminRegister.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/include.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/administerHeader.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>商品編集画面</title>
</head>
<body>
<jsp:include page="adminHeader.jsp"/>
<jsp:include page="administerMenu.jsp"/>
<h3>商品編集画面</h3>
<p>編集したい商品の情報を入力してください。</p>
<form:form modelAttribute="itemEditingForm" action="${pageContext.request.contextPath}/admin/edit" enctype="multipart/form-data">
	<form:hidden path="id"/>
	<form:label path="name">商品名:</form:label>
	<form:input path="name"/><br>
	<form:errors path="name" cssStyle="color:red" element="div"/><br>
	<form:label path="price">価格　:</form:label>
	<form:input path="price"/><br>
	<form:errors path="price" cssStyle="color:red" element="div"/><br>
	<form:label path="description">説明　:</form:label>
	<form:textarea path="description" cols="40" rows="5"/><br>
	<form:errors path="description" cssStyle="color:red" element="div"/><br>
	画像　:
	<img src="${pageContext.request.contextPath}/img/${imagePath}">
	<input type="hidden" name="beforeImagePath" value="${imagePath}">
	<input type="file" name="imageFile"><br>
	<c:out value="※ファイルはjpegファイルを選択してください"/><br>
	<form:errors path="imageFile" cssStyle="color:red" element="div"/><br>
	<form:checkbox path="deleted"/>
	<form:label path="deleted">削除</form:label><br>
	<input type="submit" value="編集">
</form:form>
<a href="${pageContext.request.contextPath}/adminItemList/">商品一覧画面に戻る</a>

</body>
</html>