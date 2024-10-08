<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>메뉴 추가</title>
    <link rel="stylesheet" type="text/css" href="/static/css/addMenu.css">
</head>
<body>

	 <div class="info-section">
            <h2>메뉴 추가</h2>
    <div class="menu-container">
        <div class="column">
            <input type="text" placeholder="메뉴명 입력">
            <input type="text" placeholder="메뉴 금액 입력">
            <select>
                <option value="">카테고리 선택</option>
                <!-- 카테고리 옵션 추가 -->
            </select>
            <input type="file" placeholder="이미지">
        </div>
        <div class="column">
            <input type="text" placeholder="메뉴명 입력">
            <input type="text" placeholder="메뉴 금액 입력">
            <input type="text" placeholder="메뉴 설명 입력">
            
            <input type="file" placeholder="이미지">
        </div>
         <div class="column">
            <input type="text" placeholder="메뉴명 입력">
            <input type="text" placeholder="메뉴 금액 입력">
            <select>
                <option value="">카테고리 선택</option>
                <option value="한식">한식</option>
                <option value="중식">중식</option>
                <option value="일식">일식</option>
                <!-- 카테고리 옵션 추가 -->
            </select>
            <input type="file" placeholder="이미지">
        </div>
         <div class="column">
            <input type="text" placeholder="메뉴명 입력">
            <input type="text" placeholder="메뉴 금액 입력">
            <select>
                <option value="">카테고리 선택</option>
                <!-- 카테고리 옵션 추가 -->
            </select>
            <input type="file" placeholder="이미지">
              <div class="container">
        <h2>이미지 업로드</h2>
        <form id="uploadForm" enctype="multipart/form-data">
            <input type="file" id="fileInput" name="file" accept="image/*">
            <button type="button" onclick="uploadImage()">업로드</button>
        </form>
        <div class="image-display" id="imageDisplay"></div>
    </div>
    <script src="script.js"></script>
        </div>
        </div>
        <div class="buttons">
            <button>추가</button>
            <button>최종 저장</button>
        </div>
    </div>
</body>
</html>