document.addEventListener("DOMContentLoaded", function() {
    const menuImages = document.querySelector(".menu-images");
    const menuItems = document.querySelectorAll(".menu-item");
    const btnMoreImages = document.querySelector(".btn-more-images");

    let currentIndex = 0; // 현재 인덱스
    const visibleImages = 5; // 처음에 보여질 이미지 수
    const totalImages = menuItems.length; // 전체 이미지 수

    // 모든 이미지 초기 상태 설정
    function setupInitialImages() {
        menuItems.forEach((item, index) => {
            item.style.display = index < visibleImages ? "flex" : "none"; // 처음 3개 이미지만 표시
            item.classList.remove("fade-out");
            item.style.transform = "translateX(0)";
            item.style.opacity = "1";
        });
    }

    setupInitialImages(); // 초기 설정 호출

    btnMoreImages.addEventListener("click", function() {
        const currentImage = menuItems[currentIndex]; // 현재 보이는 첫 번째 이미지

        // 현재 이미지 왼쪽으로 슬라이드하고 희미해지게 처리
        currentImage.style.transition = "transform 0.5s ease-in-out, opacity 0.5s ease-in-out";
        currentImage.style.transform = "translateX(-100%)"; // 왼쪽으로 슬라이드
        currentImage.style.opacity = "0"; // 사라지는 효과

        // 다음 이미지 인덱스 계산
        let nextImageIndex = (currentIndex + visibleImages) % totalImages;
        const nextImage = menuItems[nextImageIndex];

        // 새로운 이미지를 오른쪽에서 슬라이드해서 나타내기
        nextImage.style.display = "flex";
        nextImage.style.transform = "translateX(100%)"; // 오른쪽에서 등장
        nextImage.style.opacity = "1";
        nextImage.style.transition = "transform 0.5s ease-in-out, opacity 0.5s ease-in-out";

        // 현재 인덱스 증가
        currentIndex++;

        // 슬라이드 효과 후 처리
        setTimeout(() => {
            currentImage.style.display = "none"; // 현재 이미지 숨기기
            nextImage.style.transform = "translateX(0)"; // 새 이미지 원래 위치로 설정
        }, 500);

        // 마지막 이미지까지 도달했을 때
        if (currentIndex >= totalImages) {
            currentIndex = 0; // 인덱스를 다시 0으로 초기화

            setTimeout(() => {
                // 모든 이미지를 숨기고 첫 번째부터 세 번째 이미지 다시 보여주기
                menuItems.forEach(item => {
                    item.style.display = "none"; // 모든 이미지 숨기기
                });
                setupInitialImages(); // 첫 번째부터 세 번째 이미지 다시 보여주기
            }, 500); // 애니메이션이 끝난 후 500ms 대기 후 실행
        }
    });
});
