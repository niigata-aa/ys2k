<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録画面</title>
</head>
<body>
	<form action="UserRegist" method="post">
		ユーザーID:<input type="text" name="userId"><br>
		パスワード:<input type="password"name="password"><br>
		ニックネーム:<input type="text"name="nickname"><br>
		<input type="submit" value="会員登録">
	</form>
</body>
</html>