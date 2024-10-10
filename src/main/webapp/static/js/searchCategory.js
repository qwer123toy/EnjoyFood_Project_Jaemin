document.addEventListener('DOMContentLoaded', function() {
    // 금액 버튼 클릭 시 해당 금액 입력란에 설정
    const priceButtons = document.querySelectorAll('.price-btn');
    priceButtons.forEach(button => {
        button.addEventListener('click', function() {
            const price = this.getAttribute('data-price');
            document.getElementById('max-price').value = price;
        });
    });

    // 체크박스 클릭 시 배경색 변경
    const categories = document.querySelectorAll('.category');
    categories.forEach(category => {
        category.addEventListener('click', function() {
            const checkbox = this.querySelector('input[type="checkbox"]');
            if (checkbox) {
                checkbox.checked = !checkbox.checked;
                this.classList.toggle('active', checkbox.checked);
            }
        });
    });
});
