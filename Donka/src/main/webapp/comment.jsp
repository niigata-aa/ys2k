<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.CommentBean,model.entity.UserBean"%>
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
			String myName = (String) request.getAttribute("myName");
		%>
		<%
			List<CommentBean> commentList = (List<CommentBean>) request.getAttribute("commentList");
			List<UserBean> userList = (List<UserBean>) request.getAttribute("userList");
			int count = 0;
		%>
		<%
				for (CommentBean comment : commentList) {
		%>
			ユーザーのニックネーム：
			<%=userList.get(count).getNickname() %><br>
			コメント：
			<%=comment.getContent() %><br>
			<br>
			<% count++; %>
		<%
				}
		%>
	
		<%-- ニックネーム表示 --%>
		ニックネーム：
		<%=myName %><br>
		コメント入力：<br>
		<textarea id="content" name="content"></textarea><br>
		<input type="hidden" name="userId" value=<%=userId %>>
		<input type="hidden" name="breweryId" value=<%=breweryId %>>
		<input type="button" value="戻る" onClick="history.back()">
		<input type=submit value="投稿">
	</form>
</body>
</html>