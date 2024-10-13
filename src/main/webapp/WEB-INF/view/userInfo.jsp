<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<link rel="icon" href="/static/ico/favicon.ico">
</head>
<body>
	<!-- 상단 고정 바 -->
	<jsp:include page="/WEB-INF/module/header.jsp"></jsp:include>

	<!-- 왼쪽 고정 바 -->
<%-- 	<jsp:include page="/WEB-INF/module/sidebar.jsp"></jsp:include> --%>

	<main>
		<div class="user-info-container">
			<form id="userInfoForm">
				<h2>회원님의 정보</h2>
				<ul>
					<li><strong>아이디: </strong><input type="text" id="userID"
						name="userID" readonly></li>
					<li><strong>닉네임: </strong><input type="text" id="userNickname"
						name="userNickname" required="required">
						<p class="error hidden" id="errorNickname">닉네임</p></li>
					<li><strong>전화번호: </strong><input type="text"
						id="userPhoneNumber" name="userPhoneNumber" required="required">
						<p class="error hidden" id="errorPhoneNumber">전화번호</p></li>
					<li class="hidden"><strong>사업자 번호: </strong><input type="text"
						id="userOwnerNumber" readonly></li>
				</ul>
				<div style="display: flex; justify-content: center">
					<button id="togglePW" class="button" type="button"
						onclick="toggleInputPW()">비밀번호 변경</button>
				</div>
				<ul id="inputPW" class="hidden">
					<li><strong>기존 비밀번호: </strong><input type="password"
						id="prevPW" name="prevPW" required></li>
					<li><strong>신규 비밀번호: </strong><input type="password"
						id="userPW" name="userPW"></li>
					<li><strong>비밀번호 확인: </strong><input type="password"
						id="confirmPW">
						<p class="error hidden" id="errorPW">비밀번호</p></li>
				</ul>
				<div style="display: flex; justify-content: center">
					<button class="button" type="submit" id="changeBtn">회원정보
						수정</button>
				</div>
			</form>
			<div style="display: flex; justify-content: center">
				<button id="ownerPageBtn" class="button hidden">가게정보 추가</button>
				<button id="addMenuBtn" class="button hidden">가게메뉴 추가</button>
			</div>
		</div>
	</main>
</body>
</html>