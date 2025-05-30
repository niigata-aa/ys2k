<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップページ</title>
<link rel="stylesheet" href="topstyle.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	
<ul class="sakura">
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
</ul>
	<div>
	<h1>呑花臥酒</h1>

	<img src="img\topsakura.jpg" width="1000" height="600" alt="トップ画" >
</div>
<br>

<h2>「花に呑(の)み酒に臥（ふ）す」</h2><br>

<div class="ex">春の素晴らしさや美しさを味わって、酒に酔って心地よくなって横になること。<br>
日本酒王国「新潟」の酒を味わう。</div>

<h3>-酒蔵一覧を見る-</h3>

<div class="Bre">
<a href="BreweryView"><img src="img\sakagura.png" alt="酒蔵リンク" class="bre"></a><br>
<h3 class="brewery">BREWERY</h3><br>
</div>

<h3>-酒一覧を見る-</h3>
<div class="Sak">
<a href="SakeViewServlet"><img src="img\sake.png" alt="酒リンク" class="sa"></a>
<h3 class="sake">SAKE</h3>	
</div>
</body>
</html>