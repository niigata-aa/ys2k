<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録画面</title>
<link rel="stylesheet" href="user_re.css">
</head>
<body>

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

<jsp:include page="header.jsp"></jsp:include>
<div class="body"></div>
    <div class="grad"></div>
    <div class="header">
      <div>会員登録<span></span></div>
    </div>
    <br>
    <div class="user">

	<form action="UserRegist" method="post">
	    <input type="text" placeholder="会員番号" name="userId"><br>
		<input type="password" placeholder="合言葉" name="password"><br>
		<input type="text" placeholder="愛称" name="nickname"><br>
		<input type="submit" value="会員登録">
	</form>
	
	<!-- Validation check -->
	<% if( request.getAttribute("errorLog") != null){%>
		<div style="color:red"><%= request.getAttribute("errorLog") %></div>
	<% } %>
	</div>
	

	
	
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>