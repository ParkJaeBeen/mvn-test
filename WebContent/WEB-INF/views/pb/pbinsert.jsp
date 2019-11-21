<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
제목 : <input type="text" id="pbTitle"><br>
내용 : <input type="text" id="pbContent"><br>
이미지 : <input type="file" id="pbImg" multiple="multiple"><br>
<button onclick="submit()">send</button>
<script>
function submit() {
	var form = new FormData();
	form.append("pbTitle",document.querySelector('#pbTitle').value);
	form.append("pbContent",document.querySelector('#pbContent').value);
	form.append("pbImg1",pbImg.files[0]);
	form.append("pbImg2",pbImg.files[1]);
	
	var xhr = new XMLHttpRequest();
	xhr.open('POST','/pb/insert');
	xhr.onreadystatechange = function(){
		if(xhr.readyState==xhr.DONE){
			if(xhr.status==200){
				alert('저장완료!');
				location.href='/views/pb/pblist';
			}
		}
	}
	console.log(form);
	xhr.send(form);
}
</script>
</body>
</html>