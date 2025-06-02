<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,java.util.ArrayList,model.entity.BreweryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒蔵情報更新（仮）</title>
</head>
<body>

	<%
	List<BreweryBean> breweryList = (List<BreweryBean>)request.getAttribute("breweryList");
	%>
	
	<form action="BreweryUpdate" method="post">
		変更する酒蔵の詳細<br>
		
		変更する酒蔵を選択:
		<select name="breweryId">
			
			<% for(BreweryBean brewery : breweryList){%>	
				<option value="<%= brewery.getBreweryId()%>"><%= brewery.getBreweryName()%>
		
				</option>
			<%} %>
		
		</select><br>

		酒蔵の名前：<input type="text" id="breweryName" name="breweryName"><br>
		酒蔵の説明：<input type="text" id="breweryExplanation" name="breweryExplanation"><br>
		酒蔵の緯度：<input type="text" id="latitude" name="latitude"><br>
		酒蔵の経度：<input type="text" id="longitude" name="longitude"><br>
		
		酒蔵の地域：<select name="areaId" id="areaId">
						<option value="">--酒蔵の地域を選択--</option>
						<option value=1>上越</option>
						<option value=2>中越</option>
						<option value=3>下越</option>
						<option value=4>佐渡</option>
					</select><br>
					
		酒蔵の住所：<input type="text" id="address" name="address"><br>
		予約の可否：<input type="checkbox" id="reservetionFlag" name="reservetionFlag"/><br>
		
		
		酒蔵の写真：<input type="text" id="bImgPath" name="bImgPath" value="写真のURL"><br>
		予約先URL：<input type="text" id="reservetionPath" name="reservetionPath"><br>
		
		<input type="submit" value="登録する">
	</form>

	<form action="adminRegistration.jsp" method="POST">
		<input type="submit" value="戻る">
	</form>

</body>
</html>