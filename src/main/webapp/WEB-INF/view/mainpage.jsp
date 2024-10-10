<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Taste GPT</title>
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&family=Roboto:wght@400;500&display=swap" rel="stylesheet">
    <script src="/static/js/mainpage.js"></script>
    <link rel="stylesheet" type="text/css" href="/static/css/mainpage.css">
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
        <button>인기순</button>
        <button>신규 오픈</button>
        <button>리뷰 보기</button>
        <button>문의사항</button>
    </nav>

    <!-- 메인 컨텐츠 -->
    <main>
        <!-- 검색 입력란 -->
        <div class="search-bar">
            <form method="post">
                <input type="text" name="searchQuery" id ="searchQuery" placeholder="맛집 검색어 입력">
                <button type="submit">→</button>
            </form>
        </div>

        <!-- 가게 리스트 -->
            <!-- 데이터베이스에서 검색 결과를 불러와서 JSTL로 반복 출력 -->
      <div class="store-list">
            <c:forEach var="cafeteria" items="${list}">
                <div class="store-item">
                    <a href="/store-details/${cafeteria.cafeNum}">
                        <!--  <div class="store-image">이미지 넣을거임!</div> -->
                    </a>
                    
                    <ul>
                    
                        <li><a href="cafeteria?cafeNum=${cafeteria.cafeNum}&cafeName=${cafeteria.cafeName}">${cafeteria.cafeName}</a></li>
                        <li>평균 금액: ${cafeteria.cafePrice} 원</li>
                        <li>전화번호: ${cafeteria.cafePhoneNumber}</li>
                        <li><a href="#">주소: ${cafeteria.cafeAddress}</a></li>
                        
                    </ul>
                    
                </div>
            </c:forEach>
        </div>  
    </main>
</body>
</html>
