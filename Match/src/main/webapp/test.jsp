<%@page import="java.sql.PreparedStatement"%>
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
	PreparedStatement prst=null;
	String sql="delete from reservation where member_id=? and place_id=? and reserve_date=? and reserve_time=?";
	prst=con.prepareStatement(sql);
	prst.setString(1,"hong");
	prst.setInt(2, 1);
	prst.setString(3, "2021년8월27일");
	prst.setString(4, "09:00~12:00");
	prst.executeUpdate();
	System.out.print("성공");
}catch(SQLException e)
{
	out.print("실패");
}
%>
</body>
</html>