<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 읽기</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#cmtCnt-btn").click(function(){	
			 $.ajax({
				url: "/article/commentInsert.do",
				type: "POST",
				data: {  //무슨형식으로 주는거지?
					num: ${articleData.article.number}
					content : $("#cmtCmt").val()
				},
				dataType: "String",
				success: function(){
					console.log("보내기 성공");
					location.reload()
				},
			}); 
		});
	});

</script>
</head>
<body>
<table border="1" width="50%">


<tr>
	<td>번호</td>
	<td>${articleData.article.number}</td>
</tr>
<tr>
	<td>작성자</td>
	<td>${articleData.article.writer.name}</td>
</tr>
<tr>
	<td>제목</td>
	<td><c:out value="${articleData.article.title}" /></td>
</tr>
<tr>
	<td>내용</td>
	<td><u:pre value="${articleData.article.content}"/></td>
</tr>
<tr>
	<td colspan="2">
	<c:set var="pageNo" value="${empty param.pageNo ?'1': param.pageNo}" />
		<a href="list.do?pageNo=${pageNo}">[목록]</a>
		
		<!-- authUser과 article writer id가 같을때 수정 삭제가 보인다. -->
		<c:if test="${authUser.id == articleData.article.writer.id}">
		<a href="modify.do?no=${articleData.article.number}">[게시글수정]</a>
		<a href="delete.do?no=${articleData.article.number}">[게시글삭제]</a>
		</c:if>
	</td>
</tr>
</table>



<!-- 댓글 기능 -->
<!-- 본래 있던 댓글 -->
<c:if test="${comment != null }">
<table border="1" width="50">
	<tr>
		<!-- 아이디, 작성날짜 -->
		<td width="150">
			<div>
				${comment.comment_id }	<br/>
				${comment.comment_date }
			</div>
		</td>
		<!-- 본문 내용 -->
		<td width="550">
			<div class="">
				${comment.comment_content}
			</div>
		</td>
			<!-- 작성자만 삭제 -->
			<c:if test="${authUser.id == comment.comment_id  }">
				<a href="#">[삭제]</a>
			</c:if>
		</td>
	</tr>
</table>
</c:if>

<!-- 댓글 작성 -->
	<c:if test="${authUser != null }">
	<form action="read.do" method="get">
		<input type="hidden" name="comment_article" value="${article.comment_no }">
		<input type="hidden" name="id" value="${authUser.id }">
		<!-- 아이디 -->
		<td width="150">
			<div>
				${authUser.id}
			</div>
		</td>
		<!-- 본문 작성 -->
		<td width="550">
			<div>
				<textarea id="cmtCmt" name="cmtCmt" placeholder="댓글을 입력해주세요."></textarea>
				<%--<c:if test="${errors.content}">내용을 입력해주세요.</c:if> --%>
			</div>
		</td>
		<!--댓글 등록 버튼 -->
		<td width="100">
			<div class="comment-button">
				<button id="cmtCnt-btn">댓글달기</button>
			</div>
		</td>
	</form>
	</c:if>
	
</body>
</html>