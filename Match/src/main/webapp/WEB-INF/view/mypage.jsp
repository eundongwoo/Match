<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="member.service.User"%>
<%
	User user=(User)request.getSession().getAttribute("authUser");
	System.out.println("방금"+user.getTel());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1 nitial-scale=1, user-scalable=no">
<noscript>
	<link rel="stylesheet" href="/Match/css/noscript.css" />
</noscript>
<link rel="stylesheet" href="/Match/css/main.css">
<style>
#wrap
{
	width: 70%;
	overflow: hidden;
}
#wrap div:first-child {
	width: 50%;
	
	box-sizing:border-box;
	float: left;
}
#wrap div:last-child {
	width: 70%;
	
	box-sizing:border-box;
	float: left;
}    
           table {
    width: 70%; height: 50px; margin:150px;
    border: 1px solid #444444;
    text-align: center;
  }
  th, td {
    border: 1px solid #444444;
    vertical-align : under;
  }
  #box1{ text-align: left;		 
  		 padding:150px;	}
        

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
			<div class="inner">
				<header class="major">
					<h1 align="right">회원정보수정</h1>
				</header>
			</div>
		</section>
		
<div id="wrap">
 <table border="1">
 	<tbody>	 
 			<tr colspan="2">
                <td id="title">이름</td>
                <td><%=user.getName() %></td>
                <td></td>
            </tr> 	
            <tr colspan="2">
                <td width="30%" id="title">아이디</td>
                <td><%=user.getId() %></td>
                <td></td>
            </tr>          
                            
            <tr>
                <td id="title">비밀번호</td>
                <td><form action="pwdmodify.do" method="post">
             	<input type="password" name="newPwd" placeholder="변경할 암호를 입력.">
             	<input type="submit" value="변경"></form></td>
             	<td></td>
             	
            </tr>
            <tr>
                <td id="title">전화번호</td> 
                <td><%=user.getTel() %></td>         
                <td><form action="telmodify.do" method="post">
                <input type="text" name="newTel" placeholder="ex)010-1234-5678">
                <input type="submit" value="변경"></form></td>
            </tr>
          
     </tbody>
     </table>      
</div>
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