<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,java.util.ArrayList,model.entity.BreweryBean,model.entity.SakeBean,com.fasterxml.jackson.databind.ObjectMapper"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒更新画面</title>
<link rel="stylesheet" type="text/css" href="admin.css">
<link rel="stylesheet" type="text/css" href="adminForm.css">
</head>
<body>

<jsp:include page="adminHeader.jsp" />

<div class="main-content">
	<h1>酒情報更新</h1>
	
	<%
	List<SakeBean> sakeList = (List<SakeBean>)request.getAttribute("sakeList");
	List<BreweryBean> breweryList = (List<BreweryBean>)request.getAttribute("breweryList");
	SakeBean selectedSakeOnLoad = (SakeBean)request.getAttribute("selectedSake"); // ページロード時に選択される酒
	
	// ObjectMapperを使用してsakeListをJSON文字列に変換
	ObjectMapper mapper = new ObjectMapper();
	String sakeListJson = mapper.writeValueAsString(sakeList);
	%>
	
	<%-- Validation check --%>
	<% if( request.getAttribute("errorLog") != null){%>
		<div class="error-message"><%= request.getAttribute("errorLog") %></div>
	<% } %>

	<form action="SakeUpdate" method="post" enctype="multipart/form-data">
		<p class="form-description">変更する酒の詳細</p>
		
		<div class="form-group">
			<label for="sakeId">変更したい酒の名前を選択:</label>
			<select name="sakeId" id="sakeId" required>
				<option value="" selected disabled>--酒を選択--</option>
				<% for(SakeBean sake : sakeList){%>	
					<option value="<%= sake.getSakeId()%>" <%= (selectedSakeOnLoad != null && selectedSakeOnLoad.getSakeId() == sake.getSakeId()) ? "selected" : "" %>><%= sake.getSakeName()%></option>
				<%} %>
			</select>
		</div>
		
		<div class="form-group">
			<label for="sakeName">変更する酒の名前:</label>
			<input type="text" id="sakeName" name="sakeName" value="<%= (selectedSakeOnLoad != null) ? selectedSakeOnLoad.getSakeName() : "" %>" required>
		</div>
		
		<div class="form-group">
			<label for="breweryId">変更する酒蔵を選択:</label>
			<select name="breweryId" id="breweryId" required>
				<option value="" selected disabled>--酒蔵を選択--</option>
				<% for(BreweryBean brewery : breweryList){%>	
					<option value="<%= brewery.getBreweryId()%>" <%= (selectedSakeOnLoad != null && selectedSakeOnLoad.getBreweryId() == brewery.getBreweryId()) ? "selected" : "" %>><%= brewery.getBreweryName()%></option>
				<%} %>
			</select>
		</div>
		
		<div class="form-group">
			<label for="alc">度数を選択:</label>
			<select name="alc" id="alc" required>
				<option value="" selected disabled>--度数を選択--</option>
				<% for(int i = 5; i < 22; i++){%>	
					<option value="<%= i%>" <%= (selectedSakeOnLoad != null && (int)selectedSakeOnLoad.getAlc() == i) ? "selected" : "" %>><%= i%>%</option>
				<%} %>
			</select>
		</div>	
		
		<div class="form-group">
			<label for="fDrink">飲み方を選択:</label>
			<select name="fDrink" id="fDrink" required>
				<option value="" selected disabled>--飲み方を選択--</option>
				<option value="冷" <%= (selectedSakeOnLoad != null && "冷".equals(selectedSakeOnLoad.getfDrink())) ? "selected" : "" %>>冷</option>
				<option value="常温" <%= (selectedSakeOnLoad != null && "常温".equals(selectedSakeOnLoad.getfDrink())) ? "selected" : "" %>>常温</option>
				<option value="燗" <%= (selectedSakeOnLoad != null && "燗".equals(selectedSakeOnLoad.getfDrink())) ? "selected" : "" %>>燗</option>
			</select>
		</div>	
		
		<div class="form-group">
			<label for="taste">味わいを選択:</label>
			<select name="taste" id="taste" required>
				<option value="" selected disabled>--味わいを選択--</option>
				<option value="甘口" <%= (selectedSakeOnLoad != null && "甘口".equals(selectedSakeOnLoad.getTaste())) ? "selected" : "" %>>甘口</option>
				<option value="中口" <%= (selectedSakeOnLoad != null && "中口".equals(selectedSakeOnLoad.getTaste())) ? "selected" : "" %>>中口</option>
				<option value="辛口" <%= (selectedSakeOnLoad != null && "辛口".equals(selectedSakeOnLoad.getTaste())) ? "selected" : "" %>>辛口</option>
			</select>
		</div>
			
		<div class="form-group">
			<label for="sakeExplanation">変更する酒の説明:</label>
			<textarea name="sakeExplanation" id="sakeExplanation" cols="30" rows="4"><%= (selectedSakeOnLoad != null) ? selectedSakeOnLoad.getSakeExplanation() : "" %></textarea>
		</div>
		
		<div class="form-group">
			<label for="sImgPath">変更する酒の写真:</label>
			<input type="file" id="sImgPath" name="sImgPath" accept="image/jpeg, image/png"> <%-- required を削除 --%>
            <% if (selectedSakeOnLoad != null && selectedSakeOnLoad.getsImgPath() != null && !selectedSakeOnLoad.getsImgPath().isEmpty()) { %>
                <p class="current-image-info">現在の画像: <span id="currentImageName"><%= selectedSakeOnLoad.getsImgPath() %></span></p>
                <img id="currentImagePreview" src="<%= request.getContextPath() %>/img/<%= selectedSakeOnLoad.getsImgPath() %>" alt="現在の酒の画像" style="max-width: 100px; margin-top: 10px; border: 1px solid #ddd;">
            <% } else { %>
                <p class="current-image-info">現在の画像: <span id="currentImageName">なし</span></p>
                <img id="currentImagePreview" src="" alt="現在の酒の画像プレビュー" style="max-width: 100px; margin-top: 10px; border: 1px solid #ddd; display: none;">
            <% } %>
		</div>
		
		<input type="submit" value="更新する" class="submit-button">
		
	</form>	
		
	<button onclick="history.back()" class="back-button">戻る</button>
		
</div>

<script>
// JSPから渡された酒リストをJavaScript変数に格納
const allSakes = <%= sakeListJson %>;

document.addEventListener('DOMContentLoaded', function() {
    const sakeIdSelect = document.getElementById('sakeId');
    const sakeNameInput = document.getElementById('sakeName');
    const breweryIdSelect = document.getElementById('breweryId');
    const alcSelect = document.getElementById('alc');
    const fDrinkSelect = document.getElementById('fDrink');
    const tasteSelect = document.getElementById('taste');
    const sakeExplanationTextarea = document.getElementById('sakeExplanation');
    const sImgPathInput = document.getElementById('sImgPath'); // file input
    const currentImageNameSpan = document.getElementById('currentImageName');
    const currentImagePreviewImg = document.getElementById('currentImagePreview');
    const contextPath = "<%= request.getContextPath() %>";

    // フォームフィールドを更新する関数
    function updateFormFields(selectedId) {
        const selectedSakeData = allSakes.find(s => s.sakeId == selectedId);

        if (selectedSakeData) {
            sakeNameInput.value = selectedSakeData.sakeName || '';
            breweryIdSelect.value = selectedSakeData.breweryId || '';
            alcSelect.value = selectedSakeData.alc || '';
            fDrinkSelect.value = selectedSakeData.fDrink || '';
            tasteSelect.value = selectedSakeData.taste || '';
            sakeExplanationTextarea.value = selectedSakeData.sakeExplanation || '';

            // 画像情報とプレビューの更新
            if (selectedSakeData.sImgPath) {
                currentImageNameSpan.textContent = selectedSakeData.sImgPath;
                currentImagePreviewImg.src = contextPath + '/img/' + selectedSakeData.sImgPath;
                currentImagePreviewImg.style.display = 'block';
            } else {
                currentImageNameSpan.textContent = 'なし';
                currentImagePreviewImg.src = '';
                currentImagePreviewImg.style.display = 'none';
            }

            // file input の required 属性を解除（変更しない場合は必須にしないため）
            sImgPathInput.removeAttribute('required');
        } else {
            // IDが見つからない場合や、デフォルトオプション選択時はフォームをクリア
            sakeNameInput.value = '';
            breweryIdSelect.value = '';
            alcSelect.value = '';
            fDrinkSelect.value = '';
            tasteSelect.value = '';
            sakeExplanationTextarea.value = '';
            currentImageNameSpan.textContent = 'なし';
            currentImagePreviewImg.src = '';
            currentImagePreviewImg.style.display = 'none';
            sImgPathInput.setAttribute('required', 'required'); // file input を必須に戻す
        }
    }

    // ページロード時の初期設定
    // URLパラメータでsakeIdが渡されてselectedSakeOnLoadがある場合、それを基に初期化
    // そうでない場合は、セレクトボックスの現在の選択（通常はdisabledな初期オプション）を基に初期化
    const initialSelectedSakeId = sakeIdSelect.value;
    if (initialSelectedSakeId) {
        updateFormFields(initialSelectedSakeId);
    } else {
        // ページロード時に有効なsakeIdがselectedになっていない場合、フォームをクリア状態にする
        updateFormFields(null); // nullを渡してフォームをクリアする
    }


    // セレクトボックスの変更イベントリスナー
    sakeIdSelect.addEventListener('change', function() {
        updateFormFields(this.value);
    });

    // ファイル入力フィールドが変更されたときにrequiredを解除
    sImgPathInput.addEventListener('change', function() {
        if (this.value) { // 新しいファイルが選択された場合
            this.removeAttribute('required');
        } else { // ファイル選択がクリアされた場合
            // 既存の画像がある場合は必須に戻さない
            if (currentImageNameSpan.textContent === 'なし') {
                 this.setAttribute('required', 'required');
            }
        }
    });

    // フォーム送信時にsImgPathが空でもrequiredが適用されないように調整
    document.querySelector('form[action="SakeUpdate"]').addEventListener('submit', function(event) {
        if (sImgPathInput.value === '' && currentImageNameSpan.textContent !== 'なし') {
            // 新しいファイルが選択されておらず、かつ既存の画像がある場合
            sImgPathInput.removeAttribute('required');
        } else if (sImgPathInput.value === '' && currentImageNameSpan.textContent === 'なし') {
            // 新しいファイルが選択されておらず、既存の画像もない場合
            sImgPathInput.setAttribute('required', 'required');
        }
    });
});
</script>

</body>
</html>