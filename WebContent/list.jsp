<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
</head>
<body  style="text-align:center">
<h2>USER INFO</h2> 
<div class="container">
<table class="table">
 <thead class="thead-dark">
	<tr>
      <th scope="col">번호</th>
      <th scope="col">닉네임</th>
      <th scope="col">작성일</th>
      <th scope="col">작성시간</th>
    </tr>
    </thead>
	<tbody id="tBody">
	</tbody>
</table>
<button type="button" class="btn btn-outline-secondary">글쓰기</button>
</div>
<script>
window.onload = function(){
	var xhr = new XMLHttpRequest();
	xhr.open('GET','/user')
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				var list = JSON.parse(xhr.responseText);
				console.log(xhr.responseText);
				var tBody = document.getElementById("tBody");
				var html = '';
				for(var l of list)  
				{
					html += '<tr>'; 
					html += '<td>'+l.ui_num+'</td>';
					html += '<td>'+l.ui_id+'</td>';
					html += '<td>'+l.credat+'</td>';
					html += '<td>'+l.cretim+'</td>';
					html += '</tr>';
				} 
				tBody.innerHTML = html;
			}
		}
	}
	xhr.send();
}
</script>
</body>
</html>