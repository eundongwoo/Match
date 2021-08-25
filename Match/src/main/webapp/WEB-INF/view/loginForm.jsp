<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/Match/css/login.css">
</head>
<body>
<div class="login-page">
<div class="form">
<form action="login.do" method="post" class="login-form">
<c:if test="${errors.idOrPwNotMatch}">
</c:if>
<p>
	<input type="text" name="id" value="${param.id}" placeholder="ID">
	<c:if test="${errors.idOrPwNotMatch }">
	아이디와 암호가 일치하지 않습니다.
	</c:if>
</p>

<p>
	<input type="password" name="password" placeholder="Password" >
	<c:if test="${errors.password}">
	암호를 입력하세요.
	</c:if>
</p>
<input type="submit" value="로그인">
</form>
</div>
</div>
</body>
</html>