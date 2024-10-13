<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&family=Roboto:wght@400;500&display=swap" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/static/css/default.css">
<link rel="stylesheet" type="text/css" href="/static/css/findId.css">
<script src="/static/js/findId.js"></script>
<link rel="icon" href="/static/ico/favicon.ico">
</head>
<body>
 	<!-- 상단 고정 바 -->
    <jsp:include page="/WEB-INF/module/header.jsp"></jsp:include>
    
	<!-- 왼쪽 고정 바 -->
<%--    	<jsp:include page="/WEB-INF/module/sidebar.jsp"></jsp:include> --%>
   	
	<main class="main">
		<h1>아이디 찾기</h1>
		<form id="findIdForm">
			<label for="userPhoneNumber">전화번호:</label> <input type="text"
				id="userPhoneNumber" name="userPhoneNumber"
				placeholder="010-1234-5678" maxlength="13" required>
			<p class="hidden" id="result"></p>
			<button class="button" type="submit" id="findId">아이디 찾기</button>
		</form>
	</main>
</body>
</html>