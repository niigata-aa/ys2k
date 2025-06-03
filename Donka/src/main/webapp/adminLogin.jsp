<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ログイン</title>
<link rel="stylesheet" type="text/css" href="adminLogin.css"> <%-- 新しいログイン画面用CSSを読み込む --%>
</head>
<body>

<div class="login-container"> <%-- ここでコンテンツを囲みます --%>
	<h1>管理者ログイン</h1>

	<form action="AdminLogin" method="post">
	
		<div class="form-group"> <%-- form-groupで入力フィールドを囲む --%>
			<label for="adminId">管理者ID:</label>
			<input type="text" id="adminId" name="adminId">
		</div>
		
		<div class="form-group"> <%-- form-groupで入力フィールドを囲む --%>
			<label for="password">パスワード:</label>
			<input type="password" id="password" name="password">
		</div>
		
		<div class="validation-message"> <%-- エラーメッセージ表示用のdiv --%>
			<% if( request.getAttribute("errorLog") != null){%>
				<%= request.getAttribute("errorLog") %>
			<% } %>
		</div>
		
		<input type="submit" value="ログイン" class="submit-button">
		
	</form>

</div>

</body>
</html>