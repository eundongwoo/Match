<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1 nitial-scale=1, user-scalable=no">
<link rel="stylesheet" href="/Match/css/main.css">
<style>
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
		<section id="one">
			<div id="wrap">
		<header style= "text-align: center">
			<h1 align="center">회원가입</h1>
		</header>

<div id="wrap" style="text-align: center">
<form action="join.do" method="post" class="register-form">
<input type="text" name="id" value="${param.id}" placeholder="ID">
<c:if test="${errors.id}">아이디를 입력하세요.</c:if>
<c:if test="${errors.duplicateId}">이미 사용중인 아이디 입니다.</c:if>
<br>
<input type="text" name="name" value="${param.name}" placeholder="name">
<c:if test="${errors.name}">이름을 입력하세요.</c:if>
<br>

<input type="password" name="password" placeholder="Password">
<c:if test="${errors.password}">패스워드를 입력하세요.</c:if>
<br>

<input type="password" name="confirm" placeholder="PasswordConfirm">
<c:if test="${errors.confirm}">패스워드 확인을 입력하세요.</c:if>
<c:if test="${errors.notMatch}">패스워드와 확인이 일치하지 않습니다.</c:if>
<br>

<input type="text" name="tel" value="${param.tel}" placeholder="ex) 010-1234-5678">
<c:if test="${errors.tel}">전화번호를 입력하세요.</c:if>
<br>
<input type="submit" value="가입하기">
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