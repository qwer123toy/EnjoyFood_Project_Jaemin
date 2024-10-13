<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>리뷰 작성</title>
<link rel="stylesheet" type="text/css" href="/static/css/cafeReview.css">
<link rel="icon" href="/static/ico/favicon.ico">
</head>
<body>
	<!-- 상단 고정 바 -->
	<jsp:include page="/WEB-INF/module/header.jsp"></jsp:include>

	<!-- 왼쪽 고정 바 -->
<%-- 	<jsp:include page="/WEB-INF/module/sidebar.jsp"></jsp:include> --%>


	<!-- 메인 컨텐츠 -->
	<main>
		<section class="review-section">
			<form id="reviewForm">
				<h1 class="review-title">리뷰 작성 화면</h1>
				
				<div class="review-container">
					<input type="hidden" id="cafeNum" name="cafeNum" value="${param.cafeNum}">
					<!-- 이미지 업로드 -->
					<div class="review-image">
						<label for="imageUpload">이미지 추가</label>
						<input type="file" id="imageUpload" name="imageUpload" accept="image/*">
						<!-- 이미지 미리보기 -->
						<img id="imagePreview" src="#" alt="이미지 미리보기" style="display: none; max-width: 200px; margin-top: 10px;">
						<input type="hidden" id="userPic" name="userPic">
					</div>

					<!-- 평점 부분 -->
					<div class="review-inputs">
						<label for="rating">평점:</label>
						<div class="star-rating">
							<span class="star" data-value="5">&#9733;</span>
							<span class="star" data-value="4">&#9733;</span>
							<span class="star" data-value="3">&#9733;</span>
							<span class="star" data-value="2">&#9733;</span>
							<span class="star" data-value="1">&#9733;</span>
						</div>
						<!-- 실제 평점 값 -->
						<input type="hidden" id="rating" name="cafeScore" value="0">
						<div class="rating-value">0.0</div>

						<label for="reviewContent">리뷰 내용:</label>
						<textarea id="reviewContent" name="cafeComment" placeholder="리뷰 내용을 작성해주세요" rows="5"></textarea>

						<!--  
					<label for="menu">이용 메뉴:</label>
					<c:forEach var="menu" items="${list}">
						<li><input type="checkbox" id="checkbox-${menu}" onclick="toggleInput('${menu}')"> <label for="checkbox-${menu}">${menu}</label> <input type="number" id="input-${menu}" style="display: none;"></li>
					</c:forEach>
					<label for="price">이용 금액:</label>
					<input type="number" id="price" name="price" placeholder="이용 금액 입력">
-->
						<label for="menu">이용 메뉴:</label>
						<ul>
							<c:forEach var="menu" items="${list}">
								<li><input type="checkbox" id="checkbox-${menu.menuName}" data-price="${menu.menuPrice}" onclick="toggleInput('${menu.menuName}')"> <label for="checkbox-${menu.menuName}">${menu.menuName} - ${menu.menuPrice}원</label> <input type="number" id="input-${menu.menuName}" style="display: none;" oninput="calculateTotal()"></li>
							</c:forEach>
						</ul>
						<label for="price">이용 금액:</label>
						<input type="number" id="price" name="userPayment" placeholder="이용 금액 입력" readonly>
					</div>
				</div>
				<!-- 작성 완료 버튼 -->
				<button class="btn-submit" id="reviewBtn">작성 완료</button>
			</form>
		</section>
	</main>

</body>
<script src="/static/js/cafeReview.js"></script>
</html>
