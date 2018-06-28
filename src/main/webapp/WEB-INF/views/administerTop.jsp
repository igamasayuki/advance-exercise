<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<style>
div#menu{
	float:left;
	width:250px;
	height:800px;;
	background-color:black;
	color:white;
}

*{
	margin:0px;
	padding:0px;
}

div#main{
	float:left;
	width:250px;
	height:350px;
	background-color:red;
}

div#head{
	margin:0px;
	padding:0px;
	width:auto;
	height:80px;
	background-color:skyblue;
}

div#Top{
	font-size:32px;
	text-align:center;
	margin-right:auto;
	margin-left:auto;
}

table{
	margin-right:auto;
	margin-left:auto;
}

div#adminItemList{
	height:100%;
	margin-right:auto;
	margin-left:auto;
	background-color:lavender;
}

a.menuLink:link{
	color:white;
}

a.menuLink:visited{
	color:white;
}

a.menuLink:hover{
	color:white;
}
</style>
<meta charset="UTF-8">
<title>管理者TOPメニュー</title>
</head>
<body>
<jsp:include page="administerMenu.jsp"/>
<jsp:include page="adminHeader.jsp"/>

<div style="width:500px;margin-right:auto;margin-left:auto;font-size: 200%;">
おかえりなさい<br>

<h1>
<c:out value="${year}年 ${month}月" />
</h1>
<table border="1">
<tr>
	<th style="color:red">日</th>
	<th>月</th>
	<th>火</th>
	<th>水</th>
	<th>木</th>
	<th>金</th>
	<th style="color:blue">土</th>
</tr>
<c:forEach var="week" items="${weekList}">
	<tr>
		<c:forEach var="day" items="${week}" end="0">
			<td style="text-align:right;color:red"><c:out value="${day}" /></td>
		</c:forEach>
		<c:forEach var="day" items="${week}" begin="1" end="5">
			<td style="text-align:right;"><c:out value="${day}" /></td>
		</c:forEach>
		<c:forEach var="day" items="${week}" begin="6">
			<td style="text-align:right;color:blue"><c:out value="${day}" /></td>
		</c:forEach>
	</tr>
</c:forEach>
</table>

</div>
</body>
</html>