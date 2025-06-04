<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,model.entity.BreweryBean,model.entity.SakeBean,model.entity.UserBean,model.entity.CommentBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>詳細ページ</title>
<link rel="stylesheet"
	href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css"
	integrity="sha256-p4NxAoJBhIIN+hmNHrzRCf9tD/miZyoHS5obTRR9BMY="
	crossorigin="" />
<link rel="stylesheet" href="style.css">
<script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"
	integrity="sha256-20nQCchB9co0qIjJZRGuk2/Z9VM+kNiyxNV1lvTlZBo="
	crossorigin=""></script>
<script src="script.js"></script>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>
	<%
	List<BreweryBean> breweryList = (List<BreweryBean>) request.getAttribute("brewList");
	List<SakeBean> sakeList = (List<SakeBean>) request.getAttribute("sakeList");
	%>
	<%
	for (BreweryBean brew : breweryList) {
	%>

	<p class="name">
	<h1><%=brew.getBreweryName()%></h1>
	</p>
	<img src="/Donka/img/<%=brew.getbImgPath()%>" alt="<%=brew.getBreweryName()%>">
	<br>

	<p class="ex"><%=brew.getBreweryExplanation()%></p>

	<div class="accordion-item">
    <div class="accordion-header">
        <h3>日本酒</h3>
        <button class="toggle-button">+</button>
    </div>
    <%-- ここにテーブルスクロール機能を追加するために table-container クラスを適用 --%>
    <div class="accordion-content table-container">
        <table class="sake-table"> <%-- sake-table クラスを適用 --%>
            <thead>
                <tr>
                    <th>画像</th>
                    <th>名前</th>
                    <th>度数</th>
                    <th>おすすめの飲み方</th>
                    <th>味わい</th>
                    <th>説明</th> <%-- SakeBeanにsakeExplanationがあるため、説明カラムを追加します --%>
                </tr>
            </thead>
            <tbody>
                <%
                if (sakeList != null && !sakeList.isEmpty()) { // sakeListがnullまたは空でない場合のみループ
                    for (SakeBean sake : sakeList) {
                %>
                <tr>
                    <td><img src="img/<%=sake.getsImgPath()%>" alt="<%=sake.getSakeName()%>" class="sake-image-small"></td>
                    <td><%=sake.getSakeName()%></td>
                    <td><%=sake.getAlc()%></td>
                    <td><%=sake.getfDrink()%></td>
                    <td><%=sake.getTaste()%></td>
                    <td><%=sake.getSakeExplanation()%></td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="6">この酒蔵にはまだ酒が登録されていません。</td> <%-- colspan を6に変更 --%>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
    </div>
</div>



	<div class="accordion-item">
		<div class="accordion-header">
			<h3>コメント</h3>
			<button class="toggle-button">+</button>
		</div>
		<div class="accordion-content">
			<%-- コメント一覧表示--%>
			<%
			UserBean user = (UserBean) session.getAttribute("user");
			String userId = (user != null) ? user.getUserId() : null;
			%>
			<%
			List<CommentBean> commentList = (List<CommentBean>) request.getAttribute("commentList");
			List<UserBean> userList = (List<UserBean>) request.getAttribute("userList");
			int count = 0;
			%>
			<%
			for (CommentBean comment : commentList) {
			%>
			ユーザーのニックネーム：
			<%=userList.get(count).getNickname()%><br> コメント：
			<%=comment.getContent()%><br> <br>
			<%
			count++;
			%>
			<%
			}
			%>

			<form action="CommentList" method="POST">
				<input type="hidden" name="breweryId"
					value=<%=breweryList.get(0).getBreweryId()%>> <input
					type="hidden" name="userId"
					value="<%=(user != null) ? user.getUserId() : ""%>"> <input
					type="submit" value="酒の口コミ" <%=(user == null) ? "disabled" : ""%>>
			</form>

		</div>
	</div>






	<div class="accordion-item">
		<div class="accordion-header">
			<h3>地図</h3>
			<button class="toggle-button">+</button>
		</div>
		<div class="accordion-content">

			<!-- 地図だけ -->
			<div id='mapcontainer'
				style='width: 100%; height: 300px; z-index: 0;'></div>
		</div>
	</div>


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
			// ポップアップ表示
			 L.marker([<%=brew.getLatitude()%>,<%=brew.getLongitude()%>]).addTo(map)
			var marker = L.marker([<%=brew.getLatitude()%>,<%=brew.getLongitude()%>]).addTo(map);
			    //上のマーカーにポップアップを追加する。
			marker.bindPopup("<%=brew.getBreweryName()%></br><%=brew.getAddress()%>").openPopup();
		}

		//ダウンロード時に初期化する
		window.addEventListener('DOMContentLoaded', init_map());
	</script>
	<%
	}
	%>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>