<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <link rel="stylesheet" type="text/css" href="/static/css/header.css">
</head>

<header>
    <div class="logo">
        <a href="mainpage">TASTE GPT</a>
    </div>
    <div class="search-container">
        <a href="mainpage" class="searchbtn">맛집 검색 🍽️</a>
        <a href="searchCategory">지역별 검색 🎮</a>
        <a href="searchCategory" class="btn-header">금액별 검색 🎮</a>
    </div>
    <div class="login">
        <c:choose>
            <c:when test="${not empty userID}">
                <span>${userID} 님, 환영합니다!</span>
                <!-- 내 정보 보기 버튼 -->
                <c:choose>
                    <c:when test="${userType == '0'}">
                        <form action="/admin/search">
                            <button class="btn">회원 관리</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="/userInfo">
                            <button class="btn">내 정보 보기</button>
                        </form>
                        <form action="/userSuggestion">
                            <button class="btn">건의하기</button>
                        </form>
                    </c:otherwise>
                </c:choose>
                <!-- 로그아웃 버튼 -->
                <form action="/mainpage" method="get" style="display: inline;">
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
