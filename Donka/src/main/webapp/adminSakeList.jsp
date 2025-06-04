<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.List,model.entity.SakeBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者 酒一覧確認画面</title>
<link rel="stylesheet" type="text/css" href="admin.css">
<link rel="stylesheet" type="text/css" href="adminTable.css">
</head>
<body>

<jsp:include page="adminHeader.jsp" />

<div class="main-content">
    <h1>管理者 酒一覧確認</h1>

    <% List<SakeBean> sakeList = (List<SakeBean>)request.getAttribute("sakeList"); %>

    <div class="table-container">
        <table border="1">
            <caption>酒一覧</caption>
            <thead>
                <tr>
                    <th>酒のID</th>
                    <th>酒の名前</th>
                    <th>酒の写真</th> <%-- 列名を「酒の写真のパス」から「酒の写真」に変更 --%>
                    <th>酒の度数</th>
                    <th>飲み方</th>
                    <th>味わい</th>
                    <th>登録されている酒蔵のID</th>
                    <th>酒の説明</th>
                    <th>アクション</th>
                </tr>
            </thead>
            <tbody>
                <% for(SakeBean sake : sakeList){ %>
                    <tr>
                        <td><%= sake.getSakeId()%></td>
                        <td><%= sake.getSakeName()%></td>
                        <td class="table-image"> <%-- 画像表示用のクラスを追加 --%>
                            <% if (sake.getsImgPath() != null && !sake.getsImgPath().isEmpty()) { %>
                                <img src="/Donka/img/<%= sake.getsImgPath() %>" alt="<%= sake.getSakeName() %>の画像">
                            <% } else { %>
                                画像なし
                            <% } %>
                        </td>
                        <td><%= sake.getAlc()%></td>
                        <td><%= sake.getfDrink()%></td>
                        <td><%= sake.getTaste()%></td>
                        <td><%= sake.getBreweryId()%></td>
                        <td><%= sake.getSakeExplanation()%></td>
                        <td class="action-buttons">
                            <form action="SakeUpdatePreparation" method="post" class="inline-form">
                                <input type="hidden" name="sakeId" value="<%= sake.getSakeId() %>">
                                <input type="submit" value="更新" class="action-button update-button">
                            </form>
                            <form action="SakeDeleteConfirm" method="post" class="inline-form">
                                <input type="hidden" name="sake" value="<%= sake.getSakeName() %>">
                                <input type="submit" value="削除" class="action-button delete-button">
                            </form>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
                
    <form action="adminList.jsp" method="post"> 
        <input type="submit" class="submit-button" value="管理者一覧確認画面に戻る">
    </form>
		
</div>
	
</body>
</html>