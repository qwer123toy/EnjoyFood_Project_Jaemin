<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="/static/css/default.css">
<link rel="stylesheet" type="text/css" href="/static/css/signup.css">
<script src="/static/js/signup.js" defer></script>
</head>
<body>
	<main class="main">
		<h1>회원가입</h1>
		<form id="signupForm">
			<div class="form-group">
				<label for="userID">아이디</label> <input type="text" id="userID"
					name="userID" required>
				<p class="error hidden" id="errorID">ID 중복</p>
			</div>
			<div class="form-group">
				<label for="userPW">비밀번호</label> <input type="password" id="userPW"
					name="userPW" required>
			</div>
			<div class="form-group">
				<label for="confirmPW">비밀번호 확인</label> <input type="password"
					id="confirmPW" name="confirmPW" required>
				<p class="error hidden" id="errorPW">비밀번호 다름</p>
			</div>
			<div class="form-group">
				<label for="userNickname">닉네임</label> <input type="text" id="userNickname"
					name="userNickname" required>
				<p class="error hidden" id="errorNickname">닉네임 중복</p>
			</div>
			<div class="form-group">
				<label for="userPhoneNumber">전화번호</label> <input type="text"
					id="userPhoneNumber" name="userPhoneNumber" required>
				<p class="error hidden" id="errorPhoneNumber">전화번호 중복</p>
			</div>
			<div class="form-group">
				<button type="button" id="businessOwnerBtn">사업주</button>
			</div>
			<div id="businessOwnerFields" class="hidden">
				<div class="form-group">
					<label for="userOwnerNumber">사업주 번호</label> <input type="text"
						id="userOwnerNumber" name="userOwnerNumber">
					<p class="error hidden" id="errorOwnerNumber">사업주 번호 중복</p>
				</div>
				<div class="form-group">
					<label for="userPicture">사업주 사진</label> <input type="file"
						id="userPicture" name="userPicture">
				</div>
			</div>
			<div class="form-group">
				<button type="submit">가입하기</button>
			</div>
		</form>
	</main>
</body>
</html>