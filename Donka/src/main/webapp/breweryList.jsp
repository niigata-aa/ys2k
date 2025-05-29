<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.BreweryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒蔵一覧</title>
<link rel="stylesheet" href="brewerytable.css">
</head>
<body>
<%
List<BreweryBean> breweryList=(List<BreweryBean>)request.getAttribute("breweryList");
%>
<div class="d1"><h1> 酒蔵の一覧</h1></div>
<h3>画像をクリックすると詳細へ</h3>
<div class="table_box">
<table >
<tr>
<!--<th class="sticky">酒蔵の名前</th><th class="sticky">酒蔵の写真</th>-->
</tr>
<%
for(BreweryBean brewery:breweryList){
%>	
<tr><td class="d2"><%=brewery.getBreweryName() %></td>
<td><a href="BreweryDetail"><img src="<%=brewery.getBImgPath()%>"class="image"></a></td></tr>

<%
}
%>

</table>
</div>

</body>
</html>