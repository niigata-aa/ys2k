<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.CommentBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント削除確認画面</title>
</head>
<body>
	こちらを削除してもよろしいでしょうか?<br>
	削除するコメント：
	<%-- コメント一覧表示--%>  
	<%
		int commentId = (Integer) request.getAttribute("commentId");
		int breweryId = (Integer) request.getAttribute("breweryId");
	%>
	<%
		List<CommentBean> commentList = (List<CommentBean>) request.getAttribute("commentList");
	%>
	<%
			for (CommentBean comment : commentList) {
	%>
		<%=comment.getContent() %><br>
	<%
			}
	%>
	
	
	<br>
	
	<form action="CommentDeleteConfirm" method="POST">
		<input type="hidden" name="commentId" value=<%=commentId %>>
		<input type="hidden" name="breweryId" value=<%=breweryId %>>
		<input type="button" value="戻る" onClick="history.back()">
		<input type="submit" value="削除する">
	</form>
	<%-- 
	<form action="commentDelete2.jsp" method="POST">
		<input type="button" value="戻る">
	</form>--%>
</body>
</html>