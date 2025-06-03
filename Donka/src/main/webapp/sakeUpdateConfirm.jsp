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
	
	変更する酒のID :	<jsp:getProperty name="sake" property="sakeId"/><br>
	変更する酒の名前：  <jsp:getProperty name="sake" property="sakeName"/><br>
	変更する酒の度数：  <jsp:getProperty name="sake" property="alc"/><br>
	変更する酒の飲み方：<jsp:getProperty name="sake" property="fDrink"/><br>
	変更する酒の味わい：<jsp:getProperty name="sake" property="taste"/><br>
	変更する酒の説明：  <jsp:getProperty name="sake" property="sakeExplanation"/><br>
	変更する酒の写真：  <jsp:getProperty name="sake" property="sImgPath"/><br>
	
	<form action="SakeUpdateConfirm" method="POST">
		<input type="hidden" name="sakeId2" value="<jsp:getProperty name="sake" property="sakeId"/>" >
		<input type="hidden" name="sakeName2" value="<jsp:getProperty name="sake" property="sakeName"/>" >
		<input type="hidden" name="alc2" value="<jsp:getProperty name="sake" property="alc"/>" >
		<input type="hidden" name="breweryId2" value="<jsp:getProperty name="sake" property="breweryId"/>" >
		<input type="hidden" name="fDrink2" value="<jsp:getProperty name="sake" property="fDrink"/>" >
		<input type="hidden" name="taste2" value="<jsp:getProperty name="sake" property="taste"/>" >
		<input type="hidden" name="sakeExplanation2" value="<jsp:getProperty name="sake" property="sakeExplanation"/>" >
		<input type="hidden" name="sImgPath2" value="<jsp:getProperty name="sake" property="sImgPath"/>" >
		<input type="submit" value="変更する">
	</form>
	
	<button onclick="history.back()">戻る</button>

</body>
</html>