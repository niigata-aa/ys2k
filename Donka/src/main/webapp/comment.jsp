<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,model.entity.CommentBean,model.entity.UserBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>口コミ画面</title>
<link rel="stylesheet" href="Comment.css">
</head>
<body>

		<jsp:include page="header.jsp"></jsp:include>

		<ul class="sakura">
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
			<li></li>
		</ul>


		<div class="body"></div>
		<div class="grad"></div>
		<div class="header">
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
			<%=userList.get(count).getNickname()%><br> コメント：
			<%=comment.getContent()%><br> <br>
			<%
			count++;
			%>
			<%
			}
			%>

			<%-- ニックネーム表示 --%>

			<div>
				ご意見<span></span>
			</div>
		</div>
		<br>
		<div class="main">
			ニックネーム：
			<%=myName%><br> コメント入力：<br>
			<form action="CommentRegist" method="POST">
			<textarea id="content" placeholder="ご意見" name="content"></textarea>
				<br> <input type="hidden" name="userId" value=<%=userId%>>
				<input type="hidden" name="breweryId" value=<%=breweryId%>>

				<input type="button" value="戻る" onClick="history.back()"> 
				<input type=submit value="投稿">
			</form>
		</div>
</body>
</html>