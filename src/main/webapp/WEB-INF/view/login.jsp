<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 테스트</title>
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&family=Roboto:wght@400;500&display=swap"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/static/css/default.css">
<link rel="stylesheet" type="text/css" href="/static/css/login.css">
<script src="/static/js/login.js"></script>
</head>
<body>
	<!-- 상단 고정 바 -->
	<jsp:include page="/WEB-INF/module/header.jsp"></jsp:include>

	<!-- 왼쪽 고정 바 -->
	<jsp:include page="/WEB-INF/module/sidebar.jsp"></jsp:include>

	<main class="main">
		<h1>로그인</h1>
		<form id="loginForm">
			<label for="userID">아이디:</label>
			<input type="text" id="userID" name="userID" maxlength="20" required>

			<label for="userPW">비밀번호:</label>
			<input type="password" id="userPW" name="userPW" maxlength="20" required>

			<p class="error hidden"></p>

			<button class="button" type="submit" id="login">로그인</button>
			<button class="button" type="button" id="signup">회원가입</button>

			<div class="helplinks">
				<a href="/findId" class="findlink">아이디 찾기</a> | <a href="/findPw"
					class="findlink">비밀번호 찾기</a>
			</div>
		</form>
	</main>
</body>
</html>