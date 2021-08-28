<%@page import="calendar.model.Calendar"%>
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
<!doctype html>
<html lang="en">
  <head>
   	<meta charset="utf-8">
	<title>메인 페이지</title>
	<link rel="stylesheet" href="/Match/css/reserve.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			$(".timeBtn").click(function(){
				var value=$($(this)).val();
				 $("#time").val(value);
			});
		}	
		);
	</script>
  </head>
  <% String s = request.getParameter("year")+"년"+request.getParameter("month")+"월"+request.getParameter("date")+"일";
  		
  %>
  <body>
  	<nav>
    <jsp:include page="/WEB-INF/view/nav.jsp" flush="false"/>  	
  	</nav>
    <!-- 여기까지 메뉴바 구현 -->
    
    <table>
    	<tr>
    		<td class="one"><jsp:include page="/WEB-INF/view/map.jsp" flush="false"/></td>
    		<td class="two"><jsp:include page="/WEB-INF/view/calendar.jsp" flush="false"/></td>
    	</tr>
    </table>
   <!--  <input type="checkbox" id="toggle"> 
    <label for="toggle" onclick><input type="button" value="시간 조회"></label>  -->
   
    <form action="timesearch.do" method="Post">
    	<input type="hidden" name="placeName" value="<%=(String)session.getAttribute("placeName")%>">
    	<input type="hidden" name="placeDate" value="<%=s%>">
    	<input type="submit" value="시간 조회">
    </form>
   
    <br><br> 
     <div id="hidden_div">
    	<p>시간</p>
		<!-- <input type="button" value="09:00~12:00" class="timeBtn">		
		<input type="button" value="12:00~15:00" class="timeBtn">
		<input type="button" value="18:00~21:00" class="timeBtn"> -->
		
		<%--  <c:forEach var="time" items="<%=list%>">
			<c:set target="<%=hm%>" property="${time}"/>
			<input type="button" value="${time}" class="timeBtn"> 
		</c:forEach>  --%>
		<%-- <% pageContext.setAttribute("newLineChar", "\n"); %> --%>
		<%-- ${fn:replace(a, newLineChar,'<br/>')} --%>
	 	<%
	 		if(list==null) {System.out.println("list는 null");} else {
			for(String time: list) {
				String a = time;
				int b= hm.get(time);
				if(b<3) { 
				
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
	 <form action="reserve.do" method="post"> 
    <table>
    	<td>
    		풋살장:<input type="text" name="place" id="place" value="<%=(String)session.getAttribute("placeName")%>" readonly="readonly"><br>   	
    		<c:if test="${empty param.year}">
    		날짜:<input type="text" name="date" id="date" value="" readonly="readonly"><br>  
    		</c:if>	 
    		<c:if test="${!empty param.year }">  		
    		날짜:<input type="text" name="date" id="date" value="<%=cal.getYear()+"년"+cal.getMonth()+"월"+cal.getDate()+"일"%>" readonly="readonly"><br> 
    		</c:if>   		
    		시간:<input type="text" name="time" id="time" readonly="readonly">
    	</td>
    	<td>
    		<input type="submit" value="예약하기">
    	</td>
    </table>
    </form> 
  </body>
</html>