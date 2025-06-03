<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,java.util.ArrayList,model.entity.BreweryBean,com.fasterxml.jackson.databind.ObjectMapper"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒蔵情報更新画面</title>
<link rel="stylesheet" type="text/css" href="admin.css">
<link rel="stylesheet" type="text/css" href="adminForm.css">
</head>
<body>

<jsp:include page="adminHeader.jsp" />

<div class="main-content">
	<h1>酒蔵情報更新</h1>
	
	<%
	List<BreweryBean> breweryList = (List<BreweryBean>)request.getAttribute("breweryList");
	BreweryBean selectedBrewery = (BreweryBean)request.getAttribute("selectedBrewery"); // ページロード時に選択される酒蔵
	
	// ObjectMapperを使用してbreweryListをJSON文字列に変換
	ObjectMapper mapper = new ObjectMapper();
	String breweryListJson = mapper.writeValueAsString(breweryList);
	%>
	
	<%-- Validation check --%>
	<% if( request.getAttribute("errorLog") != null){%>
		<div class="error-message"><%= request.getAttribute("errorLog") %></div>
	<% } %>

	<form action="BreweryUpdate" method="post" enctype="multipart/form-data">
		<p class="form-description">変更する酒蔵の詳細</p>
		
		<div class="form-group">
			<label for="breweryId">変更する酒蔵を選択:</label>
			<select name="breweryId" id="breweryId" required>
				<option value="" selected disabled>--酒蔵を選択--</option>
				<% for(BreweryBean brewery : breweryList){%>	
					<option value="<%= brewery.getBreweryId()%>" <%= (selectedBrewery != null && selectedBrewery.getBreweryId() == brewery.getBreweryId()) ? "selected" : "" %>><%= brewery.getBreweryName()%></option>
				<%} %>
			</select>
		</div>

		<div class="form-group">
			<label for="breweryName">酒蔵の名前：</label>
			<input type="text" id="breweryName" name="breweryName" value="<%= (selectedBrewery != null) ? selectedBrewery.getBreweryName() : "" %>" required>
		</div>
		
		<div class="form-group">
			<label for="breweryExplanation">酒蔵の説明：</label>
			<textarea id="breweryExplanation" name="breweryExplanation" rows="4" required><%= (selectedBrewery != null) ? selectedBrewery.getBreweryExplanation() : "" %></textarea>
		</div>
		
		<div class="form-group">
			<label for="latitude">酒蔵の緯度：</label>
			<input type="text" id="latitude" name="latitude" value="<%= (selectedBrewery != null) ? selectedBrewery.getLatitude() : "" %>" required>
		</div>
		
		<div class="form-group">
			<label for="longitude">酒蔵の経度：</label>
			<input type="text" id="longitude" name="longitude" value="<%= (selectedBrewery != null) ? selectedBrewery.getLongitude() : "" %>" required>
		</div>
		
		<div class="form-group">
			<label for="areaId">酒蔵の地域：</label>
			<select name="areaId" id="areaId" required>
				<option value="" selected disabled>--酒蔵の地域を選択--</option>
				<option value="1" <%= (selectedBrewery != null && selectedBrewery.getAreaId() == 1) ? "selected" : "" %>>上越</option>
				<option value="2" <%= (selectedBrewery != null && selectedBrewery.getAreaId() == 2) ? "selected" : "" %>>中越</option>
				<option value="3" <%= (selectedBrewery != null && selectedBrewery.getAreaId() == 3) ? "selected" : "" %>>下越</option>
				<option value="4" <%= (selectedBrewery != null && selectedBrewery.getAreaId() == 4) ? "selected" : "" %>>佐渡</option>
			</select>
		</div>
					
		<div class="form-group">
			<label for="address">酒蔵の住所：</label>
			<input type="text" id="address" name="address" value="<%= (selectedBrewery != null) ? selectedBrewery.getAddress() : "" %>" required>
		</div>
		
		<div class="form-group checkbox-group">
			<input type="checkbox" id="reservetionFlag" name="reservetionFlag" <%= (selectedBrewery != null && selectedBrewery.getReservationFlag()) ? "checked" : "" %>>
			<label for="reservetionFlag">予約の可否</label>
		</div>
		
		<div class="form-group">
			<label for="bImgPath">酒蔵の写真：</label>
			<input type="file" id="bImgPath" name="bImgPath" accept="image/jpeg, image/png" required>
            <% if (selectedBrewery != null && selectedBrewery.getbImgPath() != null && !selectedBrewery.getbImgPath().isEmpty()) { %>
                <p class="current-image-info">現在の画像: <span id="currentImageName"><%= selectedBrewery.getbImgPath() %></span></p>
                <img id="currentImagePreview" src="<%= request.getContextPath() %>/img/<%= selectedBrewery.getbImgPath() %>" alt="現在の酒蔵の画像" style="max-width: 100px; margin-top: 10px; border: 1px solid #ddd;">
            <% } else { %>
                <p class="current-image-info">現在の画像: <span id="currentImageName">なし</span></p>
                <img id="currentImagePreview" src="" alt="現在の酒蔵の画像プレビュー" style="max-width: 100px; margin-top: 10px; border: 1px solid #ddd; display: none;">
            <% } %>
		</div>
		
		<div class="form-group">
			<label for="reservetionPath">予約先URL：</label>
			<input type="text" id="reservetionPath" name="reservetionPath" value="<%= (selectedBrewery != null) ? selectedBrewery.getReservationPath() : "" %>">
		</div>
		
		<input type="submit" value="更新する" class="submit-button">
	</form>
	
	<form action="adminUpdate.jsp" method="POST">
		<input type="submit" value="戻る" class="back-button">
	</form>

</div>

<script>
// JSPから渡された酒蔵リストをJavaScript変数に格納
const allBreweries = <%= breweryListJson %>;

document.addEventListener('DOMContentLoaded', function() {
    const breweryIdSelect = document.getElementById('breweryId');
    const breweryNameInput = document.getElementById('breweryName');
    const breweryExplanationTextarea = document.getElementById('breweryExplanation');
    const latitudeInput = document.getElementById('latitude');
    const longitudeInput = document.getElementById('longitude');
    const areaIdSelect = document.getElementById('areaId');
    const addressInput = document.getElementById('address');
    const reservationFlagCheckbox = document.getElementById('reservetionFlag');
    const reservetionPathInput = document.getElementById('reservetionPath');
    const reservationPathGroup = reservetionPathInput.closest('.form-group');
    const currentImageNameSpan = document.getElementById('currentImageName');
    const currentImagePreviewImg = document.getElementById('currentImagePreview');
    const contextPath = "<%= request.getContextPath() %>";

    // 予約の可否チェックボックスによる表示切り替え関数 (既存)
    function toggleReservationPath() {
        if (reservationFlagCheckbox.checked) {
            reservationPathGroup.style.display = 'block';
            reservetionPathInput.setAttribute('required', 'required');
        } else {
            reservationPathGroup.style.display = 'none';
            reservetionPathInput.removeAttribute('required');
            reservetionPathInput.value = '';
        }
    }

    // 酒蔵ID選択時にフォームを更新する関数
    function updateFormFields(selectedId) {
        const selectedBreweryData = allBreweries.find(b => b.breweryId == selectedId);

        if (selectedBreweryData) {
            breweryNameInput.value = selectedBreweryData.breweryName || '';
            breweryExplanationTextarea.value = selectedBreweryData.breweryExplanation || '';
            latitudeInput.value = selectedBreweryData.latitude || '';
            longitudeInput.value = selectedBreweryData.longitude || '';
            addressInput.value = selectedBreweryData.address || '';
            
            // areaId のセレクトボックスを更新
            areaIdSelect.value = selectedBreweryData.areaId || '';

            // 予約の可否チェックボックスと予約先URLを更新
            reservationFlagCheckbox.checked = selectedBreweryData.reservationFlag;
            reservetionPathInput.value = selectedBreweryData.reservationPath || '';
            toggleReservationPath(); // 表示/非表示を更新

            // 画像情報とプレビューの更新
            if (selectedBreweryData.bImgPath) {
                currentImageNameSpan.textContent = selectedBreweryData.bImgPath;
                currentImagePreviewImg.src = contextPath + '/img/' + selectedBreweryData.bImgPath;
                currentImagePreviewImg.style.display = 'block';
            } else {
                currentImageNameSpan.textContent = 'なし';
                currentImagePreviewImg.src = '';
                currentImagePreviewImg.style.display = 'none';
            }

            // file input の required 属性を解除（変更しない場合は必須にしないため）
            document.getElementById('bImgPath').removeAttribute('required');
        } else {
            // IDが見つからない場合や、デフォルトオプション選択時はフォームをクリア
            breweryNameInput.value = '';
            breweryExplanationTextarea.value = '';
            latitudeInput.value = '';
            longitudeInput.value = '';
            areaIdSelect.value = '';
            addressInput.value = '';
            reservationFlagCheckbox.checked = false;
            reservetionPathInput.value = '';
            toggleReservationPath();
            currentImageNameSpan.textContent = 'なし';
            currentImagePreviewImg.src = '';
            currentImagePreviewImg.style.display = 'none';
            document.getElementById('bImgPath').setAttribute('required', 'required'); // file input を必須に戻す
        }
    }

    // ページロード時の初期設定
    toggleReservationPath(); // 予約パスの初期表示

    // selectedBreweryが存在する場合、そのIDでフォームを初期化
    const initialSelectedBreweryId = breweryIdSelect.value;
    if (initialSelectedBreweryId) {
        updateFormFields(initialSelectedBreweryId);
    } else if (allBreweries.length > 0) {
        // selectedBreweryがnullで、かつリストに酒蔵がある場合（例：初回アクセス時）
        // オプションリストの最初の有効なオプションを選択し、フォームを初期化
        // ただし、selectタグのdisabledオプションが最初に選ばれるようにする
        // updateFormFields(allBreweries[0].breweryId); // この行はコメントアウト
    }


    // セレクトボックスの変更イベントリスナー
    breweryIdSelect.addEventListener('change', function() {
        updateFormFields(this.value);
    });

    // ファイル入力フィールドが変更されたときにrequiredを解除
    document.getElementById('bImgPath').addEventListener('change', function() {
        if (this.value) {
            this.removeAttribute('required');
        } else {
            // ファイル選択がクリアされた場合、再度必須にする
            this.setAttribute('required', 'required');
        }
    });

    // フォーム送信時にbImgPathが空でもrequiredが適用されないように調整
    document.querySelector('form[action="BreweryUpdate"]').addEventListener('submit', function(event) {
        const fileInput = document.getElementById('bImgPath');
        if (fileInput.value === '' && currentImageNameSpan.textContent !== 'なし') {
            // 新しいファイルが選択されておらず、かつ既存の画像がある場合
            fileInput.removeAttribute('required');
        }
    });
});
</script>

</body>
</html>