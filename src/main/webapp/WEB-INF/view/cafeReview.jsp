<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
        <div class="logo"><a href="mainpage">TASTE GPT</a></div>
        <div class="search-container">
            <a href="searchCategory" class="searchbtn">맛집 검색 🍽️</a>
            <a href="searchCategory">지역별 검색 🎮</a>
            <a href="searchCategory" class="btn-header">유형별 검색 🎮</a>
            
        </div>
        <div class="login">
            <!-- 로그인 버튼 클릭 시 login.jsp로 이동 -->
            <form action="/login" method="get">
                <button class="btn">로그인</button>
            </form>
        </div>
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
					<label for="imageUpload">이미지 추가</label> <input type="file"
						id="imageUpload" name="imageUpload" accept="image/*">
					<!-- 이미지 미리보기 -->
					<img id="imagePreview" src="#" alt="이미지 미리보기"
						style="display: none; max-width: 200px; margin-top: 10px;">
				</div>

				<!-- 평점 부분 -->
				<div class="review-inputs">
					<label for="rating">평점:</label>
					<div class="star-rating">
						<span class="star" data-value="5">&#9733;</span> <span
							class="star" data-value="4">&#9733;</span> <span class="star"
							data-value="3">&#9733;</span> <span class="star" data-value="2">&#9733;</span>
						<span class="star" data-value="1">&#9733;</span>
					</div>
					<!-- 실제 평점 값 -->
					<input type="hidden" id="rating" name="rating" value="0">
					<div class="rating-value">0.0</div>

					<label for="reviewContent">리뷰 내용:</label>
					<textarea id="reviewContent" name="reviewContent"
						placeholder="리뷰 내용을 작성해주세요" rows="5"></textarea>

					<label for="price">이용 금액:</label> <input type="number" id="price"
						name="price" placeholder="이용 금액 입력"> <label for="menu">이용
						메뉴:</label>
					<c:forEach var="cafeteria" items="${list}">
						
							<ul>
								<li>${cafeteria.cafeName}</li>
							</ul>
						
					</c:forEach>
				</div>
			</div>

			<!-- 작성 완료 버튼 -->
			<button class="btn-submit">작성 완료</button>
		</section>
	</main>

	<script>
		// 이미지 업로드 시 미리보기 기능
		document.getElementById('imageUpload').addEventListener(
				'change',
				function(event) {
					const imagePreview = document
							.getElementById('imagePreview');
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
		
		document.addEventListener('DOMContentLoaded', function() {
		    const stars = document.querySelectorAll('.star');
		    const ratingValue = document.querySelector('.rating-value');
		    const ratingInput = document.getElementById('rating');

		    stars.forEach(star => {
		        star.addEventListener('click', function() {
		            const rating = this.getAttribute('data-value');

		            // 클릭한 별까지 노란색으로 채우기
		            stars.forEach((s, index) => {
		                if (index > 4 - rating) {
		                    s.classList.add('selected');
		                } else {
		                    s.classList.remove('selected');
		                }
		            });

		            // 점수 표시 및 hidden input 값 설정
		            ratingValue.textContent = rating + ".0";
		            ratingInput.value = rating;
		        });
		    });
		});



	</script>
</body>
</html>
