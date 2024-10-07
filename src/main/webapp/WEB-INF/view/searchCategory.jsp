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
    <header>
        <div ><a href="mainpage" class="logo">TASTE GPT</a></div>
        <div class="search-container">
            <button class="btn">맛집 검색 🍽️</button>
            <button class="btn">지역별 검색 🎮</button>
            <a href="searchCategory" class="btn">유형별 검색 🎮</a>
            
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
        	<button>유형별 검색</button>

    </nav>
	 <!-- 검색 입력란 -->
        <div class="search-bar">
            <form method="get" action="mainpage">
               <input type="number" id="max-price" placeholder="최대 금액" />
               <button type="submit">검색</button>
		<select id="people-combo">
			<option value="1">1명</option>
			<option value="2">2명</option>
			<option value="3">3명</option>
			<option value="4">4명</option>
			<option value="5">5명</option>
			<option value="6">6명</option>
			<option value="7">7명</option>
			<option value="8">8명</option>
		</select>
            </form>
        </div>
   
<section id ="body_section">
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
			id="date" name="category"> 데이트
		</label> <label class="category" for="family"> <input type="checkbox"
			id="family" name="category"> 가족 외식
		</label> <label class="category" for="student"> <input type="checkbox"
			id="student" name="category"> 학생
		</label> <label class="category" for="special-event"> <input
			type="checkbox" id="special-event" name="category"> 특별한 이벤트
		</label> <label class="category" for="dessert"> <input type="checkbox"
			id="dessert" name="category"> 디저트
		</label> <label class="category" for="omakase"> <input type="checkbox"
			id="omakase" name="category"> 오마카세
		</label>
	</section>
</section>

	<!-- 추천 가게 이미지 및 맛집 정보 -->
	<section id="recommendations">
		<div class="recommendation-item">
			<a href="recommendation1.html" class="store-recommendation">추천 가게
				이미지</a>
			<div class="store-info">
				<p>
					맛집명: <span>가게 이름</span>
				</p>
				<p>
					평균 금액: <span>30,000원</span>
				</p>
				<p>
					주소: <span>서울특별시 강남구</span>
				</p>
			</div>
		</div>
		<div class="recommendation-item">
			<a href="recommendation2.html" class="store-recommendation">추천 가게
				이미지</a>
			<div class="store-info">
				<p>
					맛집명: <span>가게 이름</span>
				</p>
				<p>
					평균 금액: <span>50,000원</span>
				</p>
				<p>
					주소: <span>서울특별시 용산구</span>
				</p>
			</div>
		</div>
	</section>

</body>
</html>
