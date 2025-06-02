<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.List,model.entity.SakeBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日本酒ランキング</title>
<link rel="stylesheet" href="sakeTable.css"> <%-- 必要であれば酒一覧のCSSを流用 --%>
<style>
    /* ランキング表示用の追加スタイル */
    .ranking-container {
        width: 80%;
        margin: 80px auto 50px auto; /* ヘッダーとフッターの分を考慮 */
        text-align: center;
    }
    .ranking-table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
        background-color: #fff;
    }
    .ranking-table th, .ranking-table td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: left;
    }
    .ranking-table th {
        background-color: #f2f2f2;
        font-weight: bold;
    }
    .ranking-table tr:nth-child(even) {
        background-color: #f9f9f9;
    }
    .ranking-image {
        width: 80px; /* 画像のサイズ調整 */
        height: auto;
    }
    .rank-number {
        font-weight: bold;
        color: #e44d26; /* 目立つ色 */
        font-size: 1.2em;
    }
</style>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<div class="ranking-container">
    <h1>日本酒ランキング</h1>

    <%
        List<SakeBean> rankingList = (List<SakeBean>) request.getAttribute("rankingList");
    %>

    <table class="ranking-table">
        <thead>
            <tr>
                <th>順位</th>
                <th>酒名</th>
                <th>画像</th>
                <th>いいね数</th>
            </tr>
        </thead>
        <tbody>
            <%
                if (rankingList != null && !rankingList.isEmpty()) {
                    int rank = 1;
                    for (SakeBean sake : rankingList) {
            %>
                <tr>
                    <td class="rank-number"><%= rank %>位</td>
                    <td><%= sake.getSakeName() %></td>
                    <td><img src="/Donka/img/<%= sake.getsImgPath() %>" alt="<%= sake.getSakeName() %>" class="ranking-image"></td>
                    <td><%= sake.getVoteCount() %></td>
                </tr>
            <%
                        rank++;
                    }
                } else {
            %>
                <tr>
                    <td colspan="4">ランキングデータがありません。</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>
</div>

<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>