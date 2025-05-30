<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>口コミ確認画面</title>
</head>
<body>
		<%-- ニックネーム表示 --%>
		<%-- 書いたコメント表示 --%> 
	<%
		//int commentId = (Integer) request.getAttribute("commentId");
		String content = (String) request.getAttribute("content");
		String userId = (String) request.getAttribute("userId");
		int breweryId = (Integer) request.getAttribute("breweryId");
		String myName = (String) request.getAttribute("myName");
	%>
	ニックネーム：<%=myName %><br>
	投稿内容：<%=content %><br>

	こちらで投稿してもよろしいでしょうか？<br>
	<form action="CommentRegistConfirm" method="POST">
	
		<%-- <input type="hidden" name="commentId" value="8">--%>
		<input type="hidden" name="content" value=<%= content %>>
		<input type="hidden" name="userId" value=<%=userId %>>
		<input type="hidden" name="breweryId" value=<%=breweryId %>>
		<input type="button" value="戻る" onClick="history.back()">
		<input type=submit value="決定する">
	</form>
	
</body>
</html>