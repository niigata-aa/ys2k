<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.BreweryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒蔵一覧</title>
</head>
<body>

<h1>地域</h1>
<form action = "BrewerySearch" method = "POST">
1.上越：<input type = "checkbox" id = "1" name = "1"><br>
2.中越：<input type = "checkbox" id = "2" name = "2"><br>
3.下越：<input type = "checkbox" id = "3" name = "3"><br>
4.佐越：<input type = "checkbox" id = "4" name = "4"><br>

<input type = "submit" value ="検索">
</form>

	<%
			List<BreweryBean> breweryList = (List<BreweryBean>) request.getAttribute("breweryList");
			for (BreweryBean brewery : breweryList) {
	%>
	<%=brewery.getBreweryName() %>
	<%=brewery.getBImgPath() %>"><br><br>

	<%
		}
	%>


</body>
</html>