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
<title>Insert title here</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
     integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
 <link rel="stylesheet" href="/Match/css/main.css">
</head>
<body>
<!-- 메뉴바 -->
<nav>
<jsp:include page="/WEB-INF/view/nav.jsp"/>

</nav>

<!-- 메인 body -->

<div class="jumbotron one">
        <h1 class="display-4">뭉쳐야 찬다</h1>
        <p class="lead">저희 웹페이지가 뭉쳐드립니다.</p>
        <hr class="my-4">
        <p class="lead">
            <a class="btn btn-primary btn-lg" href="reserve.do" role="button">예약하기</a>
        </p>
</div>
</body>
</html>