<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.SakeBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
こちらを削除してよろしいですか？<br>
	<%
		List<SakeBean> sakeList = (List<SakeBean>) request.getAttribute("sakeList");
	%>

酒:<%= session.getAttribute("sake")%><br>

<form action="SakeDelete" method="POST">
<input type="submit" value="戻る">
</form>
<form action="SakeDeleteComplete" method="POST">
<input type="submit" value="削除する">
</form>

</body>
</html>