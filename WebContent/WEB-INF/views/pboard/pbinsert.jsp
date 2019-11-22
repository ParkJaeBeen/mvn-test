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
<div class="container">
<h3>insert</h3>
		<table class="table">
			<tbody>
			<tr>
				<th scope="row">제목</th>
				<td><input type="text" id="pbTitle"></td>
			</tr>
			<tr>
			<th scope="row">내용</th>
				<td><textarea id="pbContent"></textarea></td>
			</tr>
			<tr>
			<th scope="row">user</th>
				<td><input type="number" id="creusr"></td>
			</tr>
			<tr>
			<th scope="row">이미지</th>
				<td><input type="file" id="pbImg1"></td>
			</tr>
			<tr>
			<th scope="row">이미지</th>
				<td><input type="file" id="pbImg2"></td>
			</tr>
			<tr>
				<td><button onclick="submit()" class="btn btn-outline-secondary">send</button></td>
			</tr>
			</tbody>
		</table>
	</div>
<script>
function submit(){
	var form = makeFormData();
	var conf = {
			method:'POST',
			url:'/pboard/insert',
			func:function(res){
				alert('성공');
				location.href='/views/pboard/pblist';
			},
			data:form,
	}
	send(conf);
}
</script>
</body>
</html>