<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="javax.servlet.annotation.MultipartConfig"%>
<%@ page import="javax.servlet.http.Part"%>

<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 기본 페이지</title>
    <link rel="stylesheet" type="text/css" href="/static/css/ownerPage.css">
    <script src="/static/js/ownerPage_basic.js"></script>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 20px;
        }
        .container {
            display: flex;
            gap: 20px;
        }
        .info-section {
            flex: 1; /* 왼쪽 섹션이 더 넓게 차지하도록 설정 */
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .imageUpload {
            flex-basis: 300px; /* 오른쪽 섹션의 넓이 설정 */
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        .input-field {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .uploaded-image {
            width: 100%;
            height: auto;
            object-fit: cover;
            border-radius: 10px;
            margin-top: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
        }
        .tag-input-container {
            margin-top: 20px;
        }
        .button {
            padding: 10px 15px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <form method="post" id="ownerPage" action="/ownerPage">
        <div class="container">
            <!-- 왼쪽 정보 입력 섹션 -->
            <div class="info-section">
                <h2>기본 정보</h2>
             <input type="hidden" id="cafeOwner" name="cafeOwner" value="${cafeOwner}" />   
                <input type="text" id="cafeName" name="cafeName" placeholder="상호명 입력" class="input-field" />
                <input type="text" id="cafeExplain" name="cafeExplain" placeholder="가게 소개 입력" class="input-field" />
                <select class="input-field" name="cafeCategory">
                    <option value="">식당 카테고리 선택</option>
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.categoryNum}">${category.categoryName}</option>
                    </c:forEach>
                </select>
                <input type="text" id="cafePrice" name="cafePrice" placeholder="1인 기준 예상 금액 입력" class="input-field" />

                <div class="time-container">
                    <div class="time-item">
                        <label for="start-time">영업 시작 시간</label>
                        <select id="start-time" name="start-time" class="input-field">
                            <!-- 시간 옵션들 -->
                             <option value="06:00">06:00</option>
                            <option value="07:00">07:00</option>
                            <option value="08:00">08:00</option>
                            <option value="08:30">08:30</option>
                            <option value="09:00">09:00</option>
                            <option value="09:30">09:30</option>
                            <option value="10:00">10:00</option>
                            <option value="10:30">10:30</option>
                            <option value="11:00">11:00</option>
                            <option value="11:30">11:30</option>
                            <option value="12:00">12:00</option>
                            <option value="12:30">12:30</option>
                            <option value="13:00">13:00</option>
                            <option value="13:30">13:30</option>
                            <option value="15:00">15:00</option>
                            <option value="15:30">15:30</option>
                            <option value="16:00">16:00</option>
                            <option value="17:00">17:00</option>
                            <option value="17:30">17:30</option>
                            <option value="18:00">18:00</option>
                            <option value="19:00">19:00</option>
                            <option value="20:00">20:00</option>
                            <option value="21:00">21:00</option>
                            <option value="custom-start">직접 입력</option>
                        </select>
                        <input type="text" id="custom-start-time" name="custom-start-time" placeholder="영업 시작 시간 입력" class="input-field" style="display: none;" />
                    </div>

                    <div class="time-item">
                        <label for="end-time">영업 종료 시간</label>
                        <select id="end-time" name="end-time" class="input-field">
                            <option value="15:00">15:00</option>
                            <option value="15:30">15:30</option>
                            <option value="16:00">16:00</option>
                            <option value="17:00">17:00</option>
                            <option value="17:30">17:30</option>
                            <option value="18:00">18:00</option>
                            <option value="19:00">19:00</option>
                            <option value="20:00">20:00</option>
                            <option value="21:00">21:00</option>
                            <option value="22:00">22:00</option>
                            <option value="23:00">23:00</option>
                            <option value="24:00">24:00</option>
                            <option value="01:00">01:00</option>
                            <option value="02:00">02:00</option>
                            <option value="03:00">03:00</option>
                            <option value="04:00">04:00</option>
                            <option value="05:00">05:00</option>
                            <option value="custom-end">직접 입력</option>
                        </select>
                        <input type="text" id="custom-end-time" name="custom-end-time" placeholder="영업 종료 시간 입력" class="input-field" style="display: none;" />
                    </div>
                </div>

                <input type="text" id="cafePhoneNumber" name="cafePhoneNumber" placeholder="전화번호" class="input-field" />
                <input type="text" id="cafeAddress" name="cafeAddress" placeholder="주소" class="input-field" />
            </div>
            
            <input type="hidden" id="tagCount" name="tagCount" value="1"> <!-- 숨겨진 필드로 태그 카운트 저장 -->



            <!-- 오른쪽 이미지 업로드 섹션 -->
            <div class="imageUpload">
                <h2>대표 사진을 등록해 주세요.</h2>
                <img id="imagePreview" class="uploaded-image" name="menuInputpic" alt="이미지 미리보기" style="display: none;">
                <input type="file" id="cafePic" name="cafePic" accept="image/*" class="input-field" >
				<input type="hidden" id="cafePic64" name="cafePic64">

                <div class="tag-input-container">
                    <label for="tagInput">태그 입력</label>
                    <input type="text" id="tagInput-1" name="tagInput-1" placeholder="태그 입력" class="input-field">
                    <button type="button" id="addTagBtn" class="button">태그 추가</button>
                </div>
                <div id="tagContainer"></div>

                <script>
				    document.getElementById('cafePic').addEventListener('change', function(event) {
				        const file = event.target.files[0];
				        const reader = new FileReader();
				
				        reader.onload = function(e) {
				            const imgPreview = document.getElementById('imagePreview');
				            imgPreview.src = e.target.result;
				            imgPreview.style.display = 'block';
				        };
				
				        if (file && file.type.startsWith('image/')) {
				            reader.readAsDataURL(file);
				        }
				    });
				
				    let tagCount = 1; // 초기 태그 수
				    document.getElementById('addTagBtn').addEventListener('click', function() {
				        if (tagCount < 6) { // 최대 5개까지만 추가
				            const newTagInput = document.createElement('input');
				            newTagInput.type = 'text';
				            tagCount++; // 태그 수 증가
				            newTagInput.id = 'tagInput-' + tagCount; // 문자열 연결 사용
				            newTagInput.name = 'tagInput-' + tagCount; // 문자열 연결 사용
				            newTagInput.placeholder = '태그 입력';
				            newTagInput.classList.add('input-field');
				            document.getElementById('tagContainer').appendChild(newTagInput);
				            
				            // 숨겨진 필드에 현재 태그 수를 업데이트
				            document.getElementById('tagCount').value = tagCount;
				        } else {
				            alert('태그는 최대 5개까지만 추가 가능합니다.');
				        }
				    });
				</script>

            </div>
        </div>

        <button type="submit" class="button">카페 정보 저장</button>
    </form>
</body>
</html>
