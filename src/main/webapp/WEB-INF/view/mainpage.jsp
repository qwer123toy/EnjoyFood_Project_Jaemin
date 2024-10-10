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
    <link rel="stylesheet" type="text/css" href="/static/css/default.css">
    <link rel="stylesheet" type="text/css" href="/static/css/mainpage.css">
    <script src="/static/js/mainpage.js"></script>
</head>
<body>

    <!-- 상단 고정 바 -->
    <jsp:include page="/WEB-INF/module/header.jsp"></jsp:include>

    <!-- 왼쪽 고정 바 -->
   	<jsp:include page="/WEB-INF/module/sidebar.jsp"></jsp:include>

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
