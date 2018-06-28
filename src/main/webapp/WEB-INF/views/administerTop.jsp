<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="${pageContect.request.contextPath}/css/style.css">
<style>
body {
	background-color: lavender;
}

div#menu {
	float: left;
	width: 250px;
	height: 800px;;
	background-color: black;
	color: white;
}

* {
	margin: 0px;
	padding: 0px;
}

div#main {
	float: left;
	width: 250px;
	height: 350px;
	background-color: red;
}

div#head {
	margin: 0px;
	padding: 0px;
	width: auto;
	height: 80px;
	background-color: skyblue;
}

div#Top {
	font-size: 32px;
	text-align: center;
	margin-right: auto;
	margin-left: auto;
}

table {
	margin-right: auto;
	margin-left: auto;
}

div#adminItemList {
	height: 100%;
	margin-right: auto;
	margin-left: auto;
	background-color: lavender;
}

a.menuLink:link {
	color: white;
}

a.menuLink:visited {
	color: white;
}

a.menuLink:hover {
	color: white;
}
</style>
<meta charset="UTF-8">
<title>管理者TOPメニュー</title>

<style>
#base {
	width: 300px;
	height: 300px;
	position: relative;
}

.clock {
	width: 300px;
	height: 300px;
	position: absolute;
	top: 0px;
	left: 0px;
}
</style>

</head>
<body>
	<jsp:include page="administerMenu.jsp" />
	<jsp:include page="adminHeader.jsp" />

	<div
		style="width: 600px; margin-right: auto; margin-left: auto; font-size: 200%;">
		おかえりなさいませ、ご主人様<br>
		<div id="base" style="position: absolute; top: 150px; right: 100px">
			<img src="${pageContext.request.contextPath}/img/dial.png"
				class="clock">
			<!--文字盤-->
			<img src="${pageContext.request.contextPath}/img/hour.png" id="hour"
				class="clock">
			<!--短針-->
			<img src="${pageContext.request.contextPath}/img/minute.png"
				id="minute" class="clock">
			<!--長針-->
			<img src="${pageContext.request.contextPath}/img/second.png"
				id="second" class="clock">
			<!--秒針-->
		</div>
		<script>
			var time;
			var hour = document.getElementById("hour");
			var minute = document.getElementById("minute");
			var second = document.getElementById("second");
			function main() {
				time = new Date();
				hour.style.transform = "rotate("
						+ (time.getHours() * 30 + time.getMinutes() * 0.5)
						+ "deg)";
				minute.style.transform = "rotate(" + (time.getMinutes() * 6)
						+ "deg)";
				second.style.transform = "rotate(" + (time.getSeconds() * 6)
						+ "deg)";
				setTimeout(main, 1000 - time.getMilliseconds());
			}
			main();
		</script>

		<p id="Clock1" style="display: inline"></p>
		<script type="text/javascript">
			setInterval('showClock1()', 1000);
			function showClock1() {
				var DWs = new Array('Sun.', 'Mon.', 'Tue.', 'Wed.', 'Thu.',
						'Fri.', 'Sat.');
				var Now = new Date();
				var YY = Now.getYear();
				if (YY < 2000) {
					YY += 1900;
				}
				var MM = set0(Now.getMonth() + 1);
				var DD = set0(Now.getDate());
				var DW = DWs[Now.getDay()];
				var hh = set0(Now.getHours());
				var mm = set0(Now.getMinutes());
				var ss = set0(Now.getSeconds());
				var RTime1 = ' ' + YY + '.' + MM + '.' + DD + ' ' + DW + ' '
						+ hh + ':' + mm + ':' + ss + ' ';
				document.getElementById("Clock1").innerHTML = "現在は<br>"
						+ RTime1 + "です";
			}
			function set0(num) {
				var ret;
				if (num < 10) {
					ret = "0" + num;
				} else {
					ret = num;
				}
				return ret;
			}
		</script>

		<h1 align="center">
			<c:out value="${year}年 ${month}月" />
		</h1>
		<table border="1">
			<tr>
				<th style="color: red">日</th>
				<th>月</th>
				<th>火</th>
				<th>水</th>
				<th>木</th>
				<th>金</th>
				<th style="color: blue">土</th>
			</tr>
			<c:forEach var="week" items="${weekList}">
				<tr>
					<c:forEach var="day" items="${week}" end="0">
						<td style="text-align: right; color: red"><c:out
								value="${day}" /></td>
					</c:forEach>
					<c:forEach var="day" items="${week}" begin="1" end="5">
						<td style="text-align: right;"><c:out value="${day}" /></td>
					</c:forEach>
					<c:forEach var="day" items="${week}" begin="6">
						<td style="text-align: right; color: blue"><c:out
								value="${day}" /></td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="base" style="position:relative;bottom:500px;left:200px;">
	<img src="${pageContext.request.contextPath}/img/character_cabbage.png" id="person"
				class="vegetable">
	</div>
</body>
</html>