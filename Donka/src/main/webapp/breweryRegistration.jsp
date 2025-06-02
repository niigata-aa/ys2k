<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒蔵登録画面</title>
</head>
<body>
	<form action="BreweryRegist" method="POST" enctype="multipart/form-data">
	酒蔵のid：<input type="text" id="breweryId" name="breweryId" required><br>
	酒蔵の名前：<input type="text" id="breweryName" name="breweryName" required><br>
	酒蔵の説明：<input type="text" id="breweryExplanation" name="breweryExplanation" required><br>
	酒蔵の緯度：<input type="text" id="latitude" name="latitude" required><br>
	酒蔵の経度：<input type="text" id="longitude" name="longitude" required><br>
	
	酒蔵の地域：<select name="areaId" id="areaId" required>
					<option selected disabled value="">--酒蔵の地域を選択--</option>
					<option value=1>上越</option>
					<option value=2>中越</option>
					<option value=3>下越</option>
					<option value=4>佐渡</option>
				</select><br>
				
	酒蔵の住所：<input type="text" id="address" name="address" required><br>
	予約の可否：<input type="checkbox" id="reservetionFlag" name="reservetionFlag" required/><br>
	
	
<%-- 酒蔵の写真：<input type="text" id="bImgPath" name="bImgPath" value="写真のURL"><br> --%>
	酒蔵の写真：<input type="file" id="bImgPath" name="bImgPath" accept="image/jpeg" required><br>
	予約先URL：<input type="text" id="reservetionPath" name="reservetionPath"><br>
	<input type="submit" value="登録する">
	</form>
	<%-- チェックされている場合に、予約先URLを入力できるようにしたい --%>

	<form action="adminRegistration.jsp" method="POST">
		<input type="submit" value="戻る">
	</form>
</body>
</html>