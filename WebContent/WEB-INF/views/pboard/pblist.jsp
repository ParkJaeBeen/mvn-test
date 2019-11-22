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
<h2>Photo Board</h2> 
<div class="container">
<table class="table">
 <thead class="thead-dark">
	<tr>
      <th scope="col">번호</th>
      <th scope="col">제목</th>
      <th scope="col">내용</th>
       <th scope="col">이미지1</th>
       <th scope="col">이미지2</th>
       <th scope="col">작성날짜</th>
       <th scope="col">작성시간</th>
       <th scope="col">작성자</th>
       <th scope="col">수정날짜</th>
       <th scope="col">수정시간</th>
       <th scope="col">수정자</th>
    </tr>
    </thead>
	<tbody id="tBody">
	</tbody>
</table>
<button type="button" class="btn btn-outline-secondary" onclick="goPage('/views/pboard/pbinsert')">글쓰기</button>
</div>
<script>
window.onload = function(){
	var xhr = new XMLHttpRequest();
	xhr.open('GET','/pboard/list');
	xhr.onreadystatechange = function(){
		if(xhr.readyState==xhr.DONE){
			if(xhr.status==200){
				var pbList = JSON.parse(xhr.responseText);
				var tBody = document.getElementById('tBody');
				var html='';
				for(var i of pbList){
					html += '<tr onclick="goPage(\'/views/pboard/pbContent?pb_num=' + i.pbNum + '\')">\n';  
					html += '<td>' +i.pbNum+ '</td>\n';
					html += '<td>' +i.pbTitle+ '</td>\n';
					html += '<td>' +i.pbContent+ '</td>\n';
					html += '<td><img src="${pageContext.request.contextPath}' +i.pbImg1+ '" width="100px",height="50px"></td>\n'; 
					html += '<td><img src="${pageContext.request.contextPath}' +i.pbImg2+ '" width="100px",height="50px"></td>\n';
					html += '<td>' +i.credat+ '</td>\n';
					html += '<td>' +i.cretim+ '</td>\n';
					html += '<td>' +i.creusr+ '</td>\n';
					html += '<td>' +i.moddat+ '</td>\n';
					html += '<td>' +i.modtim+ '</td>\n';
					html += '<td>' +i.modusr+ '</td>\n';
					html += '</tr>\n';
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