<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>失敗画面</title>
</head>
<body>
	何らかの不具合が発生しました。
	５秒後にxxxxページに戻ります。
	
	<a href="<%= request.getAttribute("url")%>">戻らない場合はこちら</a>
	
</body>
</html>