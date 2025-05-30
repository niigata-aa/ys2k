<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.SakeBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒一覧</title>
<link rel="stylesheet" href="sakeTable.css">
</head>
<body>
<%
List<SakeBean> sakeList=(List<SakeBean>)request.getAttribute("sakeList");
%>
<div class="d1"><h1> 酒の一覧</h1></div>
<div class="table_box">
<table >
<tr>
<th class="sticky">酒の名前</th><th class="sticky">酒の写真</th>
</tr>	
<%
for(SakeBean sake:sakeList){
%>	
<tr><td class="d2"><%=sake.getSakeName() %></td>
<td><a href="SakeDetail"><img src="<%=sake.getsImgPath()%>"class="image"></a></td>

<%
}
%>

</table>
</div>


</body>
</html>