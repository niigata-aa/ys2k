<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録確認画面</title>
</head>
<body>
	ユーザID:       <%= request.getAttribute("userId") %>
	パスワード:     <%= request.getAttribute("password") %>
	ニックネーム:   <%= request.getAttribute("nickname") %>
	
	<form action="UserRegistConfirm" method="post">
		<input type="hidden" name="userId" value="<%= request.getAttribute("userId") %>">
		<input type="hidden" name="password" value="<%= request.getAttribute("password") %>">
		<input type="hidden" name="nickname" value="<%= request.getAttribute("nickname") %>">
		<input type="submit" value="登録する">
	</form>
	
	<form action="UserRegistConfirm" method="post">
		<input type="hidden" name="userId" value="<%= request.getAttribute("userId") %>">
		<input type="submit" value="戻る">
	</form>
</body>
</html>