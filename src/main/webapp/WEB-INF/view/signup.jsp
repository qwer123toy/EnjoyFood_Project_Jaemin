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
	<div class="container">
		<h1>회원가입</h1>
		<form id="signupForm">
			<div class="form-group">
				<label for="userID">아이디</label> <input type="text" id="userID"
					name="userID" required>
			</div>
			<div class="form-group">
				<label for="userPW">비밀번호</label> <input type="password" id="userPW"
					name="userPW" required>
			</div>
			<div class="form-group">
				<label for="confirmPW">비밀번호 확인</label> <input type="password"
					id="confirmPW" name="confirmPW" required>
			</div>
			<div class="form-group">
				<label for="nickname">닉네임</label> <input type="text" id="nickname"
					name="nickname" required>
			</div>
			<div class="form-group">
				<label for="phoneNumber">전화번호</label> <input type="text"
					id="phoneNumber" name="phoneNumber" required>
			</div>
			<div class="form-group">
				<button type="button" id="businessOwnerBtn">사업주</button>
			</div>
			<div id="businessOwnerFields" class="hidden">
				<div class="form-group">
					<label for="ownerNumber">사업주 번호</label> <input type="text"
						id="ownerNumber" name="ownerNumber">
				</div>
				<div class="form-group">
					<label for="ownerPicture">사업주 사진</label> <input type="file"
						id="ownerPicture" name="ownerPicture">
				</div>
			</div>
			<div class="form-group">
				<button type="submit">가입하기</button>
			</div>
		</form>
	</div>
</body>
</html>