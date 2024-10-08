<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>건의사항 화면</title>
    <link rel="stylesheet" type="text/css" href="/static/css/suggestions.css">
</head>
<body><!-- 상단 고정 바 -->
    <header>
        <a href="mainpage" class="main-link">TASTE GPT</a>
        <div class="search-container">
            <a href="searchCategory" class="searchbtn">맛집 검색 🍽️</a>
            <a href="searchCategory">지역별 검색 🎮</a>
            <a href="searchCategory" class="btn-header">유형별 검색 🎮</a>
            
        </div>
        <div class="login-container">
	    <c:choose>
	        <c:when test="${not empty user}">
	            <span class="user-name">${user.name}</span>
	        </c:when>
	        <c:otherwise>
	            <form action="/login" method="get">
	                <button class="btn">로그인</button>
	            </form>
	        </c:otherwise>
	    </c:choose>
</div>
    </header>


    <!-- 건의사항 폼 -->
    <section id="suggestion-form">
        <h2>건의 사항 화면</h2>

        <!-- 제목 입력 -->
        <label for="title">제목</label>
        <input type="text" id="title" name="title" placeholder="제목을 입력하세요" required />

        <!-- 내용 입력 -->
        <label for="content">내용</label>
        <textarea id="content" name="content" placeholder="요청이 필요한 내용을 작성해 주세요." rows="10" required></textarea>

        <!-- 문의하기 버튼 -->
        <button type="submit" id="submit-btn">문의 하기</button>
    </section>

    <!-- 스크롤 및 스타일 조정 -->
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8d6e8;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

		        
		header {
		    position: fixed;
		    top: 0;
		    width: 100%;
		    background-color: white; /* 상단바의 배경을 흰색으로 */
		    padding: 15px;
		    display: flex;
		    justify-content: space-between;
		    align-items: center;
		    z-index: 1000;
		    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
		}
		
		header .logo {
		    font-size: 24px;
		    color: #ff6f61;
		    font-weight: bold;
		}
		
		header a {
		    text-decoration: none !important; /* 밑줄 제거 */
		    color: #3c82d9 !important; /* 기본 색상 설정 */
		    font-weight: bold !important; /* 굵은 글씨 */
		    transition: color 0.3s ease, box-shadow 0.3s ease; /* 전환 효과 추가 */
		    position: relative !important; /* 하단 애니메이션을 위해 상대 위치 지정 */
		}
		
		header a::after {
		    content: ''; /* 하단에 가상의 요소 추가 */
		    position: absolute;
		    width: 0;
		    height: 2px;
		    bottom: -5px;
		    left: 0;
		    background-color: #3c82d9; /* 하단 밑줄 색상 */
		    transition: width 0.3s ease; /* 밑줄 애니메이션 */
		}
		
		header a:hover::after {
		    width: 100%; /* 호버 시 하단 밑줄이 확대 */
		}
		
		header a:hover {
		    color: #ff3a85; /* 호버 시 색상 변경 */
		    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); /* 살짝 그림자 효과 추가 */
		}

        .main-link {
            
            font-size: 1.2rem;
           
        }
        
		        .login-btn {
		    background-color: #ff3a85;
		    color: white;
		    border: none;
		    border-radius: 20px;
		    padding: 10px 20px;
		    font-size: 16px;
		    cursor: pointer;
		    transition: background-color 0.3s ease, transform 0.2s ease;
		}

		.login-btn:hover {
		    background-color: #e63370;
		    transform: scale(1.05);
		}
		
		.login-container {
		    display: flex;
		    justify-content: flex-end;
		    align-items: center;
		}
		
		.user-name {
		    font-size: 18px;
		    font-weight: bold;
		    color: #3c82d9;
		    margin-right: 20px;
		}
        

        #suggestion-form {
            margin-top: 150px;
            width: 80%;
            max-width: 800px;
            background-color: #ffe0f3;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        h2 {
            color: #3c82d9;
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-size: 1.2rem;
            color: #3c82d9;
            margin-top: 20px;
        }

        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            border-radius: 8px;
            border: 2px solid #ddd;
            font-size: 1rem;
            outline: none;
        }

        textarea {
            resize: none;
        }

        #submit-btn {
            margin-top: 20px;
            padding: 12px 25px;
            background-color: #3c82d9;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 1.2rem;
            cursor: pointer;
        }

        #submit-btn:hover {
            background-color: #355ea9;
        }
    </style>
</body>
</html>

