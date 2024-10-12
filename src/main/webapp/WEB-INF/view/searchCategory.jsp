<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Taste GPT</title>
<link rel="stylesheet" type="text/css"
	href="/static/css/searchCategory.css">
<script src="/static/js/searchCategory.js"></script>
</head>
<body>

	<!-- 상단 고정 바 -->
	<jsp:include page="/WEB-INF/module/header.jsp"></jsp:include>

	<!-- 왼쪽 고정 바 -->
	<jsp:include page="/WEB-INF/module/sidebar.jsp"></jsp:include>

	<!-- 왼쪽 고정 바 -->
	<nav class="sidebar">
		<button>유형별 검색</button>

	</nav>

	<!-- 검색 입력란 -->
	<main>

		<div class="search-bar">
			<form method="post">
				<input type="number" name="max-price" id="max-price"
					placeholder="최대 금액" />
				<button class="submit-btn" type="submit">검색</button>
				<select id="people-combo" name="people-combo">
					<option value="1">1명</option>
					<option value="2">2명</option>
					<option value="3">3명</option>
					<option value="4">4명</option>
					<option value="5">5명</option>
					<option value="6">6명</option>
					<option value="7">7명</option>
					<option value="8">8명</option>
				</select>

				<!-- 금액 범위 버튼 -->
				<section id="price-buttons">
					<button class="price-btn" data-price="10000">1만원 미만</button>
					<button class="price-btn" data-price="30000">3만원 미만</button>
					<button class="price-btn" data-price="50000">5만원 미만</button>
					<button class="price-btn" data-price="100000">10만원 미만</button>
				</section>

				<!-- 체크박스 카테고리 -->
				<section id="categories">
					<label class="category" for="date"> <input type="checkbox"
						id="date" name="category" value="데이트"> 데이트
					</label> <label class="category" for="family"> <input
						type="checkbox" id="family" name="category" value="가족 외식">
						가족 외식
					</label> <label class="category" for="student"> <input
						type="checkbox" id="student" name="category" value="학생">
						학생
					</label> <label class="category" for="special-event"> <input
						type="checkbox" id="special-event" name="category" value="특별한 이벤트">
						특별한 이벤트
					</label> <label class="category" for="dessert"> <input
						type="checkbox" id="dessert" name="category" value="디저트">
						디저트
					</label> <label class="category" for="omakase"> <input
						type="checkbox" id="omakase" name="category" value="오마카세">
						오마카세
					</label>
					<!-- 기타 카테고리 항목 -->
					<label class="category" for="additional-category-checkbox">
						<input type="checkbox" id="additional-category-checkbox"
						name="category" value="기타 카테고리"> 직접 입력
					</label> <input type="text" id="additional-category-input"
						name="additionalCategory" placeholder="직접 입력"
						style="display: none;">

				</section>


			</form>
		</div>

		<!-- 추천 가게 이미지 및 맛집 정보 -->
		<div class="store-list">
			<c:forEach var="cafeteria" items="${list}">
				<div class="store-item">
					<a href="/store-details/${cafeteria.cafeNum}"> <!--  <div class="store-image">이미지 넣을거임!</div> -->
					</a>
					<ul>
						<li><a
							href="cafeteria?cafeNum=${cafeteria.cafeNum}&cafeName=${cafeteria.cafeName}">${cafeteria.cafeName}</a></li>

						<li>인당 평균 금액: ${cafeteria.cafePrice} 원</li>
						<li>전화번호: ${cafeteria.cafePhoneNumber}</li>
						<li><a href="#">주소: ${cafeteria.cafeAddress}</a></li>
					</ul>
				</div>
			</c:forEach>
		</div>
	</main>
</body>
</html>
