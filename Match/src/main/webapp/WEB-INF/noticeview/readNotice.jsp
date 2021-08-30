<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 읽기</title>
</head>
<body>
<table border="1" width="50%">

<tr>
	<td>번호</td>
	<td>${notice.number}</td>
</tr>
<tr>
	<td>작성자</td>
	<td>${notice.writer.name}</td>
</tr>
<tr>
	<td>제목</td>
	<td><c:out value="${notice.title}" /></td>
</tr>
<tr>
	<td>내용</td>
	<td>${notice.content}</td>
</tr>
<tr>
	<td colspan="2">
	<c:set var="pageNo" value="${empty param.pageNo ?'1': param.pageNo}" />
		<a href="list.do?pageNo=${pageNo}">[목록]</a>
		
		<!-- authUser과 article writer id가 같을때 수정 삭제가 보인다. -->
		<c:if test="${authUser.id == notice.writer.id}">
		<a href="modify.do?no=${notice.number}">[게시글수정]</a>
		<a href="delete.do?no=${notice.number}">[게시글삭제]</a>
		</c:if>
	</td>
</tr>
</table>
</body>
</html>