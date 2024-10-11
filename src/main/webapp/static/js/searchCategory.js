document.addEventListener('DOMContentLoaded', function() {
    // 금액 버튼 클릭 시 해당 금액 입력란에 설정하고 폼 제출 방지
    const priceButtons = document.querySelectorAll('.price-btn');
    priceButtons.forEach(button => {
        button.addEventListener('click', function(event) {
            event.preventDefault();  // 버튼의 기본 제출 동작 막기
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

    // '기타 카테고리' 체크박스 선택 시 입력란 표시/숨기기
    const additionalCategoryCheckbox = document.getElementById('additional-category-checkbox');
    const additionalCategoryInput = document.getElementById('additional-category-input');

    additionalCategoryCheckbox.addEventListener('change', function() {
        if (this.checked) {
            additionalCategoryInput.style.display = 'inline-block';  // 체크 시 입력란 표시
        } else {
            additionalCategoryInput.style.display = 'none';  // 체크 해제 시 입력란 숨김
        }
    });

    // 검색 버튼 클릭 시 최대 금액 입력 확인
    document.querySelector('.submit-btn').addEventListener('click', function(event) {
        const maxPriceInput = document.getElementById('max-price');

        // 입력값이 비어있는지 체크
        if (!maxPriceInput.value) {
            alert('최대 금액을 입력해주세요.'); // 경고 메시지
            event.preventDefault(); // POST 요청 중단
        }
    });
});
