<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.List,model.entity.SakeBean,java.util.Set"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒一覧</title>
<link rel="stylesheet" href="sakeTable.css">
</head>
<body>
	<%
List<SakeBean> sakeList=(List<SakeBean>)request.getAttribute("sakeList");
%>
	<form action="SakeSearch" method="post">
		<h2>検索条件</h2>
		<p>
			<label for="searchSakeName">酒の名前:</label> <input type="text"
				name="searchSakeName" id="searchSakeName"
				value="<%= request.getAttribute("searchSakeName") != null ? (String)request.getAttribute("searchSakeName") : "" %>">
		</p>

		<p>
			度数:
			<%
            // 選択されたアルコール度数範囲のセットを取得 (再表示用)
            Set<String> selectedAlcRanges = (Set<String>) request.getAttribute("selectedAlcRanges");
            if (selectedAlcRanges == null) selectedAlcRanges = new java.util.HashSet<>(); // nullの場合は空のセットで初期化

            String[] alcRanges = {"~12", "13-15", "16~"}; // 定義済みの度数範囲
            for (String range : alcRanges) {
                // チェックボックスのIDをユニークにするため、文字列を置き換え
                String checkboxId = "alcRange_" + range.replace("~", "_").replace("-", "_");
            %>
			<input type="checkbox" name="alcRange" value="<%= range %>"
				id="<%= checkboxId %>"
				<%= selectedAlcRanges.contains(range) ? "checked" : "" %>> <label
				for="<%= checkboxId %>"><%= range %>%</label>
			<%
            }
            %>
		</p>

		<p>
			<label for="tasteSelect">味わい:</label> <select name="taste"
				id="tasteSelect">
				<option value="all">-- 全て --</option>
				<%
                String[] tastes = {"辛口", "甘口", "中口"}; // 味わいの例 (実際にはDBから取得するなど)
                String selectedTaste = (String) request.getAttribute("selectedTaste");
                if (selectedTaste == null) selectedTaste = ""; // nullの場合は空文字列で初期化

                for (String taste : tastes) {
                %>
				<option value="<%= taste %>"
					<%= taste.equals(selectedTaste) ? "selected" : "" %>><%= taste %></option>
				<%
                }
                %>
			</select>
		</p>

		<p>
			<label for="fDrinkSelect">飲み方:</label> <select name="fDrink"
				id="fDrinkSelect">
				<option value="all">-- 全て --</option>
				<%
                String[] fDrinks = {"冷", "燗", "常"}; // 飲み方の例 (実際にはDBから取得するなど)
                String selectedFDrink = (String) request.getAttribute("selectedFDrink");
                if (selectedFDrink == null) selectedFDrink = ""; // nullの場合は空文字列で初期化

                for (String fDrink : fDrinks) {
                %>
				<option value="<%= fDrink %>"
					<%= fDrink.equals(selectedFDrink) ? "selected" : "" %>><%= fDrink %></option>
				<%
                }
                %>
			</select>
		</p>
		<input type="submit" value="検索">
	</form>
	<div class="d1">
		<h1>酒の一覧</h1>
	</div>
	<div class="table_box">
		<table>
			<tr>
				<th class="sticky">酒の名前</th>
				<th class="sticky">酒の写真</th>
			</tr>
			<%
        // サーブレットから渡された酒のリストを取得
        if (sakeList != null && !sakeList.isEmpty()) {
            // 酒のリストをループし、テーブルの行を生成
            for (SakeBean sake : sakeList) {
        %>
			<tr>
				<td><%= sake.getSakeName() %></td>
				<td><a href="SakeDetail"><img src="<%=sake.getsImgPath()%>"
						class="image"></a></td>
			</tr>
			<%
            }
        } else {
        %>
			<tr>
				<td colspan="7">該当する酒は見つかりませんでした。</td>
			</tr>
			<%
        }
        %>

		</table>
	</div>


</body>
</html>