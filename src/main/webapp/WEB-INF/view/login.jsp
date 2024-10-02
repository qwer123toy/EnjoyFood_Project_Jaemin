<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 테스트</title>
<link rel="stylesheet" type="text/css" href="/static/css/login.css">
<link rel="stylesheet" type="text/css" href="/static/css/default.css">
<script src="/static/js/login.js"></script>
</head>
<body>
	<main class="main">
		<h1>로그인</h1>
		<form id="loginForm" action="#" method="post">
			<label for="username">아이디:</label> <input type="text" id="username"
				name="username" required> <label for="password">비밀번호:</label>
			<input type="password" id="password" name="password" required>
<!-- 			<div> -->
				<button type="submit">로그인</button>
				<button type="button">회원가입</button>
<!-- 			</div> -->
		</form>
	</main>
</body>
</html>