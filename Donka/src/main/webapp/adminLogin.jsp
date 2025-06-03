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
	
		<label id="adminId">
			管理者ID:<input type="text" name="adminId"><br>
		</label>
		
		<label id="password">
			パスワード:<input type="password"name="password"><br>
		</label>
		
		<label class="validation">
			<!-- Validation check -->
			<% if( request.getAttribute("errorLog") != null){%>
				<div style="color:red"><%= request.getAttribute("errorLog") %></div>
			<% } %>
		</label>
		
		<label id="login">
			<input type="submit" value="ログイン">
		</label>
		
	</form>

</body>
</html>