<%@page import="map.dao.MapDao"%>
<%@page import="map.model.Place"%>
<%@page import="java.util.List"%>
<%@page import="map.service.MapService"%>
<%@page import="jdbc.connection.ConnectionProvider"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
Connection con=ConnectionProvider.getConnection();
MapDao dao=new MapDao();
List<Place> list=dao.getPlaceInfo(con);
for(Place p:list)
{
	out.print(p.getF_name());
	out.print(p.getF_addr());
}

%>
안녕하세요
</body>
</html>