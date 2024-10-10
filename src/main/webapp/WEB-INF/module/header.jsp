<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
    <link rel="stylesheet" type="text/css" href="/static/css/header.css">
</head>

<header>
	<div class="logo">
		<a href="mainpage">TASTE GPT</a>
	</div>
	<div class="search-container">
		<a href="mainpage" class="searchbtn">맛집 검색 🍽️</a> <a
			href="searchCategory">지역별 검색 🎮</a> <a href="searchCategory"
			class="btn-header">유형별 검색 🎮</a>

	</div>
	<div class="login">
		<!-- 로그인 버튼 클릭 시 login.jsp로 이동 -->
		<form action="/login" method="get">
			<button class="btn">로그인</button>
		</form>
	</div>
</header>