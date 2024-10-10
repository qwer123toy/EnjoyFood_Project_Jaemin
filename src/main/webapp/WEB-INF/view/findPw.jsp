<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link rel="stylesheet" type="text/css" href="/static/css/default.css">
<link rel="stylesheet" type="text/css" href="/static/css/findPw.css">
<script src="/static/js/findPw.js"></script>
</head>
<body>
	<main class="main">
		<h1 id="title">비밀번호 찾기</h1>
		<form id="findPwForm">
			<label for="userID">아이디:</label> <input type="text" id="userID"
				name="userID" placeholder="아이디 입력" maxlength="20" required>

			<label for="userPhoneNumber">전화번호:</label> <input type="text" id="userPhoneNumber"
				name="userPhoneNumber" placeholder="010-1234-5678" maxlength="13" required>
				
			<label for="userPW" class="hidden">비밀번호</label> <input type="password" class="hidden" id="userPW"
					name="userPW" maxlength="20">
					
			<label for="confirmPW" class="hidden">비밀번호 확인</label> <input type="password" class="hidden"
					id="confirmPW" name="confirmPW" maxlength="20">
					
			<p class="error hidden" id="result" ></p>

			<button class="button" type="submit" id="findPw">비밀번호 찾기</button>
			<button class="button hidden" type="submit" id="changePw">비밀번호 변경</button>
		</form>
	</main>
</body>
</html>