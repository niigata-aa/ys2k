<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者用変更画面</title>
<link rel="stylesheet" type="text/css" href="admin.css"> <%-- adminTop.jspと共通のCSSを読み込む --%>
<body>

<jsp:include page="adminHeader.jsp" /> <%-- ヘッダーをインクルード --%>

<div class="main-content"> <%-- ここでコンテンツを囲みます --%>
	<h1>管理者用変更</h1>
	<a href="SakeUpdatePreparation">酒の変更</a><br>
	<a href="BreweryUpdatePreparation">酒蔵の変更</a><br>
	<a href="adminTop.jsp">トップページ</a>
</div>

</body>
</html>