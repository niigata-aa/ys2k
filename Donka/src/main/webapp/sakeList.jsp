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
<ul class="a1">

<%
for(SakeBean sake:sakeList){
%>	
<li class="d1"><%=sake.getSakeName() %></li>
<li class="d2"><a href="SakeDetail"><img src="<%=sake.getSImgPath()%>"class="image"></a></li>

<%
}
%>

</ul>
</div>


</body>
</html>