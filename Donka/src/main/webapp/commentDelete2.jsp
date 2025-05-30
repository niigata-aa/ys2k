<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.CommentBean,model.entity.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント削除画面2</title>
</head>
<body>
	
   
	<%-- コメント一覧表示--%>  
	
	<%
		int breweryId = (Integer) request.getAttribute("breweryId");
	%>
	<%
		List<CommentBean> commentList = (List<CommentBean>) request.getAttribute("commentList");
		List<UserBean> userList = (List<UserBean>) request.getAttribute("userList");
		int count = 0;
	%>
	<%
			for (CommentBean comment : commentList) {
				
	%>
		<%=userList.get(count).getNickname() %>
		<%=comment.getContent() %><br>
		<%=comment.getCommentId() %><br>
		<% count++;%>
	<%
				}	
	%>
	
	
	<form action="CommentDelete" method="POST">	
	<%-- breweryIdをCommentDeleteに送る --%>
	<input type="hidden" name="breweryId" value=<%=breweryId %>>
		<%-- selectでコメントidを選択できるようにする（参照してくる） --%>
		<%-- コメント数を取得できたほうがいいかも --%>
		コメントIDを選択する
	    <select name="commentId">
		<%
			for (CommentBean comment : commentList) {
		%>
	      <option value=<%=comment.getCommentId() %>><%=comment.getCommentId() %></option>
		<%
			}
		%>
	    </select> <br>
	    <input type="button" value="戻る" onClick="history.back()">
		<input type="submit" value="削除する">
	</form>
	<%-- 
	<form action="commentDelete1.jsp" method="POST">
		<input type="button" value="戻る" >
	</form>--%>
	</body>
</html>