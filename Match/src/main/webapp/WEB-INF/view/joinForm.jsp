<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입</title>
<link rel="stylesheet" href="/Match/css/login.css">
</head>
<body>
<div class="login-page">
<div class="form">
<form action="join.do" method="post" class="register-form">

<input type="text" name="id" value="${param.id}" placeholder="ID">
<c:if test="${errors.id}">아이디를 입력하세요.</c:if>
<c:if test="${errors.duplicateId}">이미 사용중인 아이디 입니다.</c:if>

<input type="text" name="name" value="${param.name}" placeholder="name">
<c:if test="${errors.name}">이름을 입력하세요.</c:if>


<input type="password" name="password" placeholder="Password">
<c:if test="${errors.password}">패스워드를 입력하세요.</c:if>


<input type="password" name="confirm" placeholder="PasswordConfirm">
<c:if test="${errors.confirm}">패스워드 확인을 입력하세요.</c:if>
<c:if test="${errors.notMatch}">패스워드와 확인이 일치하지 않습니다.</c:if>


<input type="text" name="tel" value="${param.tel}" placeholder="ex) 010-1234-5678">
<c:if test="${errors.tel}">전화번호를 입력하세요.</c:if>

<input type="submit" value="가입">
</form>
</div>
</div>
</body>
</html>