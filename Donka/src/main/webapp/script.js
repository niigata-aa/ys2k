document.addEventListener('DOMContentLoaded', () => {
    const accordionItems = document.querySelectorAll('.accordion-item');

    accordionItems.forEach(item => {
        const header = item.querySelector('.accordion-header');
        const toggleButton = item.querySelector('.toggle-button');
        const content = item.querySelector('.accordion-content');

        header.addEventListener('click', () => {
            item.classList.toggle('active');

            if (item.classList.contains('active')) {
                toggleButton.textContent = '-';
                // アコーディオンが開くときにコンテンツのscrollHeightを設定
                // content.scrollHeight は、パディングを含む要素の全てのコンテンツのピクセル単位の高さ
                content.style.maxHeight = content.scrollHeight + 'px';
            } else {
                toggleButton.textContent = '+';
                // アコーディオンが閉じるときはmaxHeightを0に戻す
                content.style.maxHeight = '0';
            }
        });
    });
});