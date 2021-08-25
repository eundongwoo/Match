<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전화번호 변경</title>
</head>
<body>
<form action="telmodify.do" method="post">
<input type="text" name="newTel" placeholder="ex)010-1234-5678">
<c:if test="${errors.newTel}">새로운 전화번호를 입력해주세요.</c:if>
<input type="submit" value="변경">
</form>
</body>
</html>