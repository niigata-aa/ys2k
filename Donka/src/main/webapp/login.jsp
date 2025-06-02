<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン画面</title>
<link rel="stylesheet" href="login.css">
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
      <div>ご来店<span></span></div>
    </div>
    <br>
    <div class="login">
	<form action="UserLogin" method="post">
		<input type="text" placeholder="愛称" name="userId"><br>
		<input type="password" placeholder="合言葉" name="password"><br>
		<input type="submit" value="ログイン">
		
	</form>
	</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>

