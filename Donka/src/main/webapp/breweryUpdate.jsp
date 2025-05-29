<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒蔵情報更新（仮）</title>
</head>
<body>

<%--登録してる酒蔵をセレクトとかで表示して、そこから酒蔵IDを指定できるようにしたい --%>

<form action = "BreweryEdit" method = "POST">
酒蔵のID：<input type = "text" name = "breweryId" placeholder = "酒蔵のID"><br>
酒蔵の名前：<input type = "text" name = "breweryName" placeholder = "酒蔵の名前"><br>
酒蔵の写真： <input type = "text" name = "bImgPath" placeholder = "写真のURL"><br>

<input type = "submit" value="変更する">
</form>

</body>
</html>