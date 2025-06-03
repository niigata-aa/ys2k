<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト</title>
<link rel="stylesheet" href="logout.css">
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

<%-- ヘッダーは外部ファイルとしてインクルードされているので、ここでは動的なユーザー名表示は不要です --%>
<jsp:include page="header.jsp"></jsp:include>
<div class="body"></div>
    <div class="grad"></div>
    <div class="header">
      <div>退出<span></span></div>
    </div>
    <br>
    <div class="logout">

	ログアウトが完了しました。<br>
	<span id="countdown">5</span>秒後にトップページに戻ります。<br>
	<a href="home.jsp">戻らない場合はこちらから</a>
	
	</div>

<jsp:include page="footer.jsp"></jsp:include>

<script type="text/javascript">
    window.onload = function() {
        let count = 5;
        const countdownElement = document.getElementById('countdown');

        const timer = setInterval(function() {
            count--;
            countdownElement.textContent = count;

            if (count <= 0) {
                clearInterval(timer); // カウントダウンを停止
                // トップページへ遷移
                window.location.href = "home.jsp";
            }
        }, 1000); // 1秒ごとに実行
    };
</script>
</body>
</html>