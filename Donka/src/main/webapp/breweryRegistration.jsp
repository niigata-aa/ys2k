<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒蔵登録画面</title>
<link rel="stylesheet" type="text/css" href="adminForm.css"> <%-- 新しいフォーム用CSSを読み込む --%>
<link rel="stylesheet" type="text/css" href="admin.css"> <%-- 共通CSSを読み込む --%>
</head>
<body>

<jsp:include page="adminHeader.jsp" /> <%-- ヘッダーをインクルード --%>

<div class="main-content"> <%-- ここでコンテンツを囲みます --%>
	<h1>酒蔵登録</h1>
	
	<%-- Validation check --%>
	<% if( request.getAttribute("errorLog") != null){%>
		<div class="error-message"><%= request.getAttribute("errorLog") %></div>
	<% } %>

	<form action="BreweryRegist" method="POST" enctype="multipart/form-data">
	
		<div class="form-group">
			<label for="breweryName">酒蔵の名前：</label>
			<input type="text" id="breweryName" name="breweryName" required>
		</div>
		
		<div class="form-group">
			<label for="breweryExplanation">酒蔵の説明：</label>
			<textarea id="breweryExplanation" name="breweryExplanation" rows="4" required></textarea> <%-- textareaに変更 --%>
		</div>
		
		<div class="form-group">
			<label for="latitude">酒蔵の緯度：</label>
			<input type="text" id="latitude" name="latitude" required>
		</div>
		
		<div class="form-group">
			<label for="longitude">酒蔵の経度：</label>
			<input type="text" id="longitude" name="longitude" required>
		</div>
		
		<div class="form-group">
			<label for="areaId">酒蔵の地域：</label>
			<select name="areaId" id="areaId" required>
				<option selected disabled value="">--酒蔵の地域を選択--</option>
				<option value="1">上越</option>
				<option value="2">中越</option>
				<option value="3">下越</option>
				<option value="4">佐渡</option>
			</select>
		</div>
					
		<div class="form-group">
			<label for="address">酒蔵の住所：</label>
			<input type="text" id="address" name="address" required>
		</div>
		
		<div class="form-group checkbox-group">
			<input type="checkbox" id="reservetionFlag" name="reservetionFlag">
			<label for="reservetionFlag">予約の可否</label>
		</div>
		
		<div class="form-group">
			<label for="bImgPath">酒蔵の写真：</label>
			<input type="file" id="bImgPath" name="bImgPath" accept="image/jpeg, image/png" required> <%-- acceptにimage/pngを追加 --%>
		</div>
		
		<div class="form-group">
			<label for="reservetionPath">予約先URL：</label>
			<input type="text" id="reservetionPath" name="reservetionPath">
		</div>
		
		<input type="submit" value="登録する" class="submit-button">
		
	</form>
	
	<form action="adminRegistration.jsp" method="POST">
		<input type="submit" value="戻る" class="back-button">
	</form>
</div>

<%-- JavaScriptでチェックボックスの状態に応じて予約先URLの表示/非表示を切り替える --%>
<script>
document.addEventListener('DOMContentLoaded', function() {
    const reservationFlagCheckbox = document.getElementById('reservetionFlag');
    const reservationPathGroup = reservationFlagCheckbox.closest('.main-content').querySelector('.form-group:has(#reservetionPath)');

    function toggleReservationPath() {
        if (reservationFlagCheckbox.checked) {
            reservationPathGroup.style.display = 'block';
            reservationPathGroup.querySelector('input').setAttribute('required', 'required'); // 必須にする
        } else {
            reservationPathGroup.style.display = 'none';
            reservationPathGroup.querySelector('input').removeAttribute('required'); // 必須を解除
            reservationPathGroup.querySelector('input').value = ''; // 値をクリア
        }
    }

    // 初期表示時
    toggleReservationPath();

    // チェックボックスの状態が変更された時
    reservationFlagCheckbox.addEventListener('change', toggleReservationPath);
});
</script>

</body>
</html>