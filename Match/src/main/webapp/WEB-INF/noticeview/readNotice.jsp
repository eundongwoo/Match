<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 읽기</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1 nitial-scale=1, user-scalable=no">
<noscript>
	<link rel="stylesheet" href="/Match/css/noscript.css" />
</noscript>
<link rel="stylesheet" href="/Match/css/main.css">
<!-- Scripts -->
		<script src="/Match/js/jquery.min.js"></script>
		<script src="/Match/js/jquery.scrolly.min.js"></script>
		<script src="/Match/js/jquery.scrollex.min.js"></script>
		<script src="/Match/js/browser.min.js"></script>
		<script src="/Match/js/breakpoints.min.js"></script>
		<script src="/Match/js/util.js"></script>
		<script src="/Match/js/main.js"></script>
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
		
		
		<!-- Main -->
		<div id="main" class="alt">
		
		<!-- One -->
			<section id="one">
				<div class="inner">
				<header class="major">
					<h1 align="center">공지게시판</h1>
				</header>
				
	
			<table border="1" width="50%">
			
			<tr>
				<td>번호</td>
				<td>${notice.number}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${notice.writer.name}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><c:out value="${notice.title}" /></td>
			</tr>
			<tr>
				<td>내용</td>
				<td>${notice.content}</td>
			</tr>
			<tr>
				<td colspan="2">
				<c:set var="pageNo" value="${empty param.pageNo ?'1': param.pageNo}" />
					<a href="notice_list.do?pageNo=${pageNo}">[목록]</a>
					
					<!-- authUser과 article writer id가 같을때 수정 삭제가 보인다. -->
					<c:if test="${authUser.id == notice.writer.id}">
					<a href="notice_modify.do?no=${notice.number}">[게시글수정]</a>
					<a href="notice_delete.do?no=${notice.number}" onclick = "return confirm('삭제 하시겠습니까?')">[게시글삭제]</a>
					</c:if>
				</td>
			</tr>
			</table>
			</div>
		</section>
		</div>
	</div>
	
</body>
</html>