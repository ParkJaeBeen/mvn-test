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
		<tr>
			<th>번호</th>
			<td id="pbNum" data-id="pbNum"></td>
		</tr>
 		<tr>
			<th>제목</th>
			<td data-id="pbTitle" data-if="text"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td data-id="pbContent" data-if="text"></td>
		</tr>
		<tr>
			<th>수정자</th>
			<td data-id="creusr" data-if="text"></td>
		</tr>
		<tr>
			<th>이미지1</th>
			<td  data-id="pbImg1" data-for="img"></td>
		</tr>
		<tr>
			<th>이미지2</th>
			<td  data-id="pbImg2" data-for="img"></td>
		</tr>
	<tbody id="tBody">
	</tbody>
</table>
<button type="button" class="btn btn-outline-secondary" onclick="upd(this)">수정</button>
<button type="button" class="btn btn-outline-secondary" onclick="del()">삭제</button>
</div>
<script>
var pList;

window.onload = function() {
		var xhr = new XMLHttpRequest();
		xhr.open('GET','/pboard/content?pb_num=${param.pb_num}');
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					pList = JSON.parse(xhr.responseText);
					console.log(xhr.responseText);
					var tds = document.querySelectorAll('td[data-id]');
					for (var idx = 0; idx < tds.length; idx++) {
						var td = tds[idx];
						var key = td.getAttribute('data-id');
						var dataFor = td.getAttribute('data-for');
						if(dataFor && dataFor=='img'){
							td.innerHTML = '<img src="' + pList[key] +'" width="100px" height="100px">';
						}else{
							td.innerHTML = pList[key];
						}
					}
				}
			}
	}
	xhr.send();
}

	function del() {
		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/pboard/delete');
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					var res = JSON.parse(xhr.responseText);
					console.log(xhr.responseText);
					alert(res.msg);
					location.href = res.url;
				}
			}
		}
		var param = {
			pbNum : pList.pbNum
		}
		console.log(param);
		xhr.send(JSON.stringify(param));
	}

	function upd(b) {
		b.onclick = updateOk;
		var tds = document.querySelectorAll('td[data-id]');
		for (var i = 0; i < tds.length; i++) {
			var td = tds[i];
			var id = td.getAttribute('data-id');
			var dataFor = td.getAttribute('data-for');
			var dataIf = td.getAttribute('data-if');
			if(dataFor && dataFor=="img") {
				td.innerHTML = ' <input type="file" id="'+id+'" value="'+pList[dataFor]+'">';
				} 
			else if(dataIf && dataIf=="text") {
				td.innerHTML = ' <input type="text" id="'+id+'" value="'+pList[id]+'">';
				}
			else if(id){
				td.innerHTML = ' <input type="hidden" id="'+id+'" value="'+pList[id]+'">'
			}
			console.log(id);
		}
	}

	function updateOk() {
		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/pboard/update');
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					var res = JSON.parse(xhr.responseText);
					alert(res.msg);
					location.href = res.url;
				}
			}
		}
		var form = makeFormData();
		console.log(	form);
		xhr.send(form);
	}
</script>
</body>
</html>