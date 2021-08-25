<%@page import="java.sql.SQLException"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.Catch"%>
<%@page import="java.sql.Connection"%>
<%@page import="jdbc.connection.ConnectionProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<input type="text" value="<%=request.getParameter("placeName")%>">
<% 
try(Connection con=ConnectionProvider.getConnection())
{
	out.print("성공");
}catch(SQLException e)
{
	out.print("실패");
}
%>
</body>
</html>