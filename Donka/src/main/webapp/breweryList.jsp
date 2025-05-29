<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.BreweryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒蔵一覧</title>
</head>
<body>
<%
List<BreweryBean> breweryList=(List<BreweryBean>)request.getAttribute("breweryList");
%>
<%
for(BreweryBean brewery:breweryList){
%>	
<%=brewery.getBreweryName() %>
<img src="<%=brewery.getBImgPath() %>"><br>

<%
}
%>




</body>
</html>