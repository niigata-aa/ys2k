<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" href="login.css">
</head>
<body>
<div class="container">
	<form action="UserLogin" method="post">
	<p class="fsize">ログイン画面</p>
		<input type="text" placeholder="username" name="userId"><br>
		<input type="password" placeholder="password" name="password"><br>
		<input type="submit" value="ログイン">
		
	</form>
	</div>

</body>
</html>

