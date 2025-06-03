<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>完了画面</title>
<link rel="stylesheet" href="user_comp.css">
<script>
    window.onload = function() {
        let countdownElement = document.getElementById('countdown'); // カウントダウンを表示する要素
        let timeLeft = 5; // 初期秒数

        // カウントダウン表示を更新する関数
        function updateCountdown() {
            countdownElement.textContent = timeLeft;
            timeLeft--;

            if (timeLeft < 0) {
                // カウントが0になったら遷移
                window.location.href = "home.jsp"; // 遷移先のURL
            } else {
                // 1秒ごとに更新
                setTimeout(updateCountdown, 1000);
            }
        }

        // 最初のカウントダウンを開始
        updateCountdown();
    };
</script>
</head>
<body>
<ul class="sakura">
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
  <li></li>
</ul>

<div class="body"></div>
    <div class="grad"></div>
    <div class="header">
      <div>登録完了<span></span></div>
    </div>
    <br>
    <div class="user">
    
   会員登録が完了しました。<br>
	<span id="countdown">5</span>秒後にトップページに戻ります。
	<a href="home.jsp">戻らない場合はこちらから</a>

	
	
	</div>
	
	

</body>
</html>