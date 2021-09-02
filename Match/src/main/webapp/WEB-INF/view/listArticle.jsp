<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1 nitial-scale=1, user-scalable=no">
<noscript>
	<link rel="stylesheet" href="/Match/css/noscript.css" />
</noscript>
<link rel="stylesheet" href="/Match/css/main.css">
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
			<div class="inner">
				<header class="major">
					<h1 align="center">자유게시판</h1>
				</header> 	
				
				<table border="1">
					<tr>
						<td colspan="4"><a href="article_write.do">[게시글쓰기]</a></td>
					</tr>
					<tr>
						<td>번호</td>
						<td>제목</td>
						<td>작성자</td>
						<td>조회수</td>
					</tr>
					<c:if test="${articlePage.hasNoArticles() }">
						<tr>
							<td colspan="4">게시글이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach var="article" items="${articlePage.content }">
						<tr>
							<td>${article.number}</td>
							<td><a

								href="article_read.do?no=${article.number}&pageNo=${articlePage.currentPage}">
									<c:out value="${article.title }" />

							</a></td>
							<td>${article.writer.name }</td>
							<td>${article.readCount }</td>
						</tr>
					</c:forEach>

					<c:if test="${articlePage.hasArticles()}">
						<tr>
							<td colspan="4"><c:if test="${articlepage.startPage > 5 }">
									<a href="article_list.do?pageNo=${articlePage.startPage - 5}">[이전]</a>
								</c:if> <c:forEach var="pNo" begin="${articlePage.startPage }"
									end="${articlePage.endPage}">
									<a href="article_list.do?pageNo=${pNo}">[${pNo}]</a>
								</c:forEach> <c:if test="${articlePage.endPage < articlePage.totalPages}">
									<a href="article_list.do?pageNo=${articlePage.startPage + 5 }">[다음]</a>
								</c:if></td>
						</tr>
					</c:if>
				</table>
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