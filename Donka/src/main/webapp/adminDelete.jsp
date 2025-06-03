<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者用削除画面</title>
</head>
<body>

	<form action="SakeDelete" method="post">
		<input type="submit" value="酒の削除"><br>
	</form>

	<form action="CommentSelectBreweryServlet" method="post">
		<input type="submit" value="コメントの削除"><br>
	</form>
	
	<a href="adminTop.jsp">トップページ</a>

</body>
</html>