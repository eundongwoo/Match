<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String message=(String)request.getAttribute("message");
	System.out.print("메세지----->"+message);
	if(message!=null)
	{
%>
<script>
			alert("<%=message%>");
</script>
<%} %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MainPage</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1 nitial-scale=1, user-scalable=no">
<noscript>
	<link rel="stylesheet" href="/Match/css/noscript.css" />
</noscript>
<link rel="stylesheet" href="/Match/css/main.css">
</head>

<body>

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

		<!-- Banner -->
		<section id="banner" class="major">
			<div class="inner">
				<header class="major">
					<h1>뭉쳐야 찬다</h1>
				</header>
				<div class="content">
					<p>친구는 없지만 축구를 좋아하는 사람들은 여기로</p>
					<ul class="actions">
						<li><a href="reserve.do" class="button next scrolly">예약하기</a></li>
					</ul>
				</div>
			</div>
		</section>
		<!-- Main -->
		<div id="main">

			<!-- One -->
			<section id="one" class="tiles">
				<article>
					<span class="image"> <img src="images/잔디밭.jpg" alt="" />
					</span>
					<header class="major">
						<h3>
							<a href="notice/list.do" class="link">공지사항</a>
						</h3>
						<p></p>
					</header>
				</article>
				<article>
					<span class="image"> <img src="images/축구.jpg" alt="" />
					</span>
					<header class="major">
						<h3>
							<a href="#" class="link">렌탈샵</a>
						</h3>
						<p>축구용품 대여</p>
					</header>
				</article>				
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


<!-- <div class="jumbotron one">
        <h1 class="display-4">뭉쳐야 찬다</h1>
        <p class="lead">저희 웹페이지가 뭉쳐드립니다.</p>
        <hr class="my-4">
        <p class="lead">
            <a class="btn btn-primary btn-lg" href="reserve.do" role="button" onclick="return confirm('예약하시겠습니까?');">예약하기</a>
        </p>
</div> -->

</body>
</html>