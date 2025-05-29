<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.CommentBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>口コミ画面</title>
</head>
<body>
		<form action="CommentRegist" method="POST">
		<%-- コメント一覧表示--%>  
		<%
			int breweryId = (Integer) request.getAttribute("breweryId");
			String userId = (String) request.getAttribute("userId");
		%>
		<%
			List<CommentBean> commentList = (List<CommentBean>) request.getAttribute("commentList");
		%>
		<%
				for (CommentBean comment : commentList) {
		%>
			<%=comment.getUserId() %><br>
			<%=comment.getContent() %><br>
			<%=comment.getCommentId() %><br>
		<%
				}
		%>
	
		<%-- ニックネーム表示 --%>
		<input type="hidden" name="commentId" value="6">
		<textarea id="content" name="content"></textarea><br>
		<input type="hidden" name="userId" value=<%=userId %>>
		<input type="hidden" name="breweryId" value=<%=breweryId %>>
		<input type=submit value="投稿">
	</form>
</body>
</html>