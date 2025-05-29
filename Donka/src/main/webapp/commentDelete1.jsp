<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント削除画面1</title>
</head>
<body>


<form action="CommentSearch" method="POST">
	<%-- 酒蔵を選択するセレクトボックス --%>
	<%-- セレクトボックスで酒蔵の数を取得して表示する必要がある --%>
    酒蔵
    <select name="breweryId">
        <option value="1">1</option>
        <option value="2">2</option>
    </select><br>
    
    <input type="submit" value="検索する">
    
</form>


<form action="adminDelete.jsp" method="POST">
    <input type="submit" value="戻る">
</form>

<!-- または「戻る」ボタンはJavaScriptでブラウザの戻るを実装 -->
<!-- <input type="button" value="戻る" onclick="history.back();"> -->

</body>
</html>
