<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/Match/ckeditor/ckeditor.js"></script>


<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	if ("${errors.content }"){
		alert('내용을 입력해주세요.');
	}
</script>
<meta charset="UTF-8">
<title>공지글 쓰기</title>
</head>
<body>
<form action="write.do" method="post" >
	<p>
		제목:<br/><input type="text" name="title" value="${param.title }">
		<c:if test="${errors.title }">제목을 입력하세요.</c:if>
	</p>

	<textarea class="form-control" id="p_content" name="content"></textarea>
	<script type="text/javascript">
 	CKEDITOR.replace('p_content'
                , {height: 500,
                	filebrowserUploadUrl: '/Match/notice/upload.do'
                 });
	</script>

<input type="submit" value="새 글 등록">
</form>
</body>
</html>