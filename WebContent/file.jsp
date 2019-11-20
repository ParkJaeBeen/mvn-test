<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/file" enctype="multipart/form-data">
	FILE : <input type="file" name="ftFile"><br>
	ID : <input type="text" name="ftId"><br>
	NAME : <input type="text" name="ftName"><br>
	<button>파일전송</button>
</form>
</body>
</html>