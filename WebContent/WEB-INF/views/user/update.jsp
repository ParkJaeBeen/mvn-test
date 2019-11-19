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
<h2>회원가입</h2>
<table class="table">
	 <tbody>
	 	<tr>	
	 		<th>번호</th>
	 		<td>${param.ui_num}</td>
	 	</tr>
	 	<tr>
	 		<th>아이디</th>
	 		<td><input type="text" id="ui_id" placeholder="ID를 작성해주세요"></td>
	 	</tr>
	 	<tr>
	 		<th>닉네임</th>
	 		<td><input type="text" id="ui_name" placeholder="닉네임을 작성해주세요"></td>
	 	</tr>
	 	<tr>
	 		<th>비밀번호</th>
	 		<td><input type="password" id="ui_pwd" placeholder="비밀번호를 작성해주세요"></td>
	 	</tr>
	 	<tr>
	 		<td><button onclick="update()">수정</button></td>
	 	</tr>
	 </tbody>
</table>
</div>
<script>

	function update() {
		var xhr = new XMLHttpRequest();
		xhr.open('POST', '/user/update')
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					var res = JSON.parse(xhr.responseText);
					alert(res.msg);
					location.href = res.url;
				}
			}
		}
		var param = {
			ui_id : document.getElementById('ui_id').value,
			ui_name : document.getElementById('ui_name').value,
			ui_pwd : document.getElementById('ui_pwd').value
		}
		param = JSON.stringify(param);
		xhr.send(param);
	}
</script>
</body>
</html>