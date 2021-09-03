<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>자유게시판</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="/Match/css/main.css">
</head>
<body class="is-preload">
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

		<!-- Main -->
		<div id="main" class="alt">

			<!-- One -->
			<section id="one">
				<div class="inner">
					<header class="major">
						<h1 align="center">자유게시판</h1>
					</header>
					<!-- 게시글을 등록했습니다. <br> --> 
					${ctxPath = pageContext.request.contextPath;''}
					
					<a href="${ctxPath}/article_list.do" onclick="return confirm('목록보시겠습니까?');">[게시글목록보기]</a>
					 <a	href="${ctxPath}/article_read.do?no=${newArticleNo}">[게시글내용보기]</a>							 
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