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

	<jsp:include page="header.jsp"></jsp:include>
	


	<div class="search">
		<div class="box-title"><h2>地域</h2></div>
		<form action="BrewerySearch" method="POST">
			<label>1.上越：<input type="checkbox" value="1" name="areaId"></label><br>
			<label>2.中越：<input type="checkbox" value="2" name="areaId"></label><br>
			<label>3.下越：<input type="checkbox" value="3" name="areaId"></label><br>
			<label>4.佐越：<input type="checkbox" value="4" name="areaId"></label><br>

			<input type="submit" value="検索">
		</form>
	</div>

	<div class="tab">

		<button onclick="location.href='BreweryView'">酒蔵</button>
		<button onclick="location.href='SakeViewServlet'">日本酒</button>

	</div>
	


	<%
	List<BreweryBean> breweryList = (List<BreweryBean>) request.getAttribute("breweryList");
	%>
	<div class="d1">
		<h1>酒蔵の一覧</h1>
	</div>
<!--	<p class="ex">画像をクリックすると詳細へ</p>-->
	<div class="table_box">
	
		


		<table>
<!--			<tr>-->
<!--				<th class="sticky">酒蔵の名前</th>-->
<!--				<th class="sticky">酒蔵の写真</th>-->
<!--			</tr>-->
			<%
			for (BreweryBean brewery : breweryList) {
			%>
			<tr>
				<td class="d2"><%=brewery.getBreweryName()%></td>
				<td>
					<form action="BreweryDetail" method="post">
						<input type="hidden" name="brewid"
							value=<%=brewery.getBreweryId()%>>
						<button style="background: transparent; border: none;">
							<img src="<%=brewery.getbImgPath()%>" class="image">
						<%--<img src="/Donka/img/<%=brewery.getbImgPath()%>">--%>
						</button>
					</form>
				</td>
			</tr>

			<%
}
%>

		</table>
	</div>
<div class="a"></div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>