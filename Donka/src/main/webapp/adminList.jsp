<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者一覧確認画面</title>
<link rel="stylesheet" type="text/css" href="admin.css"> 
</head>
<body>
<jsp:include page="adminHeader.jsp" />
<div class="main-content"> <%-- ここでコンテンツを囲みます --%>
	<h1>管理者一覧確認</h1>

	<form action="SakeList" method="post">
		<input type="submit" value="酒確認画面" class="submit-button"> <%-- classを追加 --%>
	</form>
	
	<form action="BreweryList" method="post">
		<input type="submit" value="酒蔵確認画面" class="submit-button"> <%-- classを追加 --%>
	</form>	
	
	<form action="adminTop.jsp" method="post"> 
		<input type="submit" value="管理者トップページ画面に戻る" class="submit-button"> <%-- classを追加 --%>
	</form>
</div>
</body>
</html>