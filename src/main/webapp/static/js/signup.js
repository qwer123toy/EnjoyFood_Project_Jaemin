document.addEventListener('DOMContentLoaded', () => {
    const businessOwnerBtn = document.getElementById('businessOwnerBtn');
    const businessOwnerFields = document.getElementById('businessOwnerFields');

    businessOwnerBtn.addEventListener('click', () => {
        if (businessOwnerFields.classList.contains('hidden')) {
            businessOwnerFields.classList.remove('hidden');
            businessOwnerBtn.textContent = '사업주 닫기';
        } else {
            businessOwnerFields.classList.add('hidden');
            businessOwnerBtn.textContent = '사업주';
        }
    });

    document.getElementById('signupForm').addEventListener('submit', (event) => {
        event.preventDefault();
        // 여기서 폼 제출 로직 추가 (예: AJAX 요청 등)
        alert('회원가입이 완료되었습니다!');
    });
});