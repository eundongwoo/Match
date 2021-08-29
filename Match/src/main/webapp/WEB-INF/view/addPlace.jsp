<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
<script>

	 $(document).ready(function() {
			var i=1;				
			$("#plus").click(function() {			
			$("#placeDiv").append("<div><input name='operationTime"+i+"' class='placeName' type='text' ><div>");			
			$("#timeRowNum").val(i);
			i++;  
		});
	});
	
	
</script>
</head>
<body>
	<h1>풋살장 추가</h1>
	<form action="admin.do" method="post">
	<table>
		<tr>
			<td>풋살장 이름</td>
			<td><input name="placeName" type="text" value=""></td>
		</tr>
		<tr>
			<td>풋살장 주소</td>
			<td><input name="placeAddr" type="text" value=""></td>
		</tr>
		<tr>
			<td>풋살장 전화번호</td>
			<td><input name="placeTel" type="text" value=""></td>
		</tr>
		<tr>
			<td>풋살장 운영시간</td>
			<td><input id="plus" type="button" value="+"></td>					
		</tr>
				
	</table>
	<div id="placeDiv">
			
	</div>
	<input type="submit" value="제출">
	<input type="hidden" name="timeRowNum" id="timeRowNum">
	</form>
	
</body>
</html>