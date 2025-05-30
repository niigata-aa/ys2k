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
酒蔵の説明：<input type = "text" name = "breweryExplanation" placeholder = "酒蔵の説明"><br>
    		<input type="hidden" name="reservationFlag" value="false"> <%-- 予約可否のフラグが選択されてない場合にFalse --%>
予約の可否：<input type = "checkbox" name = "reservationFlag" value = "true"><br>
予約先のURL： <input type = "text" name = "reservationPath" placeholder = "予約先のURL"><br>

<input type = "submit" value="変更する">
</form>

</body>
</html>