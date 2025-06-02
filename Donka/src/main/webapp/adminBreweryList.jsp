<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.BreweryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者酒蔵一覧確認画面</title>
</head>
<body>
	<% List<BreweryBean> breweryList = (List<BreweryBean>)request.getAttribute("breweryList"); %>

<body>
	<table border=1>
		<caption>酒一覧</caption>
		<tr><th>酒蔵のID</th><th>酒蔵の名前</th><th>酒蔵の写真のパス</th><th>酒蔵の緯度</th>
			<th>酒蔵の経度</th><th>酒蔵の住所</th><th>酒蔵の予約フラグ</th><th>酒蔵の予約サイトのURL</th>
			<th>酒蔵の説明</th><th>酒蔵のエリア</th><th>酒蔵の情報の最終更新日時</th></tr>
			
			<% for(BreweryBean brewery : breweryList){ %>
				<tr>
					<td><%= brewery.getBreweryId()%></td>
					<td><%= brewery.getBreweryName()%></td>
					<td><%= brewery.getbImgPath()%></td>
					<td><%= brewery.getLatitude()%></td>
					<td><%= brewery.getLongitude()%></td>
					<td><%= brewery.getAddress()%></td>
					<td><%= brewery.getReservationFlag()%></td>
					<td><%= brewery.getReservationPath()%></td>
					<td><%= brewery.getBreweryExplanation()%></td>
					<td><%  if(brewery.getAreaId() == 1){ %>上越
						<% }if(brewery.getAreaId() == 2){ %>中越
						<% }if(brewery.getAreaId() == 3){ %>下越
						<% }if(brewery.getAreaId() == 4){ %>佐渡
						<% }%>
					</td>
					<td><%= brewery.getUpdateDay()%></td>
					
				</tr>
			<% } %>
			
	</table>
	
	<form action="adminList.jsp" method="post"> 
		<input type="submit" value="管理者一覧確認画面に戻る">
	</form>
	
</body>
</html>
</body>
</html>