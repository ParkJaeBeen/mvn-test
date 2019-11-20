<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
</head>
<body  style="text-align:center">
<h2>File List</h2> 
<div class="container">
<table class="table">
 <thead class="thead-dark">
	<tr>
      <th scope="col">번호</th>
      <th scope="col">아이디</th>
      <th scope="col">이름</th>
       <th scope="col">경로</th>
    </tr>
    </thead>
	<tbody id="tBody">
	</tbody>
</table>
</div>
</body>
<script>
window.onload = function(){
	var xhr = new XMLHttpRequest();
	xhr.open('GET','/file');
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				var ftList = JSON.parse(xhr.responseText);
				console.log(xhr.responseText);
				var tBody = document.getElementById('tBody');
				var html='';
				for(var i of ftList){
					html += '<tr>';
					html += '<td>' +i.FT_NUM+ '</td>';
					html += '<td>' +i.FT_ID+ '</td>';
					html += '<td>' +i.FT_NAME+ '</td>';
					html += '<td><img src="' +i.FT_FILE+ '" width="100px",height="50px"></td>'; 
					html += '</tr>';
				}
				tBody.innerHTML = html;
			}
		}
	}
	xhr.send();
}
</script>
</html>