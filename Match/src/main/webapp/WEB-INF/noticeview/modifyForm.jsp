<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/Match/ckeditor/ckeditor.js"></script>

</script>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	if ("${errors.content }"){
		alert('내용을 입력해주세요.');
	}
</script>
<meta charset="UTF-8">
<title>공지글 수정</title>
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
				<header class="major">
					<h1 align="center">공지게시판</h1>
				</header>
				</div>
<form action="notice_modify.do" method="post">
<input type= "hidden" name="no" value="${modReq.noticeNumber}">
<p>
	번호:<br/>${modReq.noticeNumber}
</p>
<p>
	제목:<br/><input type="text" name="title" value="${modReq.title }">
	<c:if test="${errors.title}">제목을 입력하세요.</c:if>
</p>
<p>
	<textarea class="form-control" id="p_content" name="content">${modReq.content}</textarea>
	<script type="text/javascript">
 	CKEDITOR.replace('p_content'
                , {height: 500,
                	filebrowserUploadUrl: '/Match/notice_upload.do'
                 });
 	</script>
</p>
<input type="submit" value="글 수정">
</form>
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