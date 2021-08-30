<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지글 등록</title>
</head>
<body>

공지글을 등록했습니다.
<br>
${ctxPath = pageContext.request.contextPath;''}
<a href="${ctxPath}/notice/list.do" onclick="return confirm('목록보시겠습니까?');">[공지글목록보기]</a>
<a href="${ctxPath}/notice/read.do?no=${newNoticeNo}">[공지글내용보기]</a>
</body>
</html>