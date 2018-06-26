<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録画面</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/adminRegister.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/include.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/administerHeader.css" />
</head>
<body>
<jsp:include page="administerMenu.jsp"/>
<jsp:include page="adminHeader.jsp"/>
<div class="padding">
<h3 style="position:relative;left:130px;">商品登録画面</h3>
<p>新規で登録したい商品の情報を入力してください。</p>
<form:form modelAttribute="itemRegistrationForm" action="${pageContext.request.contextPath}/admin/register" enctype="multipart/form-data">
	<form:label path="name">商品名:</form:label>
	<form:input path="name"/><br>
	<form:errors path="name" cssStyle="color:red" element="div"/><br>
	<form:label path="price">価格　:<br>(半角数字上限100万円まで)</form:label>
	<form:input path="price"/><br>
	<form:errors path="price" cssStyle="color:red" element="div"/><br>
	<form:label path="description">説明　:</form:label>
	<form:textarea path="description" cols="40" rows="5"/><br>
	<form:errors path="description" cssStyle="color:red" element="div"/><br>
	画像　:
	<input type="file" name="imageFile" accept="image/jpeg"><br>
	<c:out value="※ファイルはJPEGファイルを選択してください"/><br>
	<form:errors path="imageFile" cssStyle="color:red" element="div"/><br>
	<form:checkbox path="deleted"/>
	<form:label path="deleted">削除</form:label><br>
	<input type="submit" value="登録" style="background-color:blue;color:white;
	width:200px;height:40px;margin-bottom:30px;">
</form:form>
<a href="${pageContext.request.contextPath}/adminItemList/"
style="background-color:red;color:white;width:200px;height:40px;
border-style:outset;">商品一覧画面に戻る</a>
</div>
</body>
</html>