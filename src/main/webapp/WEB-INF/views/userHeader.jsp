<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<html>
<head>
<script>
function changeLoginout(){
	var x=document.getElementById("change");
	x.id="notChange";
	
}
</script>
<style>
#change{

}

#notChange{
	display:none;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div style="width:auto;height:80px;background-color:orange">
<div style="float:right">
<sec:authorize access="hasRole('ROLE_USER') and isAuthenticated()">
							<sec:authentication var="userName" property="principal.user.name" />
								<c:out value="${userName}" />&nbsp;さんこんにちは
								<br>
								<a href="${pageContext.request.contextPath}/user/logout">ログアウト</a>
								<br>
								<script>
								var y=true;
								</script>
						</sec:authorize>
						
			<a id="change" href="${pageContext.request.contextPath}/user/login">ログイン・新規登録</a>
			<script>
			if(y){
			changeLoginout();
			}
			</script>
		
</div>
<div style="float:right;">
<a href="${pageContext.request.contextPath}/user/viewShoppingCart" style="padding-right:30px;">カートの中身を表示する</a>
</div>
<div style="float:left">
<a href="${pageContext.request.contextPath}/user/viewItemList">
<img src="${pageContext.request.contextPath}/img/king-kyabetu.png" alt="きゃべつ王国"></a>
</div>
</div>	
</body>
</html>