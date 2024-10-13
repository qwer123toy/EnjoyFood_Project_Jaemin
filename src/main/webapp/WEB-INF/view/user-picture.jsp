<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사업자등록증 보기</title>
<link rel="icon" href="/static/ico/favicon.ico">
</head>
<body>
    <h1>사업자 등록증</h1>
    <c:if test="${not empty user.userPicture}">
        <img src="${user.userPicture}" alt="사업자 등록증" style="max-width: 500px; max-height: 500px;">
    </c:if>
    <c:if test="${empty user.userPicture}">
        <p>사업자 등록증이 없습니다.</p>
    </c:if>
    <form action="/admin/users" method="get">
        <button type="submit">회원 목록으로 돌아가기</button>
    </form>
</body>
</html>
