<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>失敗画面</title>
<link rel="stylesheet" href="failure.css">
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
      <div>失敗<span></span></div>
    </div>
    <br>
    <div class="failure">
    
	    <% if(request.getAttribute("cause") != null){%>
	    	<%= request.getAttribute("cause")%>
	    <%} else{ %>
			<div>何らかの不具合が発生しました。</div>
		<%} %>	
		<div id="countdownMessage"><span id="countdown">5</span>秒後にトップページに戻ります。</div>
		
		<a href="home.jsp">戻らない場合はこちら</a>
	
	</div>
	
</body>
</html>