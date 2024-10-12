<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>지역 검색 페이지</title>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&family=Roboto:wght@400;500&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/static/css/default.css">
<link rel="stylesheet" type="text/css" href="/static/css/searchArea.css">
<script src="/static/js/searchArea.js"></script>
</head>
<body>
	<!-- 상단 고정 바 -->
	<jsp:include page="/WEB-INF/module/header.jsp"></jsp:include>

	<!-- 왼쪽 고정 바 -->
	<jsp:include page="/WEB-INF/module/sidebar.jsp"></jsp:include>

	<div class="container">
		<h1>지역 검색</h1>

		<h2>지역 선택</h2>
		<div id="cities">
			<label class="radio-label"><input type="radio" name="region"
				value="서울" onclick="showDistricts()"> 서울</label> <label
				class="radio-label"><input type="radio" name="region"
				value="부산" onclick="showDistricts()"> 부산</label> <label
				class="radio-label"><input type="radio" name="region"
				value="인천" onclick="showDistricts()"> 인천</label> <label
				class="radio-label"><input type="radio" name="region"
				value="대구" onclick="showDistricts()"> 대구</label> <label
				class="radio-label"><input type="radio" name="region"
				value="광주" onclick="showDistricts()"> 광주</label> <label
				class="radio-label"><input type="radio" name="region"
				value="대전" onclick="showDistricts()"> 대전</label> <label
				class="radio-label"><input type="radio" name="region"
				value="울산" onclick="showDistricts()"> 울산</label> <label
				class="radio-label"><input type="radio" name="region"
				value="세종" onclick="showDistricts()"> 세종</label> <label
				class="radio-label"><input type="radio" name="region"
				value="경기" onclick="showDistricts()"> 경기</label> <label
				class="radio-label"><input type="radio" name="region"
				value="강원" onclick="showDistricts()"> 강원</label> <label
				class="radio-label"><input type="radio" name="region"
				value="충북" onclick="showDistricts()"> 충북</label> <label
				class="radio-label"><input type="radio" name="region"
				value="충남" onclick="showDistricts()"> 충남</label> <label
				class="radio-label"><input type="radio" name="region"
				value="전북" onclick="showDistricts()"> 전북</label> <label
				class="radio-label"><input type="radio" name="region"
				value="전남" onclick="showDistricts()"> 전남</label> <label
				class="radio-label"><input type="radio" name="region"
				value="경북" onclick="showDistricts()"> 경북</label> <label
				class="radio-label"><input type="radio" name="region"
				value="경남" onclick="showDistricts()"> 경남</label> <label
				class="radio-label"><input type="radio" name="region"
				value="제주" onclick="showDistricts()"> 제주</label>
		</div>

		<div id="districts" class="hidden"></div>

		<input id="selected" type="hidden">
		<div id="selectedList"></div>

		<button class="search-button" onclick="performSearch()">검색</button>
	</div>
</body>
</html>