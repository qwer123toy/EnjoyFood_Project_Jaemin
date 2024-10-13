<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>지역 검색 페이지</title>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&family=Roboto:wght@400;500&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/static/css/default.css">
<link rel="stylesheet" type="text/css" href="/static/css/searchArea.css">
</head>
<body>
	<!-- 상단 고정 바 -->
	<jsp:include page="/WEB-INF/module/header.jsp"></jsp:include>

	<!-- 왼쪽 고정 바 -->
	<jsp:include page="/WEB-INF/module/sidebar.jsp"></jsp:include>
	<main>
		<h1>지역별 검색</h1>
		<div class="container">
			<div class="button-group">
				<div id="regionButtons"></div>
			</div>

			<div class="district-group">
				<div id="districtButtons"></div>
			</div>
		</div>

		<div class="vertical">
			<div id="selectedList"></div>
		</div>
		<div style="display: flex; justify-content: center;">
			<button id="clearAllButton">전체 취소</button>
			<button type="submit" id="searchButton">검색</button>
		</div>
		<div class="result">
			<p id="searchResult" style="text-align: center; width: 100%">지역을 선택해주세요</p>
			<div class="store-list"></div>
		</div>


	</main>

	<template id="template">
		<div class="store-item">
			<!--  <div class="store-image">이미지 넣을거임!</div> -->
			<ul>
				<li class="a"></li>
				<li></li>
				<li></li>
				<li class="a"></li>
			</ul>
		</div>
	</template>
</body>
<script src="/static/js/searchArea.js"></script>
</html>
