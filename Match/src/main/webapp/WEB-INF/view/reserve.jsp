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
    
  </body>
</html>