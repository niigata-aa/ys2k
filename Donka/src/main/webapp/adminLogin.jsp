<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="adminLogin.css">
<title>管理者ログイン</title>
</head>
<body>

	<form action="AdminLogin" method="post">
		管理者ID:<input type="text" name="adminId"><br>
		
		パスワード:<input type="text"name="password"><br>
		
		<!-- Validation check -->
		<% if( request.getAttribute("errorLog") != null){%>
			<div style="color:red"><%= request.getAttribute("errorLog") %></div>
		<% } %>
		
		<input type="submit" value="ログイン">
	</form>

</body>
</html>