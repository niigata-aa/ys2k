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
		int commentId = (Integer) session.getAttribute("commentId");
		String content = (String) session.getAttribute("content");
		String userId = (String) session.getAttribute("userId");
		int breweryId = (Integer) session.getAttribute("breweryId");
	%>
	<%=userId %><br>
	<%=content %><br>

	こちらで投稿してもよろしいでしょうか？<br>
	<form action="CommentRegistConfirm" method="POST">
	
		<input type="hidden" name="commentId" value=<%=commentId %>>>
		<input type="hidden" name="content" value=<%= content %>>
		<input type="hidden" name="userId" value=<%=userId %>>
		<input type="hidden" name="breweryId" value=<%=breweryId %>>
		<input type=submit value="決定する">
	</form>
	<form action="comment.jsp" method="POST">
		<input type=submit value="戻る">
	</form>
</body>
</html>