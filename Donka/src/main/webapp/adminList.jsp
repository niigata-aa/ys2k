<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者一覧確認画面</title>
</head>
<body>

	<form action="SakeList" method="post">
		<input type="submit" value="酒確認画面">
	</form>
	
	<form action="BreweryList" method="post">
		<input type="submit" value="酒一覧確認画面">
	</form>	
	
	<form action="adminTop.jsp" method="post"> 
		<input type="submit" value="管理者トップページ画面に戻る">
	</form>
	
</body>
</html>