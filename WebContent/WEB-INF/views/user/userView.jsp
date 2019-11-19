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
<h3>회원정보</h3>
	<table class="table table-bordered">
		<tr>
			<th>번호</th>
			<td data-id="ui_num"></td>
		</tr>
		<tr>
			<th>닉네임</th>
			<td data-id="ui_name"></td>
		</tr>
		<tr>
			<th>아이디</th>
			<td  data-id="ui_id"></td>
		</tr>
		<tbody id="tBody">
		</tbody>
	</table>
	<button type="button" class="btn btn-outline-secondary" onclick="update(this)">수정</button>
	<button type="button" class="btn btn-outline-secondary" onclick="del()">삭제</button>
	<button type="button" class="btn btn-outline-secondary" onclick="goPage('/views/user/list')">리스트 돌아가기</button>
</div>
</body>
<script>
var list;

window.onload = function(){
	var xhr = new XMLHttpRequest();
	xhr.open('GET','/user/view?ui_num=${param.ui_num}');
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				list = JSON.parse(xhr.responseText);
				var tds = document.querySelectorAll('td[data-id]');
				for(var idx=0;idx<tds.length;idx++){
					var td = tds[idx];
					var key = td.getAttribute('data-id');
					td.innerHTML = list[key];
				}
			}
		}
	}
	xhr.send();
}

function del(){
	var xhr = new XMLHttpRequest();
	xhr.open('POST','/user/delete');
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				var res = JSON.parse(xhr.responseText);
				alert(res.msg);
				location.href = res.url;
			} 
		}
	}
	var param = {
			ui_num:list.ui_num
	}
	xhr.send(JSON.stringify(param));
}
function update(b){
	b.onclick = updateOk;
	var tds = document.querySelectorAll('td[data-id=ui_name]');
	for(var i=0;i<tds.length;i++){
		var td = tds[i];
		var id = td.getAttribute('data-id');
		td.innerHTML = ' <input type="text" id="'+id+'" value="'+list[id]+'">';
		console.log(id);
	}
}

function updateOk(){
	var xhr = new XMLHttpRequest();
	xhr.open('POST','/user/update');
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				var res = JSON.parse(xhr.responseText);
				alert(res.msg);
				location.href = res.url;
			}
		}
	}
	var param = {
			ui_num:list.ui_num,
			ui_name:document.querySelector('input[id=ui_name]').value
	}
	console.log(param);
	xhr.send(JSON.stringify(param));
}
</script>
</html>