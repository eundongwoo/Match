<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<noscript>
	<link rel="stylesheet" href="/Match/css/noscript.css" />
</noscript>
<link rel="stylesheet" href="/Match/css/main.css">
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
<script>

	 $(document).ready(function() {
			var i=1;				
			$("#plus").click(function() {			
			$("#placeDiv").append("<div><input name='operationTime"+i+"' class='placeName' type='text' placeholder='10:00~12:00' ><div>");			
			$("#timeRowNum").val(i);
			i++;  
		});
	});
	
	
</script>
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
	
	<!-- One -->
					<header class="major">
						<h1 align="center">풋살장추가</h1>
					</header>

	
	<form action="admin.do" method="post">
	<table>
		<tr>
			<td>풋살장 이름</td>
			<td><input name="placeName" type="text" value=""></td>
		</tr>
		<tr>
			<td>풋살장 주소</td>
			<td><input name="placeAddr" type="text" value=""></td>
		</tr>
		<tr>
			<td>풋살장 전화번호</td>
			<td><input name="placeTel" type="text" value=""></td>
		</tr>
		<tr>
			<td>풋살장 운영시간</td>
			<td><input id="plus" type="button" value="+"></td>					
		</tr>
				
	</table>
	<div id="placeDiv">
			
	</div>
	<input type="submit" value="제출">
	<input type="hidden" name="timeRowNum" id="timeRowNum">
	</form>
	
	
	<script src="/Match/js/jquery.min.js"></script>
		<script src="/Match/js/jquery.scrolly.min.js"></script>
		<script src="/Match/js/jquery.scrollex.min.js"></script>
		<script src="/Match/js/browser.min.js"></script>
		<script src="/Match/js/breakpoints.min.js"></script>
		<script src="/Match/js/util.js"></script>
		<script src="/Match/js/main.js"></script>
</body>
</html>