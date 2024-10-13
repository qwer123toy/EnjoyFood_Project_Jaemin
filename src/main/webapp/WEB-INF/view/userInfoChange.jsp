<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&family=Roboto:wght@400;500&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/static/css/default.css">
<link rel="stylesheet" type="text/css"
	href="/static/css/userInfoChange.css">
<script src="/static/js/userInfoChange.js"></script>
</head>
<body>
	<!-- 상단 고정 바 -->
	<jsp:include page="/WEB-INF/module/header.jsp"></jsp:include>

	<!-- 왼쪽 고정 바 -->
	<jsp:include page="/WEB-INF/module/sidebar.jsp"></jsp:include>

	<main>
		<h2>회원 정보 수정</h2>
		<form id="changeForm">
			<div class="form-group">
				<label for="nickname">닉네임</label> <input type="text" id="nickname"
					name="nickname" required>
			</div>
			<div class="form-group">
				<label for="phone">전화번호</label> <input type="tel" id="phone"
					name="phone" required>
			</div>
			<div class="form-group">
				<label for="password">비밀번호</label> <input type="password"
					id="password" name="password" required>
			</div>
			<div class="form-group">
				<label for="confirmPassword">비밀번호 확인</label> <input type="password"
					id="confirmPassword" name="confirmPassword" required>
			</div>
			<div style="">
				<button type="submit" class="btn">수정하기</button>
			</div>
		</form>
	</main>

</body>
</html>