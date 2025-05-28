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
	酒蔵id：<input type="text" id="breweryId" name="breweryId" value="1234"><br>
	酒蔵の名前：<input type="text" id="breweryName" name="breweryName"><br>
	酒蔵の写真：<input type="text" id="bImgPath" name="bImgPath" value="写真のURL"><br>
	酒蔵の説明：<input type="text" id="explanation" name="explanation"><br>
	酒蔵の緯度：<input type="text" id="latitude" name="latitude"><br>
	酒蔵の経度：<input type="text" id="longitude" name="longitude"><br>
	酒蔵の住所：<input type="text" id="address" name="address"><br>
	酒蔵の地域：<select name="areaId" id="areaId">
					<option value="">--酒蔵の地域を選択--</option>
					<option value=1>上越</option>
					<option value=2>中越</option>
					<option value=3>下越</option>
					<option value=4>佐渡</option>
				</select><br>
	予約の可否：<input type="checkbox" id="reserveFlag" name="reserveFlag"/><br>
	予約先URL：<input type="text" id="reservePath" name="reservePath"><br>
	<input type="submit" value="登録する">
	</form>
	<%-- チェックされている場合に、予約先URLを入力できるようにしたい --%>

	<form action="adminRegistration.jsp" method="POST">
		<input type="submit" value="戻る">
	</form>
</body>
</html>