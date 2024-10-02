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
    <link rel="stylesheet" type="text/css" href="/static/css/mainpage.css">
    <script src="/static/js/mainpage.js"></script>
</head>
<body>
    <!-- 상단 고정 바 -->
    <header>
        <div class="logo">TASTE GPT</div>
        <div class="search-container">
            <button class="btn">맛집 검색 🍽️</button>
            <button class="btn">지역별 검색 🎮</button>
            <button class="btn">유형별 검색 🎮</button>
        </div>
        <div class="login">
            <!-- 로그인 버튼 클릭 시 login.jsp로 이동 -->
            <form action="login.jsp" method="get">
                <button class="btn">로그인</button>
            </form>
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
            <form method="get" action="mainpage.jsp">
                <input type="text" name="searchQuery" placeholder="맛집 검색어 입력">
                <button type="submit">→</button>
            </form>
        </div>

        <!-- 가게 리스트 -->
        <div class="store-list">
            <!-- 데이터베이스에서 검색 결과를 불러와서 JSTL로 반복 출력 -->
            <c:forEach var="store" items="${cafeteria}">
                <div class="store-item">
                    <a href="/store-details/${cafeteria.id}">
                        <div class="store-image">${cafeteria.image}</div>
                    </a>
                    <ul>
                        <li>${cafeteria.name}</li>
                        <li>평균 금액: ${cafeteria.averagePrice}</li>
                        <li>전화번호: ${cafeteria.phone}</li>
                        <li><a href="/map/${cafeteria.id}">주소: ${cafeteria.address}</a></li>
                    </ul>
                </div>
            </c:forEach>
        </div>
    </main>
</body>
</html>
