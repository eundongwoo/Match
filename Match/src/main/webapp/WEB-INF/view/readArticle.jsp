<%@ page language="java" contentType="text/html; charset=UTF-8"

	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<title>자유게시판</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1 nitial-scale=1, user-scalable=no">
<link rel="stylesheet" href="/Match/css/main.css">
<script type="text/javascript">

	//document.ready----
	$(document).ready(function(){
		//댓글쓰기 클릭 이벤트 걸기1
		$("#commentWrite").click(function(){	
			
			 if($("#cmtCmt").val()=="") {
				 alert('댓글 내용을 입력해주세요');
				 return null;
			 }
			$.ajax({
				url:"/Match/comment.do",
				dataType: "json",
				type:"POST",
				data: {
					num: "${articleData.article.number}", 
					content : $("#cmtCmt").val(),
					judge:"write"
				},
				
				success: function(data1){
						x=data1;
						showHtml(data1.comments, 1);
				}
			}); 
		});
		//function인자에 x지움
		$("#commentRead").click(function() {
			$.ajax({
				url:"/Match/comment.do",
				dataType: "json",
				type:"POST",
				data: {
					num: "${articleData.article.number}",
					judge:"read"
				},				
				success: function(data1){					
						showHtml(data1.comments, 1);	
				}
			}); 
		});
		
		$(".Delete").click(function commentDelete() {
			alert('삭제버튼 누름');
		 	 var comment_num_data = $(this).attr('data-del'); 
		 	 			 	
		 	$.ajax({
				url:"/Match/comment.do",
				dataType: "json", 
				type:"POST",
				data: {
					num: "${articleData.article.number}",
					judge:"delete",
					commentNum:comment_num_data
				},				
				 success: function(data){					
						 //showHtml(data1.comments, 1); 
						alert('삭제되었습니다.');
						$.ajax({
							url:"/Match/comment.do",
							dataType: "json",
							type:"POST",
							data: {
								num: "${articleData.article.number}",
								judge:"read"
							},				
							success: function(data1){					
									showHtml(data1.comments, 1);	
							}
						}); 
				} 
				
		 	});  
		 			 
	}); 			
	});
	//----document.ready
	
	
	//메소드 선언---
	var showHtml = function showHtml(data, pageNum) {
		/* alert($("#a").attr("data-val")); */
		var html="<div id='showContent'><input type='button' class='btn btn-default' value='댓글 보기' id='commentRead'></div>";
				
		html+="<table>";
		html+="<tr><td>댓글 번호</td><td>id</td><td>내용</td><td>작성날짜</td></tr>"
		$.each(data, function(index, item) {
			
			html += "<tr align='center'>";
            html += "<td>" + (index+1) + "</td>";
            html += "<td>" + item.id + "</td>";                               
            html += "<td align='left'>" + item.comment_content + "</td>";          
            html += "<td>" + item.comment_date+ "</td>";        
            var comment_num = item.comment_num;		
            if(item.id=='${authUser.id}') {
            	html+="<td><input class='Delete' type='button' data-del='"+comment_num+"' value='삭제하기'/></td>";
            }               
            html += "</tr>";
            
		});
		html+="</table>"
			
		$("#showContent").html(html);
		
		
		 $(".Delete").click(function commentDelete() {
				alert('삭제버튼 누름');
			 	 var comment_num_data = $(this).attr('data-del'); 
			 	 			 	
			 	$.ajax({
					url:"/Match/comment.do",
					dataType: "json",
					type:"POST",
					data: {
						num: "${articleData.article.number}",
						judge:"delete",
						commentNum:comment_num_data
					},				
					 success: function(data){					
							 //showHtml(data1.comments, 1); 
							
							$(document).ready(function(){
								alert('삭제되었습니다.!!');
								history.go();
							});
							
							
					} 
					
			 	});  
		}); 
		
	}
</script>

</head>
<body class="is-preload">

	<!-- Wrapper -->
	<div id="wrapper">

		<!-- Header -->
		<header id="header" class="alt">
			<a href="/Match/main.jsp" class="logo"><strong>matching</strong>
				<span>kick together</span></a>
			<nav>
				<a href="#menu">Menu</a>
			</nav>
		</header>
		
		<!-- Menu -->
		<nav id="menu">
			<jsp:include page="/WEB-INF/view/nav.jsp" />
		</nav>

		<!-- Main -->
		<div id="main" class="alt">

			<!-- One -->
			<section id="one">
				<div class="inner">
					<header class="major">
						<h1 align="center">자유게시판</h1>
					</header>
					
					<table border="1">
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
							<td><u:pre value="${articleData.article.content}" /></td>
						</tr>
						<tr>
							<td colspan="2"><c:set var="pageNo"
									value="${empty param.pageNo ?'1': param.pageNo}" /> <a
								href="article_list.do?pageNo=${pageNo}">[목록]</a> <!-- authUser과 article writer id가 같을때 수정 삭제가 보인다. -->
								<c:if test="${authUser.id == articleData.article.writer.id}">
									<a href="article_modify.do?no=${articleData.article.number}">[게시글수정]</a>
									<a href="article_delete.do?no=${articleData.article.number}" onclick="return confirm('게시글을 삭제하시겠습니까?')">[게시글삭제]</a>
								</c:if></td>
						</tr>
					</table>
					</div>
				
			</section>
			</div>
		</div>
		
<!-- 댓글 작성 -->
	<%-- <c:if test="${authUser != null }"> --%>
	<!-- <form id="comment"  method="POST"> -->
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
				<textarea id="cmtCmt" name="cmtCmt"  placeholder="댓글을 입력해주세요."></textarea>
				<%--<c:if test="${errors.content}">내용을 입력해주세요.</c:if> --%>
			</div>
		</td>
		<!--댓글 등록 버튼 -->
		<td width="100">
			<div class="comment-button">
				<!-- <button id="cmtCnt-btn">댓글달기</button> -->
				<input type="button" class="btn btn-default" value="댓글 쓰기" id="commentWrite">
			</div>
		</td>
	<!-- </form> -->
	<div id="showContent">
		<input type="button" class="btn btn-default" value="댓글 보기" id="commentRead">
	</div>
	<!-- Scripts -->
<script src="/Match/js/jquery.min.js"></script>
<script src="/Match/js/jquery.scrolly.min.js"></script>
<script src="/Match/js/jquery.scrollex.min.js"></script>
<script src="/Match/js/browser.min.js"></script>
<script src="/Match/js/breakpoints.min.js"></script>
<script src="/Match/js/util.js"></script>
<script src="/Match/js/main.js"></script>



</body>
</html>