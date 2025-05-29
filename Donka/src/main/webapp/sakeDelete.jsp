<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.SakeBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒の画面の削除画面</title>
</head>
<body>
	<%
		List<SakeBean> sakeList = (List<SakeBean>) request.getAttribute("sakeList");
	%>
	<form action="SakeDeleteConfirm" method="post">
		<select name="sake">
			<%
			 for(SakeBean sake: sakeList ){
			%>
			<option value=<%=sake.getSakeName()%>><%=sake.getSakeName()%></option>
		<%
			 }
		%>
		</select>
		<input type="submit" value="削除">
	</form>
</body>
</html>