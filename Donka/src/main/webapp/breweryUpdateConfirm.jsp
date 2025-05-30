<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.BreweryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒蔵変更確認画面</title>
</head>
<body>

<%
int breweryId = (int) request.getAttribute("breweryId");
String breweryExplanation = (String) request.getAttribute("breweryExplanation");
boolean reservationFlag = (boolean) request.getAttribute("reservationFlag");
String reservationPath = (String) request.getAttribute("reservationPath");
%>

こちらに変更してよろしいですか？<br>
<form action = "BreweryEditConfirm" method = "POST">
酒蔵のID：<input type = "text" name = "breweryId" value="<%= breweryId %>" disabled><br>
酒蔵の説明：<input type = "text" name = "breweryExplanation" value="<%= breweryExplanation %>" disabled><br>
予約の可否：<input type = "checkbox" name = "reservationFlag" value="<%= reservationFlag %>" disabled><br>
予約先のURL： <input type = "text" name = "ReservationPath" value="<%= reservationPath %>" disabled><br>

<input type = "submit" value="登録する">
</form>

<form action="BreweryEdit" method="POST">
<input type="submit" value="戻る">
</form>


</body>
</html>