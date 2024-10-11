<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>건의 사항 관리</title>
<link rel="stylesheet" type="text/css" href="/static/css/admin-suggestions.css">
</head>
<body>
	<header>
		<h1>건의 사항 관리</h1>
		<form action="/mainpage" class="header-button">
			<button type="submit">메인페이지로</button>
		</form>
	</header>

	<table class="suggestions">
		<thead>
			<tr>
				<th>번호</th>
				<th>회원 ID</th>
				<th>내용</th>
				<th>작성 시간</th>
				<th>처리 여부</th>
			</tr>
		</thead>
</body>
</html>