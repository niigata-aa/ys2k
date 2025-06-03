<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
/* header.css を参考に位置を固定 */
.header-5 {
  box-sizing: border-box;
  width: 100%;
  position: fixed; /* header.cssを参考に固定*/
  top: 0; /* header.cssを参考に固定*/
  background-color: #f8f8f8; /* 白に近いグレー */
  z-index: 2; /* header.cssを参考に設定*/
  border-bottom: 1px solid #ddd; /* 下線 */
}

.header-inner {
  display: flex; /* header.cssを参考にFlexboxレイアウト*/
  align-items: center; /* header.cssを参考に垂直中央揃え*/
  max-width: 1024px; /* header.cssを参考に最大幅*/
  height: 60px; /* header.cssを参考に高さ*/
  padding: 0 0.8em; /* header.cssを参考にパディング*/
  margin: 0 auto; /* 中央揃え */
  font-size: 1.2em; /* header.cssを参考にフォントサイズ*/
  justify-content: space-between; /* ロゴとナビを左右に配置 */
}

.logo img {
  width: 70px; /* header.cssを参考にロゴの幅*/
}

.header-nav-list {
  display: flex; /* リストアイテムを横並びにする */
  list-style: none; /* リストマーカーを非表示 */
  padding: 0;
  margin: 0;
}

.header-nav-item {
  /* float: left; は display: flex と併用しない方が良いので削除 */
  padding: 12px; /* header.cssを参考にパディング*/
  font-size: 20px; /* header.cssを参考にフォントサイズ*/
  font-weight: bold; /* header.cssを参考にフォントの太さ*/
  text-align: center;
  margin-left: 25px; /* 各リンク間の距離 */
}

.header-nav-item:first-child {
  margin-left: 0; /* 最初のアイテムの左マージンはなし */
}

/* リンクのスタイル */
.header-nav-item a {
  text-decoration: none; /* header.cssを参考に下線なし*/
  color: #333; /* 文字色を黒に近いグレーに固定 */
  letter-spacing: 0.15em; /* 字間を調整 */
  display: block; /* パディングを適用するため */
  padding: 0 5px; /* クリック領域を広げるためのパディング*/
  transition: background-color 0.3s ease; /* ホバー時のアニメーション */
  white-space: nowrap; /* テキストの折り返しを防ぐ */
}

/* ホバー時の背景色を白黒に変更、文字色はそのまま */
.header-nav-item a:hover {
  background-color: #e0e0e0; /* 薄いグレーの背景 */
  color: #333; /* 文字色を維持 */
}

/* クリック後や訪問済みのリンクの文字色も固定 */
.header-nav-item a:visited,
.header-nav-item a:active {
  color: #333; /* 文字色を維持 */
}

/* ログアウトボタンのスタイル */
.logout-button {
  background: none; /* 背景なし */
  border: none; /* ボーダーなし */
  color: #333; /* 文字色を黒に近いグレーに固定 */
  font-size: 20px; /* header.cssを参考にフォントサイズ*/
  font-weight: bold; /* header.cssを参考にフォントの太さ*/
  cursor: pointer; /* カーソルをポインターに */
  padding: 12px 5px; /* リンクのパディングに合わせる */
  letter-spacing: 0.15em; /* 字間を調整 */
  transition: background-color 0.3s ease; /* ホバー時のアニメーション */
  white-space: nowrap; /* テキストの折り返しを防ぐ */
}

.logout-button:hover {
  background-color: #e0e0e0; /* ホバーで背景色を変更 */
}

/* ハンバーガーメニュー関連 (header.cssからそのまま引用、今回は表示しない) */
.header-ham {
  display: none; /* header.cssを参考に非表示*/
}

.menu2 {
  width: 20px; /* header.cssを参考に幅*/
  height: 3px; /* header.cssを参考に高さ*/
  margin: 4px 0; /* header.cssを参考にマージン*/
  margin-right: 0; /* header.cssを参考にマージン*/
  background-color: #333; /* header.cssを参考に背景色*/
}
</style>
<title>管理画面ヘッダー</title>
<%-- このCSSファイルを読み込みます --%>
<link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/adminHeaderNew.css">
</head>
<body>
    <header class="header-5">
        <div class="header-inner">
            <nav class="header-nav">
                <ul class="header-nav-list">
                    <li class="header-nav-item"><a href="adminList.jsp">一覧確認</a></li>
                    <li class="header-nav-item"><a href="adminRegistration.jsp">登録</a></li>
                    <li class="header-nav-item"><a href="adminUpdate.jsp">変更</a></li>
                    <li class="header-nav-item"><a href="adminDelete.jsp">削除</a></li>
                    <li class="header-nav-item">
                        <form action="adminLogout.jsp" method="post" style="display:inline;">
                            <button type="submit" class="logout-button">ログアウト</button>
                        </form>
                    </li>
                </ul>
            </nav>
        </div>
    </header>
</body>
</html>