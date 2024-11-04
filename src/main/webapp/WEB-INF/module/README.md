<div align="center">
<img src="https://github.com/user-attachments/assets/9a6fddc1-7c8f-4e4c-8ce0-e5cd36dbaee2">
</div>


<h1 align="center">
  페이지 별 view(모듈 부분)
</h1>

<p align="center"> Header 분을 모듈화한 페이지 </p>
<p align="center"> 상단 바를 모듈화하여 페이지 별로 나타냄  <br>
  
--- 

## Contents
<p align="left">목 차</p>
<p align="left">
  <a href="#JSP-설명">JSP 설명</a> <br>
  <a href="#주요-코드">주요 코드</a> 
</p>

---

## JSP 설명


| JSP명                | 설명                                                                                                                                                       |
|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **[heaer.jsp](heaer.jsp)**        |   ●  메인 페이지의 네비게이션 및 로그인 기능을 관리하며, 로그인 상태와 사용자 유형에 따라 다른 옵션을 표시     |
| **[sidebar.jsp](sidebar.jsp)**               |   ● 왼쪽 사이드 바 부분(미구현)    |

<br>

## 주요 코드
1. 가게 검색 코드(mainpage.jsp)
 - 서블릿에서 받아온 값을 통해 JSTL 문법을 이용하여 목록을 보여줌
 - 해당 가게를 클릭 시 cafedetail 페이지로 이동
     
```
<div class="logo">
        <a href="mainpage">TASTE GPT</a>
    </div>
    <div class="search-container">
        <a href="mainpage" class="searchbtn">맛집 검색 🍽️</a>
        <a href="searchArea">지역별 검색 🎮</a>
        <a href="searchCategory" class="btn-header">금액별 검색 🎮</a>
    </div>
    <div class="login">
        <c:choose>
            <c:when test="${not empty userID}">
                <span>${userID} 님, 환영합니다!</span>
                <!-- 내 정보 보기 버튼 -->
                <c:choose>
                    <c:when test="${userType == '0'}">
                        <form action="/admin/search">
                            <button class="btn">회원 관리</button>
                        </form>
                    </c:when>
                    <c:otherwise>
                        <form action="/userInfo">
                            <button class="btn">내 정보 보기</button>
                        </form>
                        <form action="/userSuggestion">
                            <button class="btn">건의하기</button>
                        </form>
                    </c:otherwise>
                </c:choose>
                <!-- 로그아웃 버튼 -->
                <form action="/mainpage" method="get" style="display: inline;">
                    <input type="hidden" name="action" value="logout">
                    <button class="btn">로그아웃</button>
                </form>
            </c:when>
            <c:otherwise>
                <!-- 로그인 버튼 -->
                <form action="/login" method="get">
                    <button class="btn">로그인</button>
                </form>
            </c:otherwise>
        </c:choose>
    </div>
```
<br>
