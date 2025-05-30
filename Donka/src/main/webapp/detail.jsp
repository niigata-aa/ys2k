<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>詳細ページ</title>
</head>
<body>
	<%-- 酒の口コミだけ --%>
	<%-- この時は、酒蔵IDともしかしたらuser_id送るかも --%>
	<%-- 酒蔵IDとuser_idを値に参照したい --%>
	<form action="CommentList" method="POST">
		<input type="hidden" name="breweryId" value="1">
		<input type="hidden" name="userId" value="fgh">
		<input type="submit" value="酒の口コミ">
	</form>
</body>
</html>