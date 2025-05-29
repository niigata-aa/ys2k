<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.entity.SakeBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
こちらを登録してよろしいですか？<br>
<jsp:useBean id="sake" scope="request" class="model.entity.SakeBean" />

登録する酒の名前：  <jsp:getProperty name="sake" property="sakeName"/><br>
登録する酒の度数：  <jsp:getProperty name="sake" property="alc"/><br>
登録する酒の飲み方：<jsp:getProperty name="sake" property="fDrink"/><br>
登録する酒の味わい：<jsp:getProperty name="sake" property="taste"/><br>
登録する酒の説明：  <jsp:getProperty name="sake" property="sakeExplanation"/><br>
登録する酒の写真：  <jsp:getProperty name="sake" property="sImgPath"/><br>

<form action="ああああああ" method="POST">
<input type="submit" value="戻る">
</form>
<form action="ああああああ" method="POST">
<input type="submit" value="登録する">
</form>

</body>
</html>