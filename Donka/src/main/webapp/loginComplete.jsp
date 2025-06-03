<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン完了画面</title>
<link rel="stylesheet" href="loginComp.css">
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
      <div>ようこそ<span></span></div>
    </div>
    <br>
    <div class="login_ok">

    ログインが完了しました。<br>
	<span id="countdown">5</span>秒後に酒一覧ページに戻ります。<br>
	<a href="home.jsp">戻らない場合はこちら</a>
	
	</div>

<script type="text/javascript">
    window.onload = function() {
        let count = 5;
        const countdownElement = document.getElementById('countdown');

        const timer = setInterval(function() {
            count--;
            countdownElement.textContent = count;

            if (count <= 0) {
                clearInterval(timer); // カウントダウンを停止
                // 酒一覧ページへ遷移
                window.location.href = "home.jsp";
            }
        }, 1000); // 1秒ごとに実行
    };
</script>
</body>
</html>