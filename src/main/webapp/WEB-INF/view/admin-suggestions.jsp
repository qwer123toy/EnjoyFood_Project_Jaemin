<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>건의 사항</title>
<link rel="stylesheet" type="text/css"
	href="/static/css/admin-suggestions.css">
<link rel="icon" href="/static/ico/favicon.ico">
</head>
<body>
	<header>
		<h1>관리자 페이지 - 건의 사항 관리</h1>
		<form action="/admin/search" class="header-button">
			<button type="submit" id="user-button">회원관리</button>
		</form>

		<form action="/mainpage" class="header-button">
			<button type="submit" id="mainpage-button">메인페이지</button>
		</form>
	</header>

	<table class="suggestions">
		<thead>
			<tr>
				<th>번호</th>
				<th>회원 ID</th>
				<th>내용</th>
				<th>작성 시간</th>
				<th>처리 상태
					<button type="submit">변경</button>
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="suggestion" items="${listSuggestion}">
				<tr>
					<td>${suggestion.suggestionId}</td>
					<td>${suggestion.userId}</td>
					<td>${suggestion.suggestion}</td>
					<td>${suggestion.uploadTime}</td>
				
				
					<td><select name="active_${suggestion.chkProcess}">
								<option value="1" <c:if test="${user.active == 1}">selected</c:if>>처리중</option>
								<option value="0" <c:if test="${user.active == 0}">selected</c:if>>완료</option>
						</select></td>
				
				
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>