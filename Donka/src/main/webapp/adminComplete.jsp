<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者完了画面</title>
<link rel="stylesheet" type="text/css" href="admin.css">
<link rel="stylesheet" type="text/css" href="adminComplete.css">
</head>
<body>

<jsp:include page="adminHeader.jsp" />

<div class="main-content">
	<h1>処理完了</h1>
	<p>完了しました。</p>
	<p><span id="countdown">5</span>秒後にトップページに戻ります。</p> <%-- カウントダウン表示用のspanを追加 --%>
	<p><a href="adminTop.jsp" class="redirect-link">戻らない場合はこちらから</a></p>
</div>

<script>
    let timeLeft = 5; // 初期時間
    const countdownElement = document.getElementById('countdown'); // カウントダウン表示要素の取得

    function updateCountdown() {
        countdownElement.textContent = timeLeft; // 残り時間を表示

        if (timeLeft <= 0) { // 時間が0になったら遷移
            window.location.href = "<%= request.getContextPath() %>/adminTop.jsp";
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