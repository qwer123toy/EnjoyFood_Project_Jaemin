<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원 관리</title>
    <link rel="stylesheet" type="text/css" href="/static/css/admin-users.css">
</head>
<body>
    <header>
        <h1>관리자 페이지 - 회원 관리</h1>
        <form action="/mainpage" class="header-button">
            <button type="submit">메인페이지로</button>
        </form>
    </header>

    <!-- 검색창 -->
    <div class="search-bar">
        <form method="post" action="/admin/search">
            <input type="text" name="userId" placeholder="회원 ID 검색">
            <button type="submit">검색</button>
        </form>
    </div>

    <!-- 회원 목록 -->
    <form method="post" action="/admin/search">
        <table class="user-table">
            <thead>
                <tr>
                    <th>회원 ID</th>
                    <th>전화번호</th>
                    <th>닉네임</th>
                    <th>회원 구분</th>
                    <th>활성화 상태
                    <button type="submit">변경</button> <!-- 일괄 변경 버튼 -->
                    </th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${userList}">
                    <tr>
                        <td>${user.userID}</td>
                        <td>${user.userPhoneNumber}</td>
                        <td>${user.userNickname}</td>
                        <td>
                            <c:choose>
                                <c:when test="${user.userType == 1}">사업주</c:when>
                                <c:when test="${user.userType == 2}">일반회원</c:when>
                                <c:otherwise>관리자</c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <select name="active_${user.userID}">
                                <option value="1" <c:if test="${user.active == 1}">selected</c:if>>활성화</option>
                                <option value="0" <c:if test="${user.active == 0}">selected</c:if>>비활성화</option>
                            </select>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        
    </form>
</body>
</html>
