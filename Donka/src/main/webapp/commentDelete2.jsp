<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.CommentBean,model.entity.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント削除画面2</title>
<link rel="stylesheet" type="text/css" href="admin.css"> <%-- 共通CSSを読み込む --%>
<link rel="stylesheet" type="text/css" href="adminForm.css"> <%-- フォーム用CSSを読み込む --%>
<link rel="stylesheet" type="text/css" href="adminComment.css"> <%-- 新しいコメント表示用CSSを読み込む --%>
</head>
<body>

<jsp:include page="adminHeader.jsp" /> <%-- ヘッダーをインクルード --%>

<div class="main-content"> <%-- ここでコンテンツを囲みます --%>
	<h1>コメント削除 - コメント選択</h1>
	
	<%-- コメント一覧表示--%>  
	<div class="comment-list-container">
		<%
			int breweryId = (Integer) request.getAttribute("breweryId");
			List<CommentBean> commentList = (List<CommentBean>) request.getAttribute("commentList");
			List<UserBean> userList = (List<UserBean>) request.getAttribute("userList");
			int count = 0;
		%>
		<% if (commentList != null && !commentList.isEmpty()) { %>
			<%
					for (CommentBean comment : commentList) {
						
			%>
				<div class="comment-item">
					<p class="comment-nickname">ニックネーム：<%=userList.get(count).getNickname() %></p>
					<p class="comment-content">コメント：<%=comment.getContent() %></p>
					<p class="comment-id">コメントID：<%=comment.getCommentId() %></p>
				</div>
			<% 
						count++;
					}	
			%>
		<% } else { %>
			<p class="no-comments">この酒蔵にはコメントがありません。</p>
		<% } %>
	</div>
	
	<form action="CommentDelete" method="POST">	
		<%-- breweryIdをCommentDeleteに送る --%>
		<input type="hidden" name="breweryId" value="<%=breweryId %>">
		
		<div class="form-group">
			<label for="commentId">コメントIDを選択する:</label>
			<select name="commentId" id="commentId" required>
				<option value="" selected disabled>--コメントIDを選択--</option>
				<%
					if (commentList != null) {
						for (CommentBean comment : commentList) {
				%>
				<option value="<%=comment.getCommentId() %>"><%=comment.getCommentId() %></option>
				<%
						}
					}
				%>
			</select>
		</div>
		
		<button type="button" value="戻る" class="back-button" onClick="history.back()">戻る</button>
		
		<% if(commentList != null && !commentList.isEmpty()){%>
			<input type="submit" value="削除する" class="submit-button delete-button">
		<%}else{ %>
			<input type="submit" value="削除する" class="submit-button delete-button" disabled>
		<%} %>
	</form>

</div>
</body>
</html>