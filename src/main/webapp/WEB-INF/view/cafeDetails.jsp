<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>가게 상세보기</title>
<link rel="stylesheet" type="text/css"
	href="/static/css/cafeDetails.css">
	
<!-- 서버에서 넘겨준 address 값을 자바스크립트 변수로 전달 -->
<script type="text/javascript">
    var address = "<%= request.getAttribute("address") %>";
    var cafeName = "<%= request.getAttribute("cafeName") %>";
</script>
</head>
<body>
	 <!-- 상단 고정 바 -->
    <header>
        <div class="logo"><a href="mainpage">TASTE GPT</a></div>
        <div class="search-container">
            <a href="mainpage" class="searchbtn">맛집 검색 🍽️</a>
            <a href="searchCategory">지역별 검색 🎮</a>
            <a href="searchCategory" class="btn-header">유형별 검색 🎮</a>
            
        </div>
        <div class="login">
    <c:choose>
        <c:when test="${not empty userID}">
            <span>${userID} 님, 환영합니다!</span>
            <!-- 내 정보 보기 버튼 -->
            <button class="btn" onclick="location.href='/myInfo'">내 정보 보기</button>
            <!-- 로그아웃 버튼 -->
            <form action="/mainpage" method="get" style="display:inline;">
                <input type="hidden" name="action" value="logout">
                <button class="btn">로그아웃</button>
            </form>
        </c:when>
        <c:otherwise>
            <!-- 로그인 버튼 -->
            <form action="/login" method="get">
                <button class="btn">로그인</button>
            </form>
        </c:otherwise>
    </c:choose>
</div>
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
			<h1 class="store-title">${cafeteria.cafeName}</h1>

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
<div class ="store-infoDetail">
<div class="store-details">
    <div class="store-image"  style="width:300px;height:300px;margin-right:100px;">가게 이미지</div>
    <p style="margin-top:-12px">
    <em class="link">
        <a href="javascript:void(0);" onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
        </a>
    </em>
</p>
<div id="store-map" style="width:300px;height:300px; margin-right:100px"></div>


<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1b35dc634ac3bd42d8c421860d626613&libraries=services"></script>
<script>
var mapContainer = document.getElementById('store-map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch(address, function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === kakao.maps.services.Status.OK) {

        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        // 결과값으로 받은 위치를 마커로 표시합니다
        var marker = new kakao.maps.Marker({
            map: map,
            position: coords
        });

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new kakao.maps.InfoWindow({
            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+ cafeName +'</div>'
        });
        infowindow.open(map, marker);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);
    } 
});    
</script>
	<ul class="store-info-list" style="width:300px; height:300px; margin-right:100px; text-align: center; display: flex; flex-direction: column; justify-content: center; align-items: center;">
    <li>상호명: <strong>${cafeteria.cafeName}</strong></li>
    <li>평균 금액: <strong>${cafeteria.cafePrice} 원</strong></li>
    <li>전화번호: <strong>${cafeteria.cafePhoneNumber}</strong></li>
    <li>주소: <strong>${cafeteria.cafeAddress}</strong></li>
    <li id="score">
        평점: <strong>${score}</strong> 
        <div class="star-rating">
            <div class="star-rating-filled" style="width: calc(${score} * 20%)"></div>
            
        </div>
    </li>
</ul>


</div>
</div>
	
			<!-- 간단한 맛집 소개 -->
			<div class="store-description">
				<h2>간단한 맛집 소개</h2>
				<p>이곳은 맛집에 대한 간단한 설명을 보여줍니다.</p>
				<p>상 호 명 : ${cafeteria.cafeName}</p>
				<p>영업 시간 : ${cafeteria.cafeOpenTime}</p>
				<p>가게 유형 : </p>
				<p>가게 태그 : </p>
				
			</div>
		</section>
	
		<!-- 추가된 리뷰 섹션 -->
		<section class="review-section">
			<h2>Review</h2>
			<div>
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

	
</body>

<!-- JavaScript for the image slider -->
<script src="/static/js/cafeDetails.js"></script>
</html>
