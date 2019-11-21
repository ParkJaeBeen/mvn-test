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
<div class="progress">
  <div id="rDiv" class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 50%"></div>
</div>
<script>
window.onload = function(){
	var rDiv = document.querySelector('div[aria-valuenow]');
	var rDiv2 = document.querySelector('div[aria-valuemax]');
	console.log(rDiv);
	var i  = 1;
	var sInt = setInterval(function(){
		rDiv.value =  i++; 
		rDiv.innerHTML = (i/rDiv2.value)*100+"%";
		if(i==1000){
			clearInterval(sInt);
		}
	},10);	//100 = 0.1초
}
</script>
</body>
</html>