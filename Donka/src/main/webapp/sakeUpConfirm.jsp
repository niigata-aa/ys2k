<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


こちらを変更してよろしいですか？<br>
<jsp:useBean id="sake" scope="request" class="model.entity.SakeBean" />

変更する酒の名前：  <jsp:getProperty name="sake" property="sakeName"/><br>
変更する酒の度数：  <jsp:getProperty name="sake" property="alc"/><br>
変更する酒の飲み方：<jsp:getProperty name="sake" property="fDrink"/><br>
変更する酒の味わい：<jsp:getProperty name="sake" property="taste"/><br>
変更する酒の説明：  <jsp:getProperty name="sake" property="sakeExplanation"/><br>
変更する写真のURL：<jsp:getProperty name="sake" property="sImgPath"/><br>
<img src="/Donka/img/<jsp:getProperty name="sake" property="sImgPath"/>">

<button onclick="history.back()">戻る</button>

<form action="Complete" method="POST">
<input type="submit" value="登録する">
</form>

</body>
</html>