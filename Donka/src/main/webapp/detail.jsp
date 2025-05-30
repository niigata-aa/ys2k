<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.BreweryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>詳細ページ</title>
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"
	integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY="
	crossorigin="" />
<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"
	integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo="
	crossorigin=""></script>
</head>
<body>
	<%
	List<BreweryBean> breweryList = (List<BreweryBean>) request.getAttribute("brewList");
	%>
	<% for(BreweryBean brew :breweryList){ %>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	<%-- 酒の口コミだけ --%>
	<%-- この時は、酒蔵IDともしかしたらuser_id送るかも --%>
	<%-- 酒蔵IDとuser_idを値に参照したい --%>
	<form action="CommentList" method="POST">
		<input type="hidden" name="breweryId" value="1"> <input
			type="hidden" name="userId" value="fgh"> <input type="submit"
			value="酒の口コミ">
	</form>
	
	
	
	
	
	
	
	
	
	
	
	<!-- 地図だけ -->
	<div id='mapcontainer' style='width: 100%; height: 300px; z-index: 0;'></div>
	

	<script>
		function init_map() {
			const map = L.map('mapcontainer');
			map.setView([<%=brew.getLatitude()%>,<%=brew.getLongitude()%>], 18);

			//マップタイルを読み込み、引用元を記載する
			L
					.tileLayer(
							'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
							{
								attribution : "<a href='http://osm.org/copyright'> ©OpenStreetMap </a>"
							}).addTo(map);

			//マーカーの配置
			L.marker([<%=brew.getLatitude()%>,<%=brew.getLongitude()%>]).on(
					'contextmenu',
					function(e) {
						window.open("https://www.google.co.jp/maps/place/"
								+ e.latlng.lat + "," + e.latlng.lng + "/@"
								+ e.latlng.lat + "," + e.latlng.lng
								+ ",15z/data");
					}).addTo(map);
		}
			
		//ダウンロード時に初期化する
		window.addEventListener('DOMContentLoaded', init_map());
	</script>
	<%
	}
	%>
</body>
</html>