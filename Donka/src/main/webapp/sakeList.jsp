<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.List,java.util.Set,java.util.Map,java.util.HashMap,model.entity.SakeBean,model.entity.UserBean,java.util.HashSet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>酒一覧</title>
<link rel="stylesheet" href="sakeTable.css">
<style>
.heart-icon {
	cursor: pointer;
	font-size: 24px; /* ハートアイコンのサイズ */
	margin-left: 10px;
	vertical-align: middle; /* テキストと高さを合わせる */
}

.heart-pink {
	color: #ff69b4; /* いいね済み（奇数） */
}

.heart-gray {
	color: #cccccc; /* いいねなし（偶数） */
}

.likes-count {
	font-size: 14px;
	margin-left: 5px;
	vertical-align: middle;
}
</style>
</head>
<body>

	<jsp:include page="header.jsp"></jsp:include>



	<%
// サーブレットから渡された酒のリストを取得
List<SakeBean> sakeList = (List<SakeBean>)request.getAttribute("sakeList");

// ログインユーザー情報をセッションから取得
UserBean user = (UserBean) session.getAttribute("user");
boolean isLoggedIn = (user != null);
%>

	<div class="search">

		<form action="SakeSearch" method="post">
			<div class="box-title">
				<h2>検索条件</h2>
			</div>
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
            if (selectedAlcRanges == null) selectedAlcRanges = new HashSet<>();

            String[] alcRanges = {"~12", "13-15", "16~"};
            for (String range : alcRanges) {
                String checkboxId = "alcRange_" + range.replace("~", "_").replace("-", "_");
            %>
				<input type="checkbox" name="alcRange" value="<%= range %>"
					id="<%= checkboxId %>"
					<%= selectedAlcRanges.contains(range) ? "checked" : "" %>>
				<label for="<%= checkboxId %>"><%= range %>%</label>
				<%
            }
            %>
			</p>

			<p>
				<label for="tasteSelect">味わい:</label> <select name="taste"
					id="tasteSelect">
					<option value="all">-- 全て --</option>
					<%
                String[] tastes = {"辛口", "甘口", "中口"};
                String selectedTaste = (String) request.getAttribute("selectedTaste");
                if (selectedTaste == null) selectedTaste = "";

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
                String[] fDrinks = {"冷", "燗", "常"};
                String selectedFDrink = (String) request.getAttribute("selectedFDrink");
                if (selectedFDrink == null) selectedFDrink = "";

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
	</div>


	<div class="tab">

		<button onclick="location.href='BreweryView'">酒蔵</button>
		<button onclick="location.href='SakeViewServlet'">日本酒</button>

	</div>



	<div class="d1">
		<h1>酒の一覧</h1>
	</div>



	<div class="table_box">
		<table>
			<%
        if (sakeList != null && !sakeList.isEmpty()) {
            for (SakeBean sake : sakeList) {
                int currentSakeId = sake.getSakeId();
                int initialLikesCount = sake.getVoteCount();
                boolean initialVotedByUser = sake.isVotedByUser();
        %>
			<tr>
				<td><%= sake.getSakeName()%></td>
				<td>
					<form action="BreweryDetail" method="post">
						<input type="hidden" name="brewid" value=<%=sake.getBreweryId()%>>
						<button style="background: transparent; border: none;">
							<img src="/Donka/<%=sake.getsImgPath()%>"
								alt="<%= sake.getSakeName() %>" width="100" class="image">
						</button>
					</form>
				</td>
				<td>
					<%-- ログインしている場合のみクリック可能なハートアイコンを表示 --%> <% if (isLoggedIn) { %> <span
					class="heart-icon <%= initialVotedByUser ? "heart-pink" : "heart-gray" %>"
					data-sake-id="<%= currentSakeId %>"
					data-voted="<%= initialVotedByUser ? "true" : "false" %>">
						&#x2764; </span> <% } else { %> <%-- ログインしていない場合はクリックイベントを捕捉するためにdata-sake-idを残し、スタイルでカーソルをデフォルトに --%>
					<span class="heart-icon heart-gray" style="cursor: pointer;"
					data-sake-id="<%= currentSakeId %>" data-voted="false"> <%-- 常にfalseとしておく --%>
						&#x2764;
				</span> <% } %> <span class="likes-count"
					id="likes-count-<%= currentSakeId %>"><%= initialLikesCount %></span>
				</td>
			</tr>
			<%
            }
        } else {
        %>
			<tr>
				<td colspan="3">該当する酒は見つかりませんでした。</td>
			</tr>
			<%
        }
        %>

		</table>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

	<script>
    const isLoggedIn = <%= isLoggedIn %>;
    const contextPath = "<%= request.getContextPath() %>";

    document.addEventListener('DOMContentLoaded', async () => {
        const heartIcons = document.querySelectorAll('.heart-icon');

        heartIcons.forEach(icon => {
            icon.addEventListener('click', async () => {
                // ログインしていない場合はアラートを表示して処理を中断
                if (!isLoggedIn) {
                    alert('いいねをするにはログインしてください。');
                    return; // ここで処理を終了
                }

                const sakeId = icon.dataset.sakeId;
                const isVoted = icon.dataset.voted === 'true';
                const action = isVoted ? 'unvote' : 'vote';

                const formData = new URLSearchParams();
                formData.append('sakeId', sakeId);
                formData.append('action', action);

                try {
                    const response = await fetch(contextPath + '/Vote', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded'
                        },
                        body: formData
                    });
                    const data = await response.json();

                    if (data.success) {
                        icon.dataset.voted = data.votedByUser;
                        updateHeartIcon(icon);
                        
                        const likesCountSpan = document.getElementById(`likes-count-${sakeId}`);
                        if (likesCountSpan) {
                            likesCountSpan.textContent = data.newVoteCount;
                        }
                    } else {
                        alert(data.message);
                    }
                } catch (error) {
                    console.error('Error:', error);
                    alert('通信エラーが発生しました。');
                }
            });
        });

        function updateHeartIcon(icon) {
            const isVoted = icon.dataset.voted === 'true';
            if (isVoted) {
                icon.classList.add('heart-pink');
                icon.classList.remove('heart-gray');
            } else {
                icon.classList.add('heart-gray');
                icon.classList.remove('heart-pink');
            }
        }
    });
</script>
</body>
</html>