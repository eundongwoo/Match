<%@page import="member.service.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User user=(User)request.getSession().getAttribute("authUser");
	System.out.println("방금"+user.getTel());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
ul li
{
	list-style: none;
}
li a
{
	text-decoration: none;
}
#wrap
{
	width: 100%;
	overflow: hidden;
}
#wrap div:first-child {
	width: 50%;
	
	box-sizing:border-box;
	float: left;
}
#wrap div:last-child {
	width: 50%;
	
	box-sizing:border-box;
	float: left;
}


 #content .select { width:250px; float:hidden;  }
        #content .select ul {  }
        #content .select ul li a { width:120px; height:50px; line-height:50px; border-radius:10px; margin-top:50px;
                              background-color:green; display:block; color:#ffffff; text-align:center;
                             font-weight:bold; }
        #content .select ul li a:hover { 
            animation-name:ani;
            animation-duration:2s;
            animation-iteration-count:infinite;
        }

        @keyframes ani {
            from { width:100px; }
            to { width:200px; background-color:skyblue; }
        }

</style>
</head>
<body>
<jsp:include page="/WEB-INF/view/nav.jsp" flush="false"/>

<div id="wrap">
<div>
<div id="content">
	<div class="select">
		<ul>
			<li><a href="pwdmodify.do">패스워드 변경</a></li>
			<li><a href="telmodify.do">전화번호 변경</a></li>
			<li><a href="search.do">예약 조회</a></li>
		</ul>	
</div>
</div>

<div>
<h1>회원 정보</h1>
<table>
<tr>
<td>이름</td>
<td><%=user.getName()%></td>
</tr>
<tr>
<td>아이디</td>
<td><%=user.getId()%></td>
</tr>
<tr>
<td>전화번호</td>
<td><%=user.getTel()%></td>
</tr>
</table>
</div>
</div>
</body>
</html>