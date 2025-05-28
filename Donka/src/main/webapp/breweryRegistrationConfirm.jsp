<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
こちらを登録してよろしいですか？<br>
<jsp:useBean id="brewery" scope="session" class="model.entity.BreweryBean" />

登録する酒蔵の名前：<jsp:getProperty name="brewery" property="name"/><br>
登録する酒蔵の緯度：<jsp:getProperty name="brewery" property="latitude"/><br>
登録する経度：<jsp:getProperty name="brewery" property="longitude"/><br>
登録する酒蔵の住所：<jsp:getProperty name="brewery" property="address"/><br>
登録する酒蔵の予約の有無：<jsp:getProperty name="brewery" property="reservationFlag"/><br>
ここに予約フラグを書く
登録する酒蔵の説明：<jsp:getProperty name="brewery" property="breweryExplanation"/><br>
登録する酒蔵の名前：<jsp:getProperty name="brewery" property="areaId"/><br>

<form action="BreweryRegist" method="POST">
<input type="submit" value="戻る">
</form>
<form action="Complete" method="POST">
<input type="submit" value="登録する">
</form>

</body>
</html>