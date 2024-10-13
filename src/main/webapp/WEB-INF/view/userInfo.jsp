<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&family=Roboto:wght@400;500&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/static/css/default.css">
<link rel="stylesheet" type="text/css" href="/static/css/userInfo.css">
<script src="/static/js/userInfo.js"></script>
</head>
<body>
	<!-- 상단 고정 바 -->
	<jsp:include page="/WEB-INF/module/header.jsp"></jsp:include>

	<!-- 왼쪽 고정 바 -->
	<jsp:include page="/WEB-INF/module/sidebar.jsp"></jsp:include>

	<main>
		<div class="user-info-container">
			<h2>회원님의 정보</h2>
			<ul>
				<li id="userID"><strong>아이디: </strong></li>
				<li id="userNickname"><strong>닉네임: </strong></li>
				<li id="userPhoneNumber"><strong>전화번호: </strong></li>
				<li id="userOwnerNumber" class="hidden"><strong>사업자
						번호: </strong></li>
			</ul>
			<div style="display:flex; justify-content: center">
				<button onclick="window.location='/userInfoChange'" class="button" style="">회원정보
					수정</button>
			</div>
		</div>
	</main>
</body>
</html>