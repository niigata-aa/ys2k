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
	
	酒蔵の名前:<jsp:getProperty name="brewery" property="breweryName"/><br>
	酒蔵の説明:<jsp:getProperty name="brewery" property="breweryExplanation"/><br>
	酒蔵の緯度:<jsp:getProperty name="brewery" property="latitude"/><br>
	経度:<jsp:getProperty name="brewery" property="longitude"/><br>
	酒蔵の地域:<jsp:getProperty name="brewery" property="address"/><br>
	酒蔵の住所:<jsp:getProperty name="brewery" property="areaId"/><br>

	酒蔵の予約先:<jsp:getProperty name="brewery" property="reservationFlag"/><br>
	予約の有無:<jsp:getProperty name="brewery" property="reservationPath"/><br>
	
	<%--
	登録する酒蔵の写真のパス:<jsp:getProperty name="brewery" property="bImgPath"/><br>
	--%>
	
	登録する写真のURL：<jsp:getProperty name="brewery" property="bImgPath"/><br>
	<img src="/Donka/img/<jsp:getProperty name="brewery" property="bImgPath"/>">

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
	
	<button onclick="history.back()">戻る</button>

</body>
</html>