<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト</title>
<link rel="stylesheet" href="logout.css">
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
      <div>退出<span></span></div>
    </div>
    <br>
    <div class="logout">

	ログアウトが完了しました。<br>
	5秒後にトップページに戻ります<br>
	<a href="home.jsp">戻らない場合はこちらから</a>
	
	</div>

<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>