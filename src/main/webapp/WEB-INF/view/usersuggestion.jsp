<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>건의사항 화면</title>
    <link rel="stylesheet" type="text/css" href="/static/css/usersuggestion.css">
   
</head>
<body><!-- 상단 고정 바 -->
    <header>
        <a href="mainpage" class="main-link">TASTE GPT</a>
        <div class="search-container">
            <a href="searchCategory" class="searchbtn">맛집 검색 🍽️</a>
            <a href="searchCategory">지역별 검색 🎮</a>
            <a href="searchCategory" class="btn-header">유형별 검색 🎮</a>
            
        </div>
        <div class="login">
    <c:choose>
        <c:when test="${not empty userID}">
            <span>${userID} 님, 환영합니다!</span>
            <!-- 내 정보 보기 버튼 -->
            <button class="btn" onclick="#">내 정보 보기</button>
            <form action="/usersuggestion">
            	<button class="btn">건의하기</button>
            </form>
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


    <!-- 건의사항 폼 -->
    <section id="suggestion-form">
        <h2>건의 사항 화면</h2>

        <!-- 제목 입력 -->
        <label for="title">제목</label>
        <input type="text" id="title" name="title" placeholder="제목을 입력하세요" required />

        <!-- 내용 입력 -->
        <label for="content">내용</label>
        <textarea id="content" name="content" placeholder="요청이 필요한 내용을 작성해 주세요." rows="10" required></textarea>

        <!-- 문의하기 버튼 -->
        <button type="submit" id="submit-btn">문의 하기</button>
    </section>


</body>
</html>

