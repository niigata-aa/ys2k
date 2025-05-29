<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.BreweryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%	
	List<BreweryBean> breweryList = (List<BreweryBean>) request.getAttribute("breweryList");
			
	for (BreweryBean brewery : breweryList) {
	%>
	<%=brewery.getBreweryName() %>
	<%=brewery.getBImgPath() %><br><br>

	<%
		}
	%>


</body>
</html>