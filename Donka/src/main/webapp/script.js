/**
 * 
 */

document.addEventListener('DOMContentLoaded', () => {
    // すべてのアコーディオンアイテムを取得
    const accordionItems = document.querySelectorAll('.accordion-item');

    accordionItems.forEach(item => {
        // 各アイテムのヘッダーとトグルボタンを取得
        const header = item.querySelector('.accordion-header');
        const toggleButton = item.querySelector('.toggle-button');
        const content = item.querySelector('.accordion-content');

        // ヘッダーまたはボタンがクリックされたときのイベントリスナー
        header.addEventListener('click', () => {
            // accordion-itemに'active'クラスをトグルする
            item.classList.toggle('active');

            // ボタンのテキストを切り替える (例: '+' と '-')
            if (item.classList.contains('active')) {
                toggleButton.textContent = '-';
            } else {
                toggleButton.textContent = '+';
            }

            // コンテンツのmax-heightを調整してアニメーションを制御
            // CSSのmax-heightとtransitionで開閉を制御しているので、
            // JavaScriptで直接高さを設定する必要はほとんどありませんが、
            // より複雑な開閉アニメーションが必要な場合はここにロジックを追加できます。
            // 例: content.style.maxHeight = item.classList.contains('active') ? content.scrollHeight + 'px' : '0';
        });
    });
});