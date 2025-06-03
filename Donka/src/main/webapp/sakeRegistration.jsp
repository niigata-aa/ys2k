<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,java.util.ArrayList,model.entity.BreweryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒登録画面</title>
<link rel="stylesheet" type="text/css" href="admin.css"> <%-- 共通CSSを読み込む --%>
<link rel="stylesheet" type="text/css" href="adminForm.css"> <%-- フォーム用CSSを読み込む --%>
</head>
<body>

<jsp:include page="adminHeader.jsp" /> <%-- ヘッダーをインクルード --%>

<div class="main-content"> <%-- ここでコンテンツを囲みます --%>
	<h1>酒の登録</h1>
	
	<%
	List<BreweryBean> breweryList=(List<BreweryBean>)request.getAttribute("breweryList");
	%>

	<form action="SakeRegist" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="sakeName">登録する酒の名前:</label>
			<input type="text" id="sakeName" name="sakeName" required>
		</div>
		
		<p class="form-description">登録する酒の詳細</p>
		
		<div class="form-group">
			<label for="breweryId">登録する酒蔵を選択:</label>
			<select name="breweryId" id="breweryId" required>
				<option value="" selected disabled>--酒蔵を選択--</option> <%-- 選択肢のデフォルト値を空に --%>
				<% for(BreweryBean brewery : breweryList){%>	
					<option value="<%= brewery.getBreweryId()%>"><%= brewery.getBreweryName()%></option>
				<%} %>
			</select>
		</div>
		
		<div class="form-group">
			<label for="alc">度数を選択:</label>
			<select name="alc" id="alc" required>
				<option value="" selected disabled>--度数を選択--</option> <%-- 選択肢のデフォルト値を空に --%>
				<% for(int i = 5; i < 22; i++){%>	
					<option value="<%= i%>"><%= i%>%</option> <%-- %を追加 --%>
				<%} %>
			</select>
		</div>	
		
		<div class="form-group">
			<label for="fDrink">飲み方を選択:</label>
			<select name="fDrink" id="fDrink" required>
				<option value="" selected disabled>--飲み方を選択--</option> <%-- 選択肢のデフォルト値を空に --%>
				<option value="冷">冷</option>
				<option value="常温">常温</option>
				<option value="燗">燗</option>
			</select>
		</div>	
		
		<div class="form-group">
			<label for="taste">味わいを選択:</label>
			<select name="taste" id="taste" required>
				<option value="" selected disabled>--味わいを選択--</option> <%-- 選択肢のデフォルト値を空に --%>
				<option value="甘口">甘口</option>
				<option value="中口">中口</option>
				<option value="辛口">辛口</option>
			</select>
		</div><br>
			
		<div class="form-group">
			<label for="sakeExplanation">登録する酒の説明:</label>
			<textarea name="sakeExplanation" id="sakeExplanation" cols="30" rows="4"></textarea> <%-- rowsを4に --%>
		</div>
		
		<div class="form-group">
			<label for="sImgPath">登録する酒の写真:</label>
			<input type="file" id="sImgPath" name="sImgPath" accept="image/jpeg, image/png" required> <%-- acceptにimage/pngを追加 --%>
		</div>
		
		<% if( request.getAttribute("errorLog") != null){%>
			<div class="error-message"><%= request.getAttribute("errorLog") %></div>
		<% } %>
		
		<input type="submit" value="登録" class="submit-button">
		
	</form>	
		
	<button onclick="history.back()" class="back-button">戻る</button> 
		
</div>
</body>
</html>