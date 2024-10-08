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
		<form id="loginForm">
			<label for="userID">아이디:</label> <input type="text" id="userID" name="userID" required> 
			<label for="userPW">비밀번호:</label> <input type="password" id="userPW" name="userPW" required>
			<p class="error hidden"></p>
			<button class="button" type="submit" id="login">로그인</button>
			<button class="button" type="button" id="signup">회원가입</button>
		</form>
	</main>
</body>
</html>