<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者ログアウト</title>
<link rel="stylesheet" type="text/css" href="admin.css"> <%-- 共通CSSを読み込む --%>
<link rel="stylesheet" type="text/css" href="adminLogout.css"> <%-- 新しいログアウト完了画面用CSSを読み込む --%>
</head>
<body>

<div class="main-content"> <%-- ここでコンテンツを囲みます --%>
	<h1>ログアウト完了</h1>
	<p>ログアウトが完了しました。</p>
	<p><span id="countdown">5</span>秒後にログインページに戻ります。</p> <%-- カウントダウン表示用のspanを追加 --%>
	<p><a href="adminLogin.jsp" class="redirect-link">戻らない場合はこちらから</a></p>
</div>

<script>
    let timeLeft = 5; // 初期時間
    const countdownElement = document.getElementById('countdown'); // カウントダウン表示要素の取得

    function updateCountdown() {
        countdownElement.textContent = timeLeft; // 残り時間を表示

        if (timeLeft <= 0) { // 時間が0になったら遷移
            window.location.href = "<%= request.getContextPath() %>/adminLogin.jsp";
        } else {
            timeLeft--; // 時間を減らす
            setTimeout(updateCountdown, 1000); // 1秒後に再度実行
        }
    }

    // ページロード時にカウントダウンを開始
    updateCountdown();
</script>

</body>
</html>