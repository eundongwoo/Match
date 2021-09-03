<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 목록</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1 nitial-scale=1, user-scalable=no">
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
						<h1 align="center">공지게시판</h1>
					</header>
		
<table border="1">
	<c:if test="${authUser.author eq '1'}">
	<tr>
		<td colspan ="4"><a href="notice_write.do">[공지글쓰기]</a></td>
	</tr>
	</c:if>
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>조회수</td>
	</tr>
	
<c:if test="${noticePage.hasNoNotices() }">
	<tr>
		<td colspan="4">게시글이 없습니다.</td>
	</tr>
</c:if>
<c:forEach var="notice" items="${noticePage.content }">
	<tr>
		<td>${notice.number}</td>
		<td>
		<a href="notice_read.do?no=${notice.number}&pageNo=${noticePage.currentPage}">
			<c:out value="${notice.title }" />
		</a>
		</td>
		<td>${notice.writer.name }</td>
		<td>${notice.readCount }</td>
	</tr>
</c:forEach>

<c:if test="${noticePage.hasNotices()}">
	<tr>
		<td colspan="4">
			<c:if test="${noticepage.startPage > 5 }">
			<a href="notice_list.do?pageNo=${noticePage.startPage - 5}">[이전]</a>
			</c:if>
			<c:forEach var="pNo" begin="${noticePage.startPage }" end="${noticePage.endPage}">
				<a href="notice_list.do?pageNo=${pNo}">[${pNo}]</a>
			</c:forEach>
			<c:if test="${noticePage.endPage < noticePage.totalPages}">
			<a href="notice_list.do?pageNo=${noticePage.startPage + 5 }">[다음]</a>
			</c:if>
		</td>
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