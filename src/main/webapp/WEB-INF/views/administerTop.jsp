<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page import="java.util.Calendar" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${pageContect.request.contextPath}/css/style.css">
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

<%
int intNen; //表示年
int intTuki; //表示月

Calendar cal = Calendar.getInstance();
int intYear = cal.get(Calendar.YEAR); //システムの年
int intMonth = cal.get(Calendar.MONTH); //システムの月
int intDate = cal.get(Calendar.DATE); //システムの日

intNen = intYear;
intTuki = intMonth;

cal.set(intNen, intTuki, 1); //表示月をセット
int intLastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //表示月の日数
int intFirstDay = cal.get(Calendar.DAY_OF_WEEK); //表示月の１日の曜日
int intLastDay = cal.get(Calendar.DATE);
%>

<% 
out.println("<h2><u>"+intNen+"年"+(intTuki+1)+"月</u></h2>");
out.println("<font color=#FF0000>日</font>　月　火　水　木　金　<font color=#0000FF>土</font><br>");

int intIchi = 0; //出力数　カウント用

//１行目の空白出力
for (int i=1; i < intFirstDay; i++){
intIchi++; 
out.println("&nbsp;&nbsp;&nbsp;&nbsp;&thinsp;");
} 

// ７で割った余りが1なら赤、0なら青と改行
for (int i=1; i <=intLastDate ;i++){
intIchi++;

//曜日の色づけ
switch ( intIchi % 7) {
case 0:
out.println("<font color=#0000FF>");
break;
case 1:
out.println("<font color=#FF0000>");
break;
default:
out.println("<font color=#000000>");
}

//本日を太字
if (intNen ==intYear && intTuki==intMonth && i==intDate){
out.println("<b>");
}

//１桁のとき空白を前に挿入 　　出力
if (i < 10){ 
out.println("&nbsp;&thinsp;"+i+"&nbsp;");
}
else { 
out.println( i+"&nbsp;");
}


if (intNen ==intYear && intTuki==intMonth && i==intDate){
out.println("</b>");
}

out.println("</font>");

//土曜日なら改行
if (intIchi % 7 == 0) {
out.println("<br>");
}	
}

%>
</div>
</body>
</html>