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
1.上越：<input type = "checkbox" value = "1" name = "areaId"><br>
2.中越：<input type = "checkbox" value = "2" name = "areaId"><br>
3.下越：<input type = "checkbox" value = "3" name = "areaId"><br>
4.佐越：<input type = "checkbox" value = "4" name = "areaId"><br>

<input type = "submit" value ="検索">
</form>

</body>
</html>

<%-- --%>