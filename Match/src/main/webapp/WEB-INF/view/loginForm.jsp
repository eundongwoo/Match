<%@page import="member.service.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1 nitial-scale=1, user-scalable=no">
<link rel="stylesheet" href="/Match/css/main.css">
<style type="text/css">
#wrap
{
	width: 70%;
	overflow: hidden;
	margin: 0 auto;
}
#wrap div:first-child {
	width: 70%;
	box-sizing:border-box;
	float: left;
}
#wrap div:last-child {
	width: 70%;
	
	box-sizing:border-box;
	float: center;
}  
</style>
</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Header -->
		<header id="header" class="alt">
			<a href="/Match/main.jsp" class="logo"><strong>matching</strong>
				<span>kick together</span></a>
			<nav>
				<a href="#menu">Menu</a>
			</nav>
		</header>

		<!-- Menu -->
		<nav id="menu">
			<jsp:include page="/WEB-INF/view/nav.jsp" />
		</nav>
		<!-- One -->
		<section id="one">
			<div id="wrap">
				<header style= "text-align: center">
					<h1 align="center" >로그인</h1>
				</header>
						
<div id="wrap" style="text-align: center">
<form action="login.do" method="post" class="login-form" >
<c:if test="${errors.idOrPwNotMatch}">
</c:if>

<p>
	<input type="text" name="id" value="${param.id}" placeholder="ID">
	<c:if test="${errors.idOrPwNotMatch }">
	아이디와 암호가 일치하지 않습니다.
	</c:if>
</p>

<p>
	<input type="password" name="password" placeholder="Password" >
	<c:if test="${errors.password}">
	암호를 입력하세요.
	</c:if>
</p>
<input type="submit" value="로그인">
</form>
</div>
</div>
</section>
</div>
	<!-- Scripts -->
	<script src="/Match/js/jquery.min.js"></script>
	<script src="/Match/js/jquery.scrolly.min.js"></script>
	<script src="/Match/js/jquery.scrollex.min.js"></script>
	<script src="/Match/js/browser.min.js"></script>
	<script src="/Match/js/breakpoints.min.js"></script>
	<script src="/Match/js/util.js"></script>
	<script src="/Match/js/main.js"></script>
</body>
</html>