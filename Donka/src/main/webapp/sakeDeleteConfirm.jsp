<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<<<<<<< master
こちらを削除してよろしいですか？<br>
<jsp:useBean id="sake" scope="session" class="model.entity.SakeBean" />

酒：<jsp:getProperty name="sake" property="sake"/><br>

<form action="SakeDelete" method="POST">
<input type="submit" value="戻る">
</form>
<form action="Complete" method="POST">
<input type="submit" value="削除する">
</form>

=======
>>>>>>> b58628d ９回目武田　jsp
</body>
</html>