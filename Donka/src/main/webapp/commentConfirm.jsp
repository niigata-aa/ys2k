<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>口コミ確認画面</title>
<link rel="stylesheet" href="comment2.css">
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
		<%-- ニックネーム表示 --%>
		<%-- 書いたコメント表示 --%> 
		
			
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

<form action="CommentRegistConfirm" method="POST">
	
			
	<div class="body"></div>
    <div class="grad"></div>
    <div class="header">
    
	<%
		//int commentId = (Integer) request.getAttribute("commentId");
		String content = (String) request.getAttribute("content");
		String userId = (String) request.getAttribute("userId");
		int breweryId = (Integer) request.getAttribute("breweryId");
		String myName = (String) request.getAttribute("myName");
	%>
		
      <div>ご意見<span></span></div>
    </div>
    <br>
    <div class="main">
	ニックネーム：<%=myName %><br>
	投稿内容：<%=content %><br>

	こちらで投稿してもよろしいでしょうか？<br>

	
		<input type="hidden" name="content" value=<%= content %>>
		<input type="hidden" name="userId" value=<%=userId %>>
		<input type="hidden" name="breweryId" value=<%=breweryId %>>
		<input type="button" value="戻る" onClick="history.back()">
		<% if(content != "" && content.length() <= 200) { %>
		<input type=submit value="決定する">
		<%}else{ %>
		<input type=submit value="決定する" disabled>
		<%} %>
	 </div>
	 
	</form>
	
	
	
</body>
</html>