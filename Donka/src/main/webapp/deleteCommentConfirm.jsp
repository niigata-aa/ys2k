<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.CommentBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント削除確認画面</title>
<link rel="stylesheet" type="text/css" href="admin.css"> <%-- 共通CSSを読み込む --%>
<link rel="stylesheet" type="text/css" href="adminConfirm.css"> <%-- 確認画面用CSSを読み込む --%>
</head>
<body>

<jsp:include page="adminHeader.jsp" /> <%-- ヘッダーをインクルード --%>

<div class="main-content"> <%-- ここでコンテンツを囲みます --%>
	<h1>コメント削除確認</h1>
	<p>こちらを削除してもよろしいでしょうか?</p>
	
	<div class="confirmation-details">
		<div class="detail-item">
			<span>削除するコメント:</span>
			<%
				int commentId = (Integer) request.getAttribute("commentId");
				int breweryId = (Integer) request.getAttribute("breweryId");
				List<CommentBean> commentList = (List<CommentBean>) request.getAttribute("commentList");
			%>
			<%
					for (CommentBean comment : commentList) {
			%>
				<%=comment.getContent() %><br>
			<%
					}
			%>
		</div>
	</div>
	
	<form action="CommentDeleteConfirm" method="POST" class="confirmation-form">
		<input type="hidden" name="commentId" value="<%=commentId %>">
		<input type="hidden" name="breweryId" value="<%=breweryId %>">
		
		<button type="button" value="戻る" class="back-button" onClick="history.back()">戻る</button>
		<input type="submit" value="削除する" class="submit-button delete-button">
	</form>
</div>
</body>
</html>