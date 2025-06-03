<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.SakeBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒削除確認画面</title> <%-- タイトルを修正しました --%>
<link rel="stylesheet" type="text/css" href="admin.css"> <%-- 共通CSSを読み込む --%>
<link rel="stylesheet" type="text/css" href="adminConfirm.css"> <%-- 確認画面用CSSを読み込む --%>
</head>
<body>

<jsp:include page="adminHeader.jsp" /> <%-- ヘッダーをインクルード --%>

<div class="main-content"> <%-- ここでコンテンツを囲みます --%>
	<h1>酒削除確認</h1>
	<p>こちらを削除してよろしいですか？</p>
	<%
		request.setCharacterEncoding("UTF-8");
	%>
	<%-- sakeListはSakeDeleteServletで設定され、このページではsessionから取得した"sake"を使っているため、sakeListは不要かもしれません --%>
	<%-- List<SakeBean> sakeList = (List<SakeBean>) request.getAttribute("sakeList"); --%>

	<div class="confirmation-details">
		<div class="detail-item"><span>酒の名前:</span><%= session.getAttribute("sake")%></div>
	</div>

	<form action="SakeDeleteComplete" method="POST" class="confirmation-form">
		<input type="submit" value="削除する" class="submit-button delete-button"> <%-- 削除ボタンにdelete-buttonクラスを追加 --%>
	</form>

	<button onclick="history.back()" class="back-button">戻る</button>

</div>

</body>
</html>