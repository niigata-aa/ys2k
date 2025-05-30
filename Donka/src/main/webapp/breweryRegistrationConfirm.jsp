<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒蔵登録画面</title>
</head>
<body>
	こちらを登録してよろしいですか？<br>
	<jsp:useBean id="brewery" scope="request" class="model.entity.BreweryBean" />
	
	登録する酒蔵の名前:<jsp:getProperty name="brewery" property="breweryName"/><br>
	登録する酒蔵の説明:<jsp:getProperty name="brewery" property="breweryExplanation"/><br>
	登録する酒蔵の緯度:<jsp:getProperty name="brewery" property="latitude"/><br>
	登録する経度:<jsp:getProperty name="brewery" property="longitude"/><br>
	登録する酒蔵の地域:<jsp:getProperty name="brewery" property="address"/><br>
	登録する酒蔵の住所:<jsp:getProperty name="brewery" property="areaId"/><br>

	登録する酒蔵の予約の有無:<jsp:getProperty name="brewery" property="reservationFlag"/><br>
	登録する予約の有無:<jsp:getProperty name="brewery" property="reservationPath"/><br>
	
	登録する酒蔵の写真のパス:<jsp:getProperty name="brewery" property="bImgPath"/><br>
	
	<form action="未設定" method="POST">
		<input type="submit" value="戻る">
	</form>
	<form action="BreweryRegistConfirm" method="POST">
		<input type ="hidden" name ="breweryName2" value="<jsp:getProperty name="brewery" property="breweryName"/>">
		<input type ="hidden" name ="breweryExplanation2" value="<jsp:getProperty name="brewery" property="breweryExplanation"/>">
		<input type ="hidden" name ="latitude2" value="<jsp:getProperty name="brewery" property="latitude"/>">
		<input type ="hidden" name ="longitude2" value="<jsp:getProperty name="brewery" property="longitude"/>">
		<input type ="hidden" name ="address2" value="<jsp:getProperty name="brewery" property="address"/>">
		<input type ="hidden" name ="areaId2" value="<jsp:getProperty name="brewery" property="areaId"/>">
		<input type ="hidden" name ="reservationFlag2" value="<jsp:getProperty name="brewery" property="reservationFlag"/>">
		<input type ="hidden" name ="reservationPath2" value="<jsp:getProperty name="brewery" property="reservationPath"/>">
		<input type ="hidden" name ="bImgPath2" value="<jsp:getProperty name="brewery" property="bImgPath"/>">
		<input type="submit" value="登録する">
	</form>

</body>
</html>