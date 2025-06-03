<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.BreweryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒蔵変更確認画面</title>
<link rel="stylesheet" type="text/css" href="admin.css"> <%-- 共通CSSを読み込む --%>
<link rel="stylesheet" type="text/css" href="adminConfirm.css"> <%-- 確認画面用CSSを読み込む --%>
</head>
<body>

<jsp:include page="adminHeader.jsp" /> <%-- ヘッダーをインクルード（WEB-INF以下に配置されている場合） --%>

<div class="main-content"> <%-- ここでコンテンツを囲みます --%>
	<h1>酒蔵変更確認</h1>
	<p>こちらに更新してよろしいですか？</p>
	<jsp:useBean id="brewery" scope="request" class="model.entity.BreweryBean" />
	
	<div class="confirmation-details">
		<div class="detail-item"><span>酒蔵の名前:</span><jsp:getProperty name="brewery" property="breweryName"/></div>
		<div class="detail-item"><span>酒蔵の説明:</span><jsp:getProperty name="brewery" property="breweryExplanation"/></div>
		<div class="detail-item"><span>酒蔵の緯度:</span><jsp:getProperty name="brewery" property="latitude"/></div>
		<div class="detail-item"><span>経度:</span><jsp:getProperty name="brewery" property="longitude"/></div>
		<div class="detail-item"><span>酒蔵の住所:</span><jsp:getProperty name="brewery" property="address"/></div>
		<div class="detail-item"><span>酒蔵の地域:</span><%-- エリアIDから地域名に変換 --%>
			<% int areaId = brewery.getAreaId();
			   String areaName = "";
			   if(areaId == 1) areaName = "上越";
			   else if(areaId == 2) areaName = "中越";
			   else if(areaId == 3) areaName = "下越";
			   else if(areaId == 4) areaName = "佐渡";
			%>
			<%= areaName %>
		</div>

		<div class="detail-item"><span>酒蔵の予約の可否:</span>
			<% if (brewery.getReservationFlag()){%>有<% }else { %>無<% }%>
		</div>
		<div class="detail-item"><span>酒蔵の予約先:</span><jsp:getProperty name="brewery" property="reservationPath"/></div>
		
		<div class="detail-item"><span>写真のパス:</span><jsp:getProperty name="brewery" property="bImgPath"/></div>
		<div class="image-preview">
			<img src="<%= request.getContextPath() %>/img/<jsp:getProperty name="brewery" property="bImgPath"/>" alt="酒蔵の写真">
		</div>
	</div>
		
	<form action="BreweryUpdateConfirm" method="POST" class="confirmation-form">
		<input type ="hidden" name ="breweryId2" value="<jsp:getProperty name="brewery" property="breweryId"/>">
		<input type ="hidden" name ="breweryName2" value="<jsp:getProperty name="brewery" property="breweryName"/>">
		<input type ="hidden" name ="breweryExplanation2" value="<jsp:getProperty name="brewery" property="breweryExplanation"/>">
		<input type ="hidden" name ="latitude2" value="<jsp:getProperty name="brewery" property="latitude"/>">
		<input type ="hidden" name ="longitude2" value="<jsp:getProperty name="brewery" property="longitude"/>">
		<input type ="hidden" name ="address2" value="<jsp:getProperty name="brewery" property="address"/>">
		<input type ="hidden" name ="areaId2" value="<jsp:getProperty name="brewery" property="areaId"/>">
		<input type ="hidden" name ="reservationFlag2" value="<jsp:getProperty name="brewery" property="reservationFlag"/>">
		<input type ="hidden" name ="reservationPath2" value="<jsp:getProperty name="brewery" property="reservationPath"/>">
		<input type ="hidden" name ="bImgPath2" value="<jsp:getProperty name="brewery" property="bImgPath"/>">
		<input type="submit" value="更新する" class="submit-button">
	</form>

	<button onclick="history.back()" class="back-button">戻る</button>

</body>
</html>