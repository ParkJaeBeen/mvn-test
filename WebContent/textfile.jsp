<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
</head>
<body>
<form method="post" action="/textfile" enctype="multipart/form-data">
<div class="container">
<h3>Text File</h3>
<table class="table">
	<tr>
		<th>텍스트 파일 업로드</th>
		<td>
		<span class="input-group-text">
		<input type="file" name="txtfile" class="form-control"> 
		</span>
		</td>
	</tr>
	<tr>
		<td><button class="btn btn-outline-secondary">txt파일 전송</button></td>
	</tr>
</table>
</div>

</form>
</body>
</html>