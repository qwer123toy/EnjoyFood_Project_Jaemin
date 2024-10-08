<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="javax.servlet.annotation.MultipartConfig" %>
<%@ page import="javax.servlet.http.Part" %>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>관리자 기본 페이지</title>
<link rel="stylesheet" type="text/css" href="/static/css/ownerPage.css"> <!-- CSS 파일 경로 설정 -->
</head>
<body>
     <div class="container">
        <!-- 왼쪽 정보 입력 섹션 -->
        <div class="info-section">
            <h2>기본 정보</h2>
            <!-- 상호명 입력 -->
            <input type="text" placeholder="상호명 입력" class="input-field" />
             <!-- 가게 소개 입력 -->
            <input type="text" placeholder="가게 소개 입력" class="input-field" />
			
			<!-- 카테고리 입력 -->
			<div class="category">
            <select class="input-field">
                <option value="">식당 카테고리 선택</option>
                <option value="한식">한식</option>
                <option value="중식">중식</option>
                <option value="일식">일식</option>
                <option value="양식">양식</option>
                <option value="주점">주점</option>
                <option value="퓨전">퓨전</option>
              
            </select>
            
        </div>
        
            <!-- 예상 금액 입력 -->
            <input type="text" placeholder="1인 기준 예상 금액 입력" class="input-field" />

           <!-- 영업 시작 및 종료 시간 -->
<div class="time-container">
  <div class="time-item">
    <label for="start-time">영업 시작 시간</label>
    <select id="start-time" class="input-field">
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
    <input type="text" id="custom-start-time" placeholder="영업 시작 시간 입력" class="input-field" style="display:none;" />
  </div>

  <div class="time-item">
    <label for="end-time">영업 종료 시간</label>
    <select id="end-time" class="input-field">
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
    <input type="text" id="custom-end-time" placeholder="영업 종료 시간 입력" class="input-field" style="display:none;" />
  </div>
</div>

<script>
  const startTime = document.getElementById('start-time');
  const customStartTime = document.getElementById('custom-start-time');
  const endTime = document.getElementById('end-time');
  const customEndTime = document.getElementById('custom-end-time');

  startTime.addEventListener('change', function() {
    if (this.value === 'custom-start') {
      customStartTime.style.display = 'block';
    } else {
      customStartTime.style.display = 'none';
    }
  });

  endTime.addEventListener('change', function() {
    if (this.value === 'custom-end') {
      customEndTime.style.display = 'block';
    } else {
      customEndTime.style.display = 'none';
    }
  });
</script>

<style>
  .time-container {
    display: flex; /* Flexbox로 가로 정렬 */
    gap: 20px; /* 아이템 간의 간격 */
  }

  .time-item {
    display: flex;
    flex-direction: column; /* 레이블과 콤보박스는 세로로 정렬 */
    width: 150px; /* 각 항목의 너비 */
  }

  .input-field {
    width: 100%; /* 입력 필드 너비를 100%로 설정 */
    padding: 10px;
    font-size: 16px;
    margin-top: 5px; /* 레이블과 입력 필드 간의 간격 */
  }
</style>

            <!-- 전화번호 입력 -->
<input type="text" id="phone-number" placeholder="전화번호" class="input-field" />

<script>
  const phoneNumberInput = document.getElementById('phone-number');

  phoneNumberInput.addEventListener('input', function (e) {
    // 입력된 값에서 하이픈을 제거
    const input = e.target.value.replace(/-/g, '');
    
    // 하이픈을 추가하는 정규 표현식
    const formattedNumber = input.replace(/(\d{3})(\d{3,4})(\d{4})/, '$1-$2-$3');
    
    // 입력 필드에 포맷된 전화번호 설정
    e.target.value = formattedNumber;
  });
</script>

            <!-- 주소 입력 -->
            <input type="text" placeholder="주소" class="input-field" />
        </div>

    <h2>이미지 업로드</h2>
    <form action="imageUpload.jsp" method="post" enctype="multipart/form-data">
        <input type="file" name="image" accept="image/*" required />
        <input type="submit" value="업로드" />
    </form>

    <div class="image-display">
        <h3>업로드한 이미지</h3>
        <%
            // 이미지 파일을 업로드한 후 보여주기
            if (request.getMethod().equalsIgnoreCase("POST")) {
                // part를 받아오기 위해 MultipartConfig를 추가
                Part imagePart = request.getPart("image");
                String fileName = imagePart.getSubmittedFileName();
                String uploadPath = application.getRealPath("/") + "uploads/" + fileName;

                // 이미지 저장
                imagePart.write(uploadPath);
                String imageUrl = "uploads/" + fileName; // 웹에서 접근 가능한 경로

                // 이미지 출력
                out.println("<img src='" + imageUrl + "' alt='Uploaded Image' class='uploaded-image' />");
            }
        %>
    </div>

           <!-- 태그 입력 -->
            <div class="tag-input-container">
                <label for="tagInput">태그 입력</label>
                <input type="text" id="tagInput" name="tagInput" placeholder="태그 입력 (쉼표로 구분)">
                <button type="submit">태그 추가</button>
          
            </div>
     
    </div>
        <!-- 다음 버튼 -->
        <div class="next-button">
            <button>→</button>
        </div>
    </div>
</body>
</html>