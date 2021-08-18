<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
  <head>
   	<meta charset="utf-8">
	<title>메인 페이지</title>
	<link rel="stylesheet" href="/Match/css/reserve.css">
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
    <br><br> 
    <div id="hidden_div">
    	<p>시간</p>
		<input type="button" value="09:00~12:00">		
		<input type="button" value="12:00~15:00">
		<input type="button" value="18:00~21:00">
	</div>
	 
    <table>
    	<td>
    		풋살장:<input type="text" name="place" id="place" ><br>   	
    		<c:if test="${empty param.year }">
    		날짜:<input type="text" name="date" id="date" value=""><br>  
    		</c:if>	 
    		<c:if test="${!empty param.year }">  		
    		날짜:<input type="text" name="date" id="date" value="<%=s%>"><br> 
    		</c:if>   		
    		시간:<input type="text" name="time" id="time" >
    	</td>
    	<td>
    		<input type="submit" value="예약하기">
    	</td>
    </table>
    
  </body>
</html>