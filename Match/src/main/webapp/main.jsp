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
							<a href="#" class="link">공지사항</a>
						</h3>
						<p></p>
					</header>
				</article>
				<article>
					<span class="image"> <img src="images/축구.jpg" alt="" />
					</span>
					<header class="major">
						<h3>
							<a href="elements.html" class="link">Tempus</a>
						</h3>
						<p>feugiat amet tempus</p>
					</header>
				</article>

				<!-- Two -->
				<section id="two">
					<div class="inner">
						<header class="major">
							<h2>Massa libero</h2>
						</header>
						<p>Nullam et orci eu lorem consequat tincidunt vivamus et
							sagittis libero. Mauris aliquet magna magna sed nunc rhoncus
							pharetra. Pellentesque condimentum sem. In efficitur ligula tate
							urna. Maecenas laoreet massa vel lacinia pellentesque lorem ipsum
							dolor. Nullam et orci eu lorem consequat tincidunt. Vivamus et
							sagittis libero. Mauris aliquet magna magna sed nunc rhoncus amet
							pharetra et feugiat tempus.</p>
						<ul class="actions">
							<li><a href="landing.html" class="button next">Get
									Started</a></li>
						</ul>
					</div>
				</section>
		</div>
		<section class="split">
			<section>
				<div class="contact-method">
					<span class="icon solid alt fa-envelope"></span>
					<h3>Email</h3>
					<a href="#">showth1720@naver.com</a>
				</div>
			</section>
			<section>
				<div class="contact-method">
					<span class="icon solid alt fa-phone"></span>
					<h3>Phone</h3>
					<span>대표전화 : 053-655-5600 / 팩스 : 053-655-3501</span>
				</div>
			</section>
			<section>
				<div class="contact-method">
					<span class="icon solid alt fa-home"></span>
					<h3>Address</h3>
					<span>대구 수성구 알파시티1로 170(대흥동) <br /> 대구디지털산업진흥원 402호<br />
						Republic of Korea
					</span>
				</div>
			</section>
		</section>
	</div>

	<!-- Footer -->
	<footer id="footer">
		<div class="inner">
			<ul class="icons">
				<li><a href="#" class="icon brands alt fa-twitter"><span
						class="label">Twitter</span></a></li>
				<li><a href="#" class="icon brands alt fa-facebook-f"><span
						class="label">Facebook</span></a></li>
				<li><a href="#" class="icon brands alt fa-instagram"><span
						class="label">Instagram</span></a></li>
				<li><a href="#" class="icon brands alt fa-github"><span
						class="label">GitHub</span></a></li>
				<li><a href="#" class="icon brands alt fa-linkedin-in"><span
						class="label">LinkedIn</span></a></li>
			</ul>
			<ul class="copyright">
				<li>&copy; Untitled</li>
				<li>Design: <a href="https://html5up.net">HTML5 UP</a></li>
			</ul>
		</div>
	</footer>

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