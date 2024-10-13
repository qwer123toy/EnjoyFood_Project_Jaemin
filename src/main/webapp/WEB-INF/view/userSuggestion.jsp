<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>건의사항 화면</title>
<link rel="stylesheet" type="text/css"
	href="/static/css/usersuggestion.css">
<link rel="icon" href="/static/ico/favicon.ico">
</head>
<script src="/static/js/userSuggestion.js"></script>
<body>
	<!-- 상단 고정 바 -->
	<jsp:include page="/WEB-INF/module/header.jsp"></jsp:include>

	<!-- 왼쪽 고정 바 -->
	<%-- 	<jsp:include page="/WEB-INF/module/sidebar.jsp"></jsp:include> --%>

	<!-- 건의사항 폼 -->
	<form id="suggestionForm">
		<h2>건의 사항 화면</h2>

		<!-- 제목 입력 -->
		<label for="title">제목</label> <input type="text" id="title"
			name="title" placeholder="제목을 입력하세요" required />

		<!-- 내용 입력 -->
		<label for="suggestion">내용</label>
		<textarea id="suggestion" name="suggestion"
			placeholder="요청이 필요한 내용을 작성해 주세요." rows="10" required></textarea>

		<!-- 문의하기 버튼 -->
		<button type="submit" id="submitBtn">문의 하기</button>
	</form>

</body>
</html>

