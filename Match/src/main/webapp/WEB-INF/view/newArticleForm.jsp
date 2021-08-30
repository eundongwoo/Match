<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="/Match/ckeditor/ckeditor.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 쓰기</title>
</head>
<body>
<form action="write.do" method="post" >
<p>
	제목:<br/><input type="text" name="title" value="${param.title }">
	<c:if test="${errors.title }">제목을 입력하세요.</c:if>
</p>
<p>
	내용:<br/>
	<textarea name="content" rows="5" cols="30">${param.content}</textarea>
	<c:if test="${errors.content }">내용을 입력하세요.</c:if>
	
	<!-- <textarea class="form-control" id="p_content" name="content"></textarea>
	<script type="text/javascript">
 	CKEDITOR.replace('p_content'
                , {height: 500,
                	filebrowserUploadUrl: '/Match/article/upload.do'
                 });
 	<c:if test="${errors.content }">내용을 입력하세요.</c:if>
	</script> -->
	
</p>



<input type="submit" value="새 글 등록">
</form>
</body>
</html>