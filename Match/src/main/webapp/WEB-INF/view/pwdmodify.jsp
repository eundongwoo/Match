<%@page import="member.service.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>패스워드 변경</title>
</head>
<body>
<form action="pwdmodify.do" method="post">
<p>
<input type="password" name="curPwd" placeholder="현재암호를 입력." >
<c:if test="${errors.curPwd}">현재 암호를 입력하세요</c:if>
<c:if test="${errors.badCurPwd}">현재 암호가 일치하지 않습니다.</c:if>
</p>
<p>
<input type="password" name="newPwd" placeholder="변경할 암호를 입력.">
<c:if test="${errors.newPwd}">새 암호를 입력하세요.</c:if>
</p>
<input type="submit" value="변경">
</form>
</body>
</html>