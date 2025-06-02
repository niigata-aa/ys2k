<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% if(session.getAttribute("user") == null){%>
<link rel="stylesheet" href="header.css">


<%--ログインしていないときの処理 --%>
<header class="header-5">
  <div class="header-inner">
    <div class="logo">
      <img src="img\logo.png">
    </div>
    <nav class="header-nav">
      <ul class="header-nav-list">
<a href="home.jsp"><li class="header-nav-item">呑花臥酒</li></a>
<a href="BreweryView"><li class="header-nav-item">一覧表示</li></a>
<a href="showRank.jsp"><li class="header-nav-item">ランキング</li></a>
<a href="login.jsp"><li class="header-nav-item">ログイン</li></a>
<a href="userRegistration.jsp"><li class="header-nav-item">登録</li></a>
    </ul>
   <div class="header-ham">
        <div class="menu2"></div>
        <div class="menu2"></div>
        <div class="menu2"></div>
      </div>
    </nav>
</div>
</header>

<%} else{%>
<%--ログインしているときの処理 --%>
<link rel="stylesheet" href="header.css">
<header class="header-5">
  <div class="header-inner">
    <div class="logo">
     <img src="img\logo.png">
    </div>
    <nav class="header-nav">
      <ul class="header-nav-list">

<a href="home.jsp"><li class="header-nav-item">呑花臥酒</li></a>
<a href="BreweryView"><li class="header-nav-item">一覧表示</li></a>
<a href="showRank.jsp"><li class="header-nav-item">ランキング</li></a>
<li class="header-nav-item"><form action="UserLogout" method="post">
	<input type="submit" style="border:none;background: transparent;font-family:"游明朝";" value="ログアウト">
</form>
</li>
</ul>
   <div class="header-ham">
        <div class="menu2"></div>
        <div class="menu2"></div>
        <div class="menu2"></div>
      </div>
    </nav>
</div>
</header>



<%} %>