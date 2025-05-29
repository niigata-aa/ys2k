<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,java.util.ArrayList,model.entity.BreweryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒登録画面</title>
</head>
<body>
	<%
	List<BreweryBean> breweryList=(List<BreweryBean>)request.getAttribute("breweryList");
	%>
	<form action="SakeRegist" method="post">
		登録する酒の名前:<input type="text" name="sakeName"><br>
		登録する酒の詳細<br>
		酒蔵選択:
		<select name="breweryId">
			
			<% for(BreweryBean brewery : breweryList){%>	
				<option value="<%= brewery.getBreweryId()%>"><%= brewery.getBreweryName()%>
		
				</option>
			<%} %>
		
		</select><br>
		度数:
		<select name="alc">
			<option>
			<% for(int i = 5; i < 22; i++){%>	
				<option value="<%= i%>"><%= i%></option>
			<%} %>
		</select><br>	
		飲み方:
		<select name="fDrink">
			<option value="冷">冷</option>
			<option value="常温">常温</option>
			<option value="燗">燗</option>
		</select><br>	
		味わい:
		<select name="taste">
			<option value="甘口">甘口</option>
			<option value="中口">中口</option>
			<option value="辛口">辛口</option>
		</select><br>
			
		登録する酒の説明:<textarea name="sakeExplanation" cols="30" rows="2"></textarea><br>
		登録する酒の写真URL:<input type="text" name="sImgPath"><br>
		
		<input type="submit" value="登録"> 
	</form>
</body>
</html>