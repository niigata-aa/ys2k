<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒一覧</title>
</head>
<body>
<%
List<SakeBean> sakeList=(List<SakeBean>)request.getAttribute("sakeList");
%>
<%
for(SakeBean brewery:sakeList){
%>	
<%=brewery.getBreweryName() %>
<img src="<%=brewery.getBImgPath() %>"><br>

<%
}
%>

</body>
</html>