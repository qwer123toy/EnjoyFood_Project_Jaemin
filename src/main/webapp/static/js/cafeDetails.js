document.addEventListener("DOMContentLoaded", function() {
    const menuImages = document.querySelector(".menu-images");
    const menuItems = document.querySelectorAll(".menu-item");
    const btnMoreImages = document.querySelector(".btn-more-images");

    let currentIndex = 0;
    const visibleImages = 3; // 처음 보일 이미지 수
    const totalImages = menuItems.length; // 전체 이미지 수

    // 모든 이미지 초기 상태 설정
    function setupInitialImages() {
        menuItems.forEach((item, index) => {
            item.style.display = index < visibleImages ? "flex" : "none"; // 처음 3개 이미지는 보이도록 설정
            item.classList.remove("fade-out"); // 희미하게 처리 해제
            item.style.transform = "translateX(0)"; // 초기 위치로 설정
            item.style.opacity = "1"; // 보이도록 설정
        });
    }

    setupInitialImages(); // 초기 설정 호출

    btnMoreImages.addEventListener("click", function() {
        // 현재 보이는 이미지
        const currentImage = menuItems[currentIndex];

        // 왼쪽으로 슬라이드하고 사라지는 효과
        currentImage.style.transition = "transform 0.5s ease-in-out, opacity 0.5s ease-in-out"; // 부드러운 전환
        currentImage.style.transform = "translateX(-100%)"; // 왼쪽으로 슬라이드
        currentImage.style.opacity = "0"; // 사라지는 효과

        // 다음 인덱스의 이미지를 계산
        const nextImageIndex = (currentIndex + visibleImages) % totalImages; // 다음 이미지 인덱스

        // 새로운 이미지를 보이게 설정
        const nextImage = menuItems[nextImageIndex];
        nextImage.style.display = "flex"; // 다음 이미지를 보이도록 설정
        nextImage.style.transform = "translateX(100%)"; // 오른쪽에서 들어오게 설정
        nextImage.style.opacity = "1"; // 완전히 보이도록 설정
        nextImage.style.transition = "transform 0.5s ease-in-out, opacity 0.5s ease-in-out"; // 부드러운 전환

        // 현재 인덱스 증가
        currentIndex++;

        // 슬라이드 효과 후 처리
        setTimeout(() => {
            // 현재 이미지를 숨기기
            currentImage.style.display = "none"; 
            // 다음 이미지를 왼쪽으로 슬라이드
            nextImage.style.transform = "translateX(0)"; // 원래 위치로 돌아오기
        }, 500); // 슬라이드 효과 후 500ms 대기

        // 모든 이미지가 사라진 경우 다시 처음 3개 이미지를 보이도록
        if (currentIndex >= totalImages) {
            currentIndex = 0; // 처음으로 돌아가기
            setTimeout(() => {
                menuItems.forEach(item => {
                    item.style.display = "none"; // 모든 이미지 숨기기
                });
                setupInitialImages(); // 다시 처음 3개 보이도록 설정
            }, 500); // 이전 이미지가 사라지는 동안 대기
        }
    });
});
