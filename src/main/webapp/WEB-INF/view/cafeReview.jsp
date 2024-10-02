<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰 작성</title>
    <link rel="stylesheet" type="text/css" href="/static/css/cafeReview.css">
</head>
<body>
    <!-- 상단 고정 바 -->
    <header>
        <div class="logo">TASTE GPT</div>
        <div class="menu-container">
            <button class="btn">메인화면</button>
        </div>
        <div class="user-info">유저 정보</div>
    </header>

    <!-- 왼쪽 고정 바 -->
    <nav class="sidebar">
        <button class="btn">리뷰 작성</button>
        <button class="btn">고정바</button>
    </nav>

    <!-- 메인 컨텐츠 -->
    <main>
        <section class="review-section">
            <h1 class="review-title">리뷰 작성 화면</h1>
            <div class="review-container">
                <!-- 이미지 업로드 -->
                <div class="review-image">
                    <label for="imageUpload">이미지 추가</label>
                    <input type="file" id="imageUpload" name="imageUpload" accept="image/*">
                    <!-- 이미지 미리보기 -->
                    <img id="imagePreview" src="#" alt="이미지 미리보기" style="display:none; max-width: 200px; margin-top: 10px;">
                </div>

                <!-- 평점, 리뷰 내용 -->
                <div class="review-inputs">
                    <label for="rating">평점:</label>
                    <input type="number" id="rating" name="rating" min="1" max="5">

                    <label for="reviewContent">리뷰 내용:</label>
                    <textarea id="reviewContent" name="reviewContent" placeholder="리뷰 내용을 작성해주세요" rows="5"></textarea>

                    <!-- 메뉴 선택 및 이용 금액 -->
                    <label for="price">이용 금액:</label>
                    <input type="number" id="price" name="price" placeholder="이용 금액 입력">
                </div>
            </div>

            <!-- 작성 완료 버튼 -->
            <button class="btn-submit">작성 완료</button>
        </section>
    </main>

    <script>
        // 이미지 업로드 시 미리보기 기능
        document.getElementById('imageUpload').addEventListener('change', function(event) {
            const imagePreview = document.getElementById('imagePreview');
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    imagePreview.src = e.target.result;
                    imagePreview.style.display = 'block';
                }
                reader.readAsDataURL(file);
            }
        });
    </script>
</body>
</html>
