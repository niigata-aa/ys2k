<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>管理者ログイン</title>
</head>
<body>
	<form action="AdminLogin" method="post">
		管理者ID:<input type="text" name="id"><br>
		パスワード:<input type="text"name="password"><br>
		<input type="submit" value="ログイン">
	</form>
</body>
</html>