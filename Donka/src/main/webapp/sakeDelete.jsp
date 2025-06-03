<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.SakeBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒の削除画面</title> <%-- タイトルを修正 --%>
<link rel="stylesheet" type="text/css" href="admin.css"> <%-- 共通CSSを読み込む --%>
<link rel="stylesheet" type="text/css" href="adminForm.css"> <%-- フォーム用CSSを読み込む --%>
</head>
<body>

<jsp:include page="adminHeader.jsp" /> <%-- ヘッダーをインクルード --%>

<div class="main-content"> <%-- ここでコンテンツを囲みます --%>
	<h1>酒の削除</h1>
	
	<%
		List<SakeBean> sakeList = (List<SakeBean>) request.getAttribute("sakeList");
	%>
	<form action="SakeDeleteConfirm" method="post">
		<div class="form-group">
			<label for="sake">削除する酒を選択:</label>
			<select name="sake" id="sake" required>
				<option value="" selected disabled>--酒を選択--</option> <%-- デフォルトの選択肢を追加 --%>
				<%
				 for(SakeBean sake: sakeList ){
				%>
				<option value="<%=sake.getSakeName()%>"><%=sake.getSakeName()%></option>
			<%
				 }
			%>
			</select>
		</div>
		<input type="submit" value="削除" class="submit-button">
	</form>
		<button onclick="history.back()" class="back-button">戻る</button>
</div>
</body>
</html>