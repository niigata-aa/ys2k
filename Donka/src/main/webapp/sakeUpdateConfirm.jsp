<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.SakeBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒情報変更確認画面</title> <%-- タイトルを明確にしました --%>
<link rel="stylesheet" type="text/css" href="admin.css"> <%-- 共通CSSを読み込む --%>
<link rel="stylesheet" type="text/css" href="adminConfirm.css"> <%-- 確認画面用CSSを読み込む --%>
</head>
<body>

<jsp:include page="adminHeader.jsp" /> <%-- ヘッダーをインクルード --%>

<div class="main-content"> <%-- ここでコンテンツを囲みます --%>
	<h1>酒情報変更確認</h1>
	<p>こちらを変更してよろしいですか？</p>
	
	<jsp:useBean id="sake" scope="request" class="model.entity.SakeBean" />
	
	<div class="confirmation-details">
		<div class="detail-item"><span>変更する酒のID:</span><jsp:getProperty name="sake" property="sakeId"/></div>
		<div class="detail-item"><span>変更する酒の名前:</span><jsp:getProperty name="sake" property="sakeName"/></div>
		<div class="detail-item"><span>変更する酒の度数:</span><jsp:getProperty name="sake" property="alc"/>%</div> <%-- %を追加 --%>
		<div class="detail-item"><span>変更する酒蔵のID:</span><jsp:getProperty name="sake" property="breweryId"/></div> <%-- 酒蔵IDを追加 --%>
		<div class="detail-item"><span>変更する酒の飲み方:</span><jsp:getProperty name="sake" property="fDrink"/></div>
		<div class="detail-item"><span>変更する酒の味わい:</span><jsp:getProperty name="sake" property="taste"/></div>
		<div class="detail-item"><span>変更する酒の説明:</span><jsp:getProperty name="sake" property="sakeExplanation"/></div>
		<div class="detail-item"><span>変更する酒の写真:</span><jsp:getProperty name="sake" property="sImgPath"/></div>
		<div class="image-preview">
			<img src="<%= request.getContextPath() %>/img/<jsp:getProperty name="sake" property="sImgPath"/>" alt="酒の写真">
		</div>
	</div>
	
	<form action="SakeUpdateConfirm" method="POST" class="confirmation-form">
		<input type="hidden" name="sakeId2" value="<jsp:getProperty name="sake" property="sakeId"/>" >
		<input type="hidden" name="sakeName2" value="<jsp:getProperty name="sake" property="sakeName"/>" >
		<input type="hidden" name="alc2" value="<jsp:getProperty name="sake" property="alc"/>" >
		<input type="hidden" name="breweryId2" value="<jsp:getProperty name="sake" property="breweryId"/>" >
		<input type="hidden" name="fDrink2" value="<jsp:getProperty name="sake" property="fDrink"/>" >
		<input type="hidden" name="taste2" value="<jsp:getProperty name="sake" property="taste"/>" >
		<input type="hidden" name="sakeExplanation2" value="<jsp:getProperty name="sake" property="sakeExplanation"/>" >
		<input type="hidden" name="sImgPath2" value="<jsp:getProperty name="sake" property="sImgPath"/>" >
		<input type="submit" value="変更する" class="submit-button">
	</form>
	
	<button onclick="history.back()" class="back-button">戻る</button>

</body>
</html>