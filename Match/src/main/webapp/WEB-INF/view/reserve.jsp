<%@page import="java.util.concurrent.Callable"%>
<%@page import="calendar.model.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="map.model.Place"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
Calendar cal= (Calendar)session.getAttribute("calendar");
List<String> list=(List<String>)request.getSession().getAttribute("timeList");
HashMap<String, Integer> hm =(HashMap<String, Integer>)  request.getSession().getAttribute("timeMap");

/* if(list !=null)
{
	for(String s:list)
	{
		System.out.println(s);
	}
} */


if(cal!=null) {
	System.out.println("여기");
System.out.println(cal.getDate());
}

/* List<String> list=(List<String>)request.getAttribute("timeList");

for(String i:list)
{
	System.out.print("여기가 진짜"+i);
} */

%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Forty by HTML5 UP</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<noscript>
			<link rel="stylesheet" href="/Match/css/noscript.css" />
		</noscript>
			<link rel="stylesheet" href="/Match/css/main.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
		<script>
		$(document).ready(function(){
			$(".timeBtn").click(function(){
				var value=$($(this)).val();
				 $("#time").val(value);
			});
			
			
			$(function() {
				var topNum=$("#place").offset().top;
				$(window).scrollTop(topNum);
			});
			
			$("#lookTotal").click(function() {
				var topNum=$("#banner").offset().top;
				$(window).scrollTop(topNum);
			});
				
			
			});
			
		
			
		</script>
	</head>
	<% String s = request.getParameter("year")+"년"+request.getParameter("month")+"월"+request.getParameter("date")+"일"; %>
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

				<!-- Banner -->
					<section id="banner" class="major">
						<div class="inner">
							<header class="major">
								<h1>축구를 즐길 준비가 되셨나요?</h1>
							</header>
							<div class="content">
								<p>구장을 선택해 주세요.</p>
								<ul class="actions">
									<li><a href="#one" class="button next scrolly">구장 선택</a></li>
								</ul>
							</div>
						</div>
					</section>

				<!-- Main -->
					

						<!-- One -->
							<section id="one" class="tiles">							
									 <table>
					    				<tr>
					    					<td class="one"><jsp:include page="/WEB-INF/view/map.jsp" flush="false"/></td>					    									
					    				</tr>					    																					
    								</table>								
							</section>

						<!-- Two -->
							<section id="two" class="tiles">							
									 <table>
					    				<tr>
					    					<td class="two"><jsp:include page="/WEB-INF/view/calendar.jsp" flush="false"/></td>				    									
					    				</tr>					    																					
    								</table>								
							</section>
						
							
							<form action="timesearch.do" method="Post">
						    	<input type="hidden" name="placeName" value="<%=(String)session.getAttribute("placeName")%>">
						    	<input type="hidden" name="placeDate" value="<%=s%>">
						    	<input id="timelook" type="submit" value="시간 조회">
						    </form>
						    <div id="hidden_div">
    						<p>시간</p>
    						<%

		/* <%-- </c:forEach>   	<!--  이부분 check--> */

	 		if(list==null) {System.out.println("list는 null");} else {
	 			
			for(String time: list) {
				String a = time;
				String startTime = a.split("~")[0];
				SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");
				Date reserveTime = new Date(sdf.parse(startTime).getTime());
				java.util.Calendar cal2 = java.util.Calendar.getInstance();
				Date currentTime = cal2.getTime();
				reserveTime.setYear(currentTime.getYear());
				reserveTime.setMonth(currentTime.getMonth());
				reserveTime.setDate(currentTime.getDate());		//reserveTime완성
				
							//b<3||check==false
				boolean check = currentTime.after(reserveTime);
					//check가 true면 지나갔다 -> 버튼 못 누르게			
				
					
				java.util.Calendar cal3 =java.util.Calendar.getInstance();
				if(cal!=null) {
					System.out.println("받아온 날짜"+cal.getYear()+"년"+cal.getMonth()+"월"+cal.getDate()+"일");
					System.out.println("setting전날짜"+cal3.getTime());
					cal3.set(Integer.parseInt(cal.getYear()), Integer.parseInt(cal.getMonth())-1, Integer.parseInt(cal.getDate()));
					System.out.println("setting후 받아온 날짜"+cal3.getTime());		//예약시점의 날짜 (시간은 현재시점과 같음)
					System.out.println("boolean값:"+(cal3.getTime()!=currentTime));
				}
				
				System.out.println(cal3.getTime()+"::::"+currentTime);
				int b= hm.get(time);
				System.out.println("!check"+!check);
				System.out.println("cal3.getTime()"+cal3.getTime());
				System.out.println("currentTime"+currentTime);
				System.out.println("cal3.getTime()!=currentTime"+(cal3.getTime().after(currentTime)));
				if(b<3 && (!check || (cal3.getTime().after(currentTime)))) { 
				
		%>		
			<%-- <input type="button" value="<%=a%>&#10;<%=b %>" class="timeBtn">  --%>
			<button class="timeBtn" value="<%=a%>"><%=a%><br><%=b %></button>		
		
					
		<%
				}
			else{						
		%>
				<button  class="timeBtn rf" value="<%=a%>" disabled="disabled"><%=a%><br><%=b %></button>
			<script>
				$(".rf").css({
					background:'red',
					opacity:0.7
				});
			</script>	
			
		<%
				}
		%>
		<%
				}
			}

	 		
		%> 
							</div>
							<br><br>
				<form action="reserve.do" method="post"> 
   				 <table>
    			<td>

    			
    		
    		풋살장:
    		<%if((String)session.getAttribute("placeName")!=null) {%>
    		
    		<input type="text" name="place" id="place" value="<%=(String)session.getAttribute("placeName")%>" readonly="readonly"><br>   	
    		<%} else {%>
    		<input type="text" name="place" id="place" value="" readonly="readonly"><br>
    		<%} %>

    		<c:if test="${empty param.year}">
    		날짜:<input type="text" name="date" id="date" value="" readonly="readonly"><br>  
    		</c:if>	 
    		<c:if test="${!empty param.year }">  		
    		날짜:<input type="text" name="date" id="date" value="<%=cal.getYear()+"년"+cal.getMonth()+"월"+cal.getDate()+"일"%>" readonly="readonly"><br> 
    		</c:if>   		
    		시간:<input type="text" name="time" id="time" readonly="readonly">
    		 <div id="locationss"></div>	
    	</td>
    	<td>
    		<input type="button" id="lookTotal" value="구장선택하기">
    	</td>
    	
    	<td>
    		<input type="submit" value="예약하기">
    	</td>
    </table>
    	<!--  스크롤-->
    </form>
   
			

			<!-- Scripts -->
			<script src="/Match/js/jquery.min.js"></script>
			<script src="/Match/js/jquery.scrolly.min.js"></script>
			<script src="/Match/js/jquery.scrollex.min.js"></script>
			<script src="/Match/js/browser.min.js"></script>
			<script src="/Match/js/breakpoints.min.js"></script>
			<script src="/Match/js/util.js"></script>
			<script src="/Match/js/main.js"></script>
	<script>	
			/* $(function() {
				alert('hello');
				var topNum=$("#place").offset().top;
				$(window).scrollTop(topNum);
			}); */
			
				
	</script>
	</body>
</html>