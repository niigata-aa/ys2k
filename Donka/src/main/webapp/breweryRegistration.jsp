<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒蔵登録画面</title>
</head>
<body>
	<form action="BreweryRegist" method="POST">
	酒蔵の名前：<input type="text" id="name" name="name"><br>
	酒蔵の説明：<input type="text" id="explanation" name="explanation"><br>
	酒蔵の緯度：<input type="text" id="explanation" name="explanation"><br>
	酒蔵の経度：<input type="text" id="longitude" name="longitude"><br>
	酒蔵の住所：<input type="text" id="address" name="address"><br>
	酒蔵の地域：<select name="area" id="area">
					<option value="">--酒蔵の地域を選択--</option>
					<option value=1>上越</option>
					<option value=2>中越</option>
					<option value=3>下越</option>
					<option value=4>佐渡</option>
				</select>
	予約の可否：<input type="checkbox" id="reserb" name="reserb"/>
	
	<button onclick="location.href='./adminregistaration.jsp'">戻る</button><br>
	<input type="submit" value="登録する">
	
	</form>

</body>
</html>