<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者トップページ</title>
<link rel="stylesheet" type="text/css" href="admin.css"> 
</head>

<body>
<jsp:include page="adminHeader.jsp" />
<div class="main-content"> <%-- ここでコンテンツを囲みます --%>
	<h1>管理者トップページ</h1>
	<a href="adminList.jsp">一覧確認</a><br>
	<a href="adminRegistration.jsp">登録</a><br>
	<a href="adminUpdate.jsp">変更</a><br>
	<a href="adminDelete.jsp">削除</a><br>
	<a href="adminLogout.jsp">ログアウト</a>
</div>

</body>
</html>