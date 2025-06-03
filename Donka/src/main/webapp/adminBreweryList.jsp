<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.BreweryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者酒蔵一覧確認画面</title>
<link rel="stylesheet" type="text/css" href="admin.css">
<link rel="stylesheet" type="text/css" href="adminTable.css">
</head>
<body>

<jsp:include page="adminHeader.jsp" />

<div class="main-content">
	<h1>管理者 酒蔵一覧確認</h1>

	<div class="table-container">
		<table border="1">
			<caption>酒蔵一覧</caption>
			<thead>
				<tr>
					<th>酒蔵のID</th>
					<th>酒蔵の名前</th>
					<th>酒蔵の写真</th> <%-- 列名を「酒蔵の写真のパス」から「酒蔵の写真」に変更 --%>
					<th>酒蔵の緯度</th>
					<th>酒蔵の経度</th>
					<th>酒蔵の住所</th>
					<th>酒蔵の予約の有無</th>
					<th>酒蔵の予約サイトのURL</th>
					<th>酒蔵の説明</th>
					<th>酒蔵のエリア</th>
					<th>アクション</th>
				</tr>
			</thead>
			<tbody>
				<% List<BreweryBean> breweryList = (List<BreweryBean>)request.getAttribute("breweryList"); %>
				<% for(BreweryBean brewery : breweryList){ %>
					<tr>
						<td><%= brewery.getBreweryId()%></td>
						<td><%= brewery.getBreweryName()%></td>
						<td class="table-image"> <%-- 画像表示用のクラスを追加 --%>
                            <% if (brewery.getbImgPath() != null && !brewery.getbImgPath().isEmpty()) { %>
                                <img src="<%= brewery.getbImgPath() %>" alt="<%= brewery.getBreweryName() %>の画像">
                            <% } else { %>
                                画像なし
                            <% } %>
                        </td>
						<td><%= brewery.getLatitude()%></td>
						<td><%= brewery.getLongitude()%></td>
						<td><%= brewery.getAddress()%></td>
						<td><%  if (brewery.getReservationFlag()){%>有
						<%  }else { %>無<% }%></td>
						<td><%= brewery.getReservationPath()%></td>
						<td><%= brewery.getBreweryExplanation()%></td>
						<td><%  if(brewery.getAreaId() == 1){ %>上越
						<% }else if(brewery.getAreaId() == 2){ %>中越
						<% }else if(brewery.getAreaId() == 3){ %>下越
						<% }else if(brewery.getAreaId() == 4){ %>佐渡
						<% }%>
						</td>
						<td class="action-buttons">
                            <form action="BreweryUpdatePreparation" method="post" class="inline-form">
                                <input type="hidden" name="breweryId" value="<%= brewery.getBreweryId() %>">
                                <input type="submit" value="更新" class="action-button update-button">
                            </form>
                        </td>
					</tr>
				<% } %>
			</tbody>
		</table>
	</div>
	
	<form action="adminList.jsp" method="post"> 
		<input type="submit"  class="submit-button" value="管理者一覧確認画面に戻る">
	</form>
	
</div>
	
</body>
</html>