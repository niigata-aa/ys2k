<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.SakeBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>

<% List<SakeBean> sakeList = (List<SakeBean>)request.getAttribute("sakeList"); %>

<body>
	<table border=1>
		<caption>酒一覧</caption>
		<tr><th>酒のID</th><th>酒の名前</th><th>酒の写真のパス</th><th>酒の度数</th>
			<th>飲み方</th><th>味わい</th><th>登録されている酒蔵のID</th><th>更新日時</th>
			<th>酒の説明</th><th>登録されているエリア</th></tr>
			
			<% for(SakeBean sake : sakeList){ %>
				<tr>
					<td><%= sake.getSakeId()%></td>
					<td><%= sake.getSakeName()%></td>
					<td><%= sake.getsImgPath()%></td>
					<td><%= sake.getAlc()%></td>
					<td><%= sake.getfDrink()%></td>
					<td><%= sake.getTaste()%></td>
					<td><%= sake.getBreweryId()%></td>
					<td><%= sake.getSakeExplanation()%></td>
					<td><%= sake.getUpdateDay()%></td>
				</tr>
			<% } %>
			
	</table>
				
	<form action="adminList.jsp" method="post"> 
		<input type="submit" value="管理者一覧確認画面に戻る">
	</form>
		
</body>
</html>