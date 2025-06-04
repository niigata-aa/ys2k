<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録確認画面</title>
<link rel="stylesheet" href="user_con.css">
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
      <div>登録確認<span></span></div>
    </div>
    <br>
    <div class="user">

    ユーザID:       <%= request.getAttribute("userId") %>
    
	パスワード:     <%= request.getAttribute("password") %>
	
	ニックネーム:   <%= request.getAttribute("nickname") %>
	
	<form action="UserRegistConfirm" method="post">
		<input type="hidden" name="userId" value="<%= request.getAttribute("userId") %>"><br>
		<input type="hidden" name="password" value="<%= request.getAttribute("password") %>"><br>
		<input type="hidden" name="nickname" value="<%= request.getAttribute("nickname") %>"><br>
		<input type="submit" value="登録する">
	</form>
	
	<form action="UserRegistConfirm" method="post">
		<input type="hidden" name="userId" value="<%= request.getAttribute("userId") %>">
		<input type="submit" value="戻る">
	</form>
	
	</div>

<jsp:include page="footer.jsp"></jsp:include>



	
</body>
</html>