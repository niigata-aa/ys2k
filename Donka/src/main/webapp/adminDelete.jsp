<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者用削除画面</title>
<link rel="stylesheet" type="text/css" href="admin.css"> <%-- 共通CSSを読み込む --%>
</head>
<body>

<jsp:include page="adminHeader.jsp" /> <%-- ヘッダーをインクルード --%>

<div class="main-content"> <%-- ここでコンテンツを囲みます --%>
	<h1>管理者用削除</h1>

	<form action="SakeDelete" method="post">
		<input type="submit" value="酒の削除" class="submit-button delete-button"> <%-- classを追加--%>
	</form>

	<form action="CommentSelectBreweryServlet" method="post">
		<input type="submit" value="コメントの削除" class="submit-button delete-button"> <%-- classを追加--%>
	</form>
	
	<a href="adminTop.jsp">トップページ</a>
</div>

</body>
</html>