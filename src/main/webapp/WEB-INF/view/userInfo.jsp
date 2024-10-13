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
			<form>
				<h2>회원님의 정보</h2>
				<ul>
					<li><strong>아이디: </strong><input type="text" id="userID"
						disabled="disabled"></li>
					<li><strong>닉네임: </strong><input type="text" id="userNickname">
						<p class="error hidden" id="errorNickname">닉네임</p></li>
					<li><strong>전화번호: </strong><input type="text"
						id="userPhoneNumber">
						<p class="error hidden" id="errorPhoneNumber">전화번호</p></li>
					<li class="hidden"><strong>사업자 번호: </strong><input type="text"
						id="userOwnerNumber" disabled="disabled"></li>
				</ul>
				<div style="display: flex; justify-content: center">
					<button class="button" type="button" onclick="toggleInputPW()">비밀번호
						수정</button>
				</div>
				<ul id="inputPW" class="hidden">
					<li><strong>기존 비밀번호: </strong><input type="password"
						id="prevPW"></li>
					<li><strong>신규 비밀번호: </strong><input type="password"
						id="userPW"></li>
					<li><strong>비밀번호 확인: </strong><input type="password"
						id="confirmPW">
						<p class="error hidden" id="errorPW">비밀번호</p></li>
				</ul>
				<div style="display: flex; justify-content: center">
					<button class="button" type="button">회원정보 수정</button>
				</div>
			</form>
		</div>
	</main>
</body>
</html>