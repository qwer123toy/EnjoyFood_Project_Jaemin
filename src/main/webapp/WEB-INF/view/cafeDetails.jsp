<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>가게 상세보기</title>
<link rel="stylesheet" type="text/css"
	href="/static/css/cafeDetails.css">
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
		<button class="btn">가게 상세보기 화면2입니다.</button>
		<button class="btn">고정바</button>
	</nav>

	<!-- 메인 컨텐츠 -->
	<main>
		<section class="store-info">
			<!-- 기존 상세보기 화면 -->
			<h1 class="store-title">가게 상세보기 화면 1</h1>

			<!-- 메뉴 이미지 슬라이더 -->
			<div class="menu-images-wrapper">
				<div class="menu-images">
					<div class="menu-item">메뉴 이미지 1</div>
					<div class="menu-item">메뉴 이미지 2</div>
					<div class="menu-item">메뉴 이미지 3</div>
					<div class="menu-item">메뉴 이미지 4</div>
					<div class="menu-item">메뉴 이미지 5</div>
				</div>
				<button class="btn-more-images">이미지 더보기 →</button>
			</div>

			<!-- 가게 정보 -->
			<div class="store-details">
				<div class="store-image">가게 이미지</div>
				<ul class="store-info-list">
					<li>맛집명: <strong>맛집명</strong></li>
					<li>평균 금액: <strong>평균 금액</strong></li>
					<li>전화번호: <strong>전화번호</strong></li>
					<li>주소: <strong>주소</strong></li>
				</ul>
			</div>

			<!-- 간단한 맛집 소개 -->
			<div class="store-description">
				<h2>간단한 맛집 소개</h2>
				<p>이곳은 맛집에 대한 간단한 설명을 보여줍니다.</p>
			</div>
		</section>
	
		<!-- 추가된 리뷰 섹션 -->
		<section class="review-section">
			<h2>Review</h2>
			<div>
				<p>최신순</p> <p>평점순</p>
			</div>
			<div class="review-list">
				<div class="review-item">
					<div class="review-image">이미지</div>
					<p>
						<strong>평점:</strong>
					</p>
					<p>
						<strong>리뷰 내용:</strong>
					</p>
					<p>
						<strong>메뉴별 금액:</strong>
					</p>
				</div>
				<div class="review-item">
					<div class="review-image">이미지</div>
					<p>
						<strong>평점:</strong>
					</p>
					<p>
						<strong>리뷰 내용:</strong>
					</p>
					<p>
						<strong>메뉴별 금액:</strong>
					</p>
				</div>
				<div class="review-item">
					<div class="review-image">이미지</div>
					<p>
						<strong>평점:</strong>
					</p>
					<p>
						<strong>리뷰 내용:</strong>
					</p>
					<p>
						<strong>메뉴별 금액:</strong>
					</p>
				</div>
			</div>
			<button class="btn-review">리뷰 작성하기</button>
		</section>
	</main>

	<!-- 오른쪽 스크롤 바 -->
	<div class="scroll-bar"></div>

</body>

<!-- JavaScript for the image slider -->
<script src="/static/js/cafeDetails.js"></script>
</html>
