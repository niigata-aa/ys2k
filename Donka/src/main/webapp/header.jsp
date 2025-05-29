<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% if(session.getAttribute("user") == null){%>

<%--ログインしていないときの処理 --%>
<a href="home.jsp">呑花臥酒</a>
<a href="breweryList.jsp">一覧表示</a>
<a href="showRank.jsp">ランキング</a>
<a href="login.jsp">ログイン</a>
<a href="userRegistration.jsp">登録</a>

<%} else{%>

<%--ログインしているときの処理 --%>
<a href="home.jsp">呑花臥酒</a>
<a href="breweryList.jsp">一覧表示</a>
<a href="showRank.jsp">ランキング</a>
<input type="submit" value="ログアウト">

<%} %>