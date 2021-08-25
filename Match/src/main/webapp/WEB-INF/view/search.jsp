<%@page import="mypage.model.ReserveInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	 List<ReserveInfo> list=(List<ReserveInfo>)request.getAttribute("ReserveList");
	 for(ReserveInfo i:list)
	 {
		 System.out.println(i.getMember_id()+":"+i.getPlace_name()+":"+i.getReserve_date()+":"+i.getReserve_time());
	 }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/view/nav.jsp"/>

<c:set var="vals" value="<%=list%>"/>

<h1>${authUser.name}님 예약조회</h1>


<table class="table table-striped">
<thead>
<tr>
<th>풋살장 명</th>
<th>예약 날짜</th>
<th>예약 시간</th>
<th>신청 날짜</th>
<th>예약 상태</th>

</tr>
</thead>
<tbody>
<c:if test="${empty vals}">
<td colspan="4">예약목록이 없습니다.</td>
</c:if>

<c:forEach var="info" items="<%=list%>">
<tr>
	<td>${info.place_name}</td>
	<td>${info.reserve_date}</td>
	<td>${info.reserve_time}</td>
	<td>${info.reg_time}</td>
	<td>${info.state}</td>
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>