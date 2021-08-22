<%@page import="calendar.model.Calendar"%>
<%@page import="map.model.Place"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
List<Place> list=(List<Place>)request.getAttribute("list");

%> --%>
<%
Calendar cal= (Calendar)session.getAttribute("calendar");

if(cal!=null) {
	System.out.println("여기");
System.out.println(cal.getDate());
}
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
		});
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
    <input type="checkbox" id="toggle"> 
    <label for="toggle" onclick><input type="button" value="시간 조회"></label> 
    <br><br> 
    <div id="hidden_div">
    	<p>시간</p>
		<input type="button" value="09:00~12:00" class="timeBtn">		
		<input type="button" value="12:00~15:00" class="timeBtn">
		<input type="button" value="18:00~21:00" class="timeBtn">
	</div>
	 <form action="reserve.do" method="post"> 
    <table>
    	<td>
    		풋살장:<input type="text" name="place" id="place" value="<%=(String)session.getAttribute("placeName")%>"><br>   	
    		<c:if test="${empty param.year }">
    		날짜:<input type="text" name="date" id="date" value=""><br>  
    		</c:if>	 
    		<c:if test="${!empty param.year }">  		
    		날짜:<input type="text" name="date" id="date" value="<%=cal.getYear()+"년"+cal.getMonth()+"월"+cal.getDate()+"일"%>"><br> 
    		</c:if>   		
    		시간:<input type="text" name="time" id="time" >
    	</td>
    	<td>
    		<input type="submit" value="예약하기">
    	</td>
    </table>
    </form> 
  </body>
</html>