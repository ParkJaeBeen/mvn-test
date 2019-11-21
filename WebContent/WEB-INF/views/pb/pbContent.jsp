<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
</head>
<body style="text-align:center">
<div class="container">
<h3>${param.pb_num}</h3>
<table class="table">
 <thead class="thead-dark">
	<tr>
      <th scope="col">번호</th>
      <th scope="col">제목</th>
      <th scope="col">내용</th>
       <th scope="col">이미지1</th>
       <th scope="col">이미지2</th>
    </tr>
    </thead>
	<tbody id="tBody">
	</tbody>
</table>
<button type="button" class="btn btn-outline-secondary" onclick="goPage('/views/pb/pbinsert')">수정</button>
<button type="button" class="btn btn-outline-secondary" onclick="del()">삭제</button>
</div>
</body>
<script>
var pList;

window.onload = function(){
	var xhr = new XMLHttpRequest();
	xhr.open('GET','/pb/content?pb_num=${param.pb_num}');
	xhr.onreadystatechange = function(){
		if(xhr.readyState==xhr.DONE){
			if(xhr.status==200){
				pList = JSON.parse(xhr.responseText);
				console.log(xhr.responseText);
				var tBody = document.getElementById('tBody');
				var html = '';
					html += '<tr>';
					html += '<td>' +pList.pbNum+ '</td>';
					html += '<td>' +pList.pbTitle+ '</td>';
					html += '<td>' +pList.pbContent+ '</td>';
					html += '<td><img src="' +pList.pbImg1+ '" width="100px",height="50px"></td>'
					html += '<td><img src="' +pList.pbImg2+ '" width="100px",height="50px"></td>'
					html += '</tr>';
					tBody.innerHTML = html;
			}
		}
	}
	xhr.send();
}

function del(){
	var xhr = new XMLHttpRequest();
	xhr.open('POST','/pb/delete');
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				var res = JSON.parse(xhr.responseText);
				console.log(xhr.responseText);
				alert(res.msg);
				location.href = res.url;
			} 
		}
	}
	var param = {
			pbNum:pList.pbNum
	}
	console.log(param);
	xhr.send(JSON.stringify(param));
}
</script>
</html>