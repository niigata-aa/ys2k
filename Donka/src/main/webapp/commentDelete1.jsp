<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.BreweryBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>コメント削除画面1</title>
<link rel="stylesheet" type="text/css" href="admin.css"> <%-- 共通CSSを読み込む --%>
<link rel="stylesheet" type="text/css" href="adminForm.css"> <%-- フォーム用CSSを読み込む --%>
</head>
<body>

<jsp:include page="adminHeader.jsp" /> <%-- ヘッダーをインクルード --%>

<div class="main-content"> <%-- ここでコンテンツを囲みます --%>
    <h1>コメント削除 - 酒蔵選択</h1>
    <form action="CommentSearch" method="POST">
        <div class="form-group">
            <label for="breweryId">酒蔵を選択:</label>
            <select name="breweryId" id="breweryId" required>
                <option value="" selected disabled>--酒蔵を選択--</option> <%-- デフォルトの選択肢を追加 --%>
                <%
                    List<BreweryBean> breweryList = (List<BreweryBean>) request.getAttribute("breweryList");
                    for (BreweryBean brewery : breweryList) {
                %>
                <option value="<%=brewery.getBreweryId() %>"><%=brewery.getBreweryName() %></option>
                <%
                    }
                %>
            </select>
        </div>
        
        <input type="submit" value="検索する" class="submit-button">
    </form>
    
    <button type="button" class="back-button" onClick="history.back()">戻る</button> <%-- buttonタグにtype="button"とクラスを追加 --%>

</div>

</body>
</html>