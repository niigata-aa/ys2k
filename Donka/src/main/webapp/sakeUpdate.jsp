<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,java.util.ArrayList,model.entity.BreweryBean,model.entity.SakeBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒更新画面</title>
</head>
<body>
	<%
	List<SakeBean> sakeList = (List<SakeBean>)request.getAttribute("sakeList");
	List<BreweryBean> breweryList = (List<BreweryBean>)request.getAttribute("breweryList");
	%>
	<form action="SakeUpdate" method="post" enctype="multipart/form-data">
		変更する酒の詳細<br>
		変更したい酒の名前を選択
		<select name="sakeId">
			
			<% for(SakeBean sake : sakeList){%>	
			<option value="<%= sake.getSakeId()%>"><%= sake.getSakeName()%>
		
			</option>
			<%} %>
		
		</select><br>
		
		変更する酒の名前:<input type="text" name="sakeName" required><br>
		
		変更する酒蔵を選択:
		<select name="breweryId">
			
			<% for(BreweryBean brewery : breweryList){%>	
				<option value="<%= brewery.getBreweryId()%>"><%= brewery.getBreweryName()%>
		
				</option>
			<%} %>
		
		</select><br>
		度数を選択:
		<select name="alc">
			<option>
			<% for(int i = 5; i < 22; i++){%>	
				<option value="<%= i%>"><%= i%></option>
			<%} %>
		</select><br>	
		飲み方を選択:
		<select name="fDrink">
			<option value="冷">冷</option>
			<option value="常温">常温</option>
			<option value="燗">燗</option>
		</select><br>	
		味わいを選択:
		<select name="taste">
			<option value="甘口">甘口</option>
			<option value="中口">中口</option>
			<option value="辛口">辛口</option>
		</select><br>
			
		変更する酒の説明:<textarea name="sakeExplanation" cols="30" rows="2" required></textarea><br>
		変更する酒の写真:<input type="file" name="sImgPath" accept="image/jpeg" required><br>
		
		<input type="submit" value="登録"> 
	</form>
</body>
</html>