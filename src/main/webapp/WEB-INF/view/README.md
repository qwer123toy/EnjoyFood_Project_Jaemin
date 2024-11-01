<div align="center">
<img src="https://github.com/user-attachments/assets/0c84ea28-53da-4ac1-8a08-460fb5b2c187">
</div>

<h1 align="center">
  페이지 별 view
</h1>

<p align="center">각 기능들을 적용한 view 페이지 </p>
<p align="center"> 맛집 리스트, 맛집 검색, 회원가입, 리뷰 남기기 등의  <br>
  모든 페이지들을 보여주는 폴더 </p>
  
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
| **[mainpage.jsp](mainpage.jsp)**        |   ● 검색된 맛집 목록을 화면에 출력하는 페이지 <br> - 사용자가 입력한 검색어에 따른 맛집 결과를 표시 <br> - 검색된 맛집은 가게 사진, 이름, 평균 금액 등을 포함한 형태    |
| **[searchCategory.jsp](searchCategory.jsp)**    |    ● 맛집을 다양한 조건으로 검색하고 결과를 표시하는 페이지 <br> -  금액, 인원, 카테고리 등을 선택하여 맞춤 검색을 수행      |
| **[searchArea.jsp](searchArea.jsp)**               |   ● 지역별 맛집을 선택해 검색하고 결과를 표시하는 페이지 <br> - 사용자는 지역과 구를 선택해 맞춤형 맛집 검색     |
| **[cafeDetails.jsp](cafeDetails.jsp)**               |   ● 카페 정보와 메뉴, 리뷰를 JSTL을 사용해 동적으로 표시하며, 지도와 리뷰 작성 기능을 제공 <br> -  가게명, 주소, 전화번호, 평균 금액, 평점 등의 기본 정보와 카페 이미지 및 메뉴 리스트를 표시하며, 지도 API로 위치를 시각화 <br> - 고객 리뷰를 보여주고, 리뷰 작성 버튼을 통해 리뷰 작성 페이지로 이동 가능    |
| **[cafeReview.jsp](cafeReview.jsp)**               |  ● 카페 리뷰를 작성하고 메뉴별 금액을 합산하여 총 이용 금액을 표시할 수 있는 페이지 <br> - 사용자가 이미지 업로드, 별점 선택, 메뉴 체크박스 선택 등으로 카페 리뷰를 작성할 수 있도록 구성   |
| **[login.jsp](login.jsp)**               |   ● 사용자 로그인을 위한 ID와 비밀번호 입력 폼을 제공하는 페이지 <br> - 사용자 ID와 비밀번호 입력, 로그인 및 회원가입 버튼을 제공     |
| **[signup.jsp](signup.jsp)**               |   ● 사용자 회원가입을 위한 ID, 비밀번호, 닉네임, 전화번호 입력 폼과 사업자 등록 옵션을 포함한 페이지 <br> - 사용자 ID, 비밀번호, 닉네임, 전화번호 입력 필드와 '사업자 등록' 옵션을 제공     |
| **[findId.jsp](findId.jsp)**               |   ●  전화번호를 통해 사용자의 아이디를 찾을 수 있는 페이지  |
| **[findPw.jsp](findPw.jsp)**               |   ● 전화번호와 아이디를 입력해 비밀번호를 찾고, 필요한 경우 변경할 수 있는 페이지  |
| **[userInfo.jsp](userInfo.jsp)**               |   ● 회원 정보를 수정하고 비밀번호를 변경할 수 있는 페이지 <br> - 아이디, 닉네임, 전화번호를 수정하고, 원할 경우 비밀번호를 변경할 수 있는 기능을 제공하는 JSP 페이지 <br> - 가게 정보와 메뉴를 추가하는 버튼도 보유(사업자만)    |
| **[user-picture.jsp](user-picture.jsp)**               |   ● 사업자 등록증을 보여주는 페이지로, 등록증이 없을 경우 안내 메시지를 표시 |
| **[userSuggestion.jsp](userSuggestion.jsp)**               |   ● 사용자 건의사항을 제출하는 JSP 페이지로, 제목과 내용을 입력받는 폼을 포함  |
| **[ownerPage.jsp](ownerPage.jsp)**               | 카페 소유자가 카페의 기본 정보(상호명, 설명, 카테고리, 운영 시간 및 이미지 업로드)를 입력할 수 있도록 하는 페이지   |
| **[addMenu.jsp](addMenu.jsp)**               |   ● 카페 메뉴를 추가하기 위한 페이지으로, 메뉴명, 가격, 설명, 및 이미지를 업로드할 수 있는 기능을 제공     |
| **[admin-users.jsp](admin-users.jsp)**               |   ● 회원 관리 기능을 제공하며, 회원 정보를 검색하고 목록 표시 <br> - 회원 목록에서 전화번호, 닉네임, 회원 구분, 활성화 상태를 확인하고 수정 가능 <br> - 사업자등록증 이미지를 확인 가능    |
| **[admin-suggestions.jsp](admin-suggestions.jsp)**               |   ● 관리자가 회원의 건의 사항을 관리할 수 있는 인터페이스를 제공하며, 각 건의 사항의 처리 상태 변경 가능    |

<br>

## 주요 코드
1. 가게 검색 코드(mainpage.jsp)
 - 서블릿에서 받아온 값을 통해 JSTL 문법을 이용하여 목록을 보여줌
 - 해당 가게를 클릭 시 cafedetail 페이지로 이동
     
```
<!-- 가게 리스트 -->
<!-- 데이터베이스에서 검색 결과를 불러와서 JSTL로 반복 출력 -->
<c:choose>
    <!-- mergedList가 비어있는 경우 -->
    <c:when test="${empty mergedList}">
        <p style="text-align: center; width: 100%">검색 결과가 없습니다</p> <!-- 검색 결과가 없음을 알림 -->
    </c:when>
    <c:otherwise>
        <p style="text-align: center;">검색 결과: ${fn:length(mergedList)}건</p> <!-- 검색 결과 개수를 표시 -->
    </c:otherwise>
</c:choose>

<div class="store-list">
    <!-- mergedList의 각 카페 정보를 반복 출력 -->
    <c:forEach var="cafeteriaWithPic" items="${mergedList}">
        <div class="store-item"
            onclick="location.href='cafeteria?cafeNum=${cafeteriaWithPic.cafeteria.cafeNum}&cafeName=${cafeteriaWithPic.cafeteria.cafeName}'"> <!-- 클릭 시 해당 카페 상세 페이지로 이동 -->
            
            <!-- 카페 사진을 반복 출력 -->
            <c:forEach var="pic" items="${cafeteriaWithPic.cafePics}">
                <img src="${pic.cafePic}" alt="Cafeteria Picture"
                    style="width: 230px; height: 150px;"> <!-- 카페 사진 표시 -->
            </c:forEach>
            
            <ul>
                <li class="a">${cafeteriaWithPic.cafeteria.cafeName}</li> <!-- 카페 이름 -->
                <li>평균 금액: ${cafeteriaWithPic.cafeteria.cafePrice} 원</li> <!-- 평균 금액 -->
                <li>전화번호: ${cafeteriaWithPic.cafeteria.cafePhoneNumber}</li> <!-- 전화번호 -->
                <li class="a">주소: ${cafeteriaWithPic.cafeteria.cafeAddress}</li> <!-- 주소 -->
                <li>
                    <c:choose>
                        <c:when test="${not empty cafeTagsMap[cafeteriaWithPic.cafeteria.cafeNum]}">
                            <!-- 해당 카페의 태그가 있을 경우 반복 출력 -->
                            <c:forEach var="tag" items="${cafeTagsMap[cafeteriaWithPic.cafeteria.cafeNum]}">
                                # ${tag.cafeTag} <!-- 태그 표시 -->
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            없음 <!-- 태그가 없을 경우 '없음' 표시 -->
                        </c:otherwise>
                    </c:choose>
                </li>
            </ul>
        </div>
    </c:forEach>
</div>

```
<br>

2. 가게 정보 조회 코드(cafeDetails.jsp)
 - cafeteria 정보를 통해 리스트 출력
 - 카카오 맵 API를 활용하여 DB에 저장된 주소를 지도로 표기
 - 해당 가게에 해당하는 리뷰 리스트 출력
 
```
 <!-- 메인 컨텐츠 -->
    <main>
        <section class="store-info">
            <!-- 가게 제목 -->
            <h1 class="store-title">${cafeteria.cafeName}</h1>

            <!-- 메뉴 이미지 슬라이더 -->
            <div class="menu-images-wrapper">
                <div class="menu-images">
                    <!-- 메뉴 이미지 반복 출력 -->
                    <c:forEach var="menu" items="${menuList}">
                        <div class="menu-item">
                            <img src="${menu.menuNamepic }" style="width: 200px; height: 200px;">
                        </div>
                    </c:forEach>
                </div>
                <button class="btn-more-images">이미지 더보기 →</button> <!-- 더보기 버튼 -->
            </div>

            <!-- 가게 정보 -->
            <div class="store-infoDetail">
                <div class="store-details">
                    <div class="store-image" style="width: 300px; height: 300px; margin-right: 100px;">
                        <img src="${cafePic}" alt="카페 사진" style="max-width: 300px; max-height: 300px;">
                    </div>
                    <p style="margin-top: -12px">
                        <em class="link"> 
                            <a href="javascript:void(0);" onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')"> </a>
                        </em>
                    </p>
                    <div id="store-map" style="width: 300px; height: 300px; margin-right: 100px"></div>

                    <!-- 카카오 맵 API를 사용하여 지도 표시 -->
                    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=------------------------------"></script>
                    <script>
                        // 지도와 마커 설정
                        var mapContainer = document.getElementById('store-map'), // 지도를 표시할 div 
                        mapOption = {
                            center : new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                            level : 3 // 지도의 확대 레벨
                        };

                        // 지도를 생성합니다    
                        var map = new kakao.maps.Map(mapContainer, mapOption);

                        // 주소-좌표 변환 객체를 생성합니다
                        var geocoder = new kakao.maps.services.Geocoder();

                        // 주소로 좌표를 검색합니다
                        geocoder.addressSearch(address, function(result, status) {
                            // 정상적으로 검색이 완료됐으면 
                            if (status === kakao.maps.services.Status.OK) {
                                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

                                // 결과값으로 받은 위치를 마커로 표시합니다
                                var marker = new kakao.maps.Marker({
                                    map: map,
                                    position: coords
                                });

                                // 인포윈도우로 장소에 대한 설명을 표시합니다
                                var infowindow = new kakao.maps.InfoWindow({
                                    content: '<div style="width:150px;text-align:center;padding:6px 0;">' + cafeName + '</div>'
                                });
                                infowindow.open(map, marker);

                                // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                                map.setCenter(coords);
                            }
                        });
                    </script>
                    
                    <!-- 가게 정보 리스트 -->
                    <ul class="store-info-list" style="width: 300px; height: 300px; margin-right: 100px; text-align: center; display: flex; flex-direction: column; justify-content: center; align-items: center;">
                        <li>상호명: <strong>${cafeteria.cafeName}</strong></li> <!-- 상호명 -->
                        <li>평균 금액: <strong>${cafeteria.cafePrice} 원</strong></li> <!-- 평균 금액 -->
                        <c:if test="${not empty customerPaymentAvg}">
                            <li>실제 이용자 평균 금액: <strong>${customerPaymentAvg} 원</strong></li> <!-- 실제 평균 금액 -->
                        </c:if>
                        <li>전화번호: <strong>${cafeteria.cafePhoneNumber}</strong></li> <!-- 전화번호 -->
                        <li>주소: <strong>${cafeteria.cafeAddress}</strong></li> <!-- 주소 -->
                        <li id="score">평점: <strong>${score}</strong>
                            <div class="star-rating">
                                <div class="star-rating-filled" style="width: calc(${score} * 20%)"></div> <!-- 평점 표시 -->
                            </div>
                        </li>
                    </ul>
                </div>
            </div>

            <!-- 간단한 맛집 소개 -->
            <div class="store-description">
                <h2>간단한 맛집 소개</h2>
                <p>이곳은 맛집에 대한 간단한 설명을 보여줍니다.</p>
                <p>상 호 명 : ${cafeteria.cafeName}</p> <!-- 상호명 -->
                <p>영업 시간 : ${cafeteria.cafeOpenTime}</p> <!-- 영업 시간 -->
                <p>가게 유형 :
                    <c:forEach var="category" items="${cafeCategoryList}">
                        ${category.categoryName} 
                    </c:forEach>
                </p>
                <p>가게 태그 :
                    <c:forEach var="cafetag" items="${cafeTagList}">
                        ${cafetag.cafeTag}
                    </c:forEach>
                </p>
            </div>

            <!-- 간단한 메뉴 소개 -->
            <div class="store-description">
                <h2>메뉴</h2>
                <c:forEach var="menu" items="${menuList}">
                    <img src="${menu.menuNamepic }" style="width: 150px; height: 150px"> <!-- 메뉴 사진 -->
                    <p>메뉴명 : ${menu.menuName}</p> <!-- 메뉴명 -->
                    <p>가격 : ${menu.menuPrice}</p> <!-- 가격 -->
                    <p>메뉴 소개 : ${menu.menuExplain }</p> <!-- 메뉴 설명 -->
                </c:forEach>
            </div>

        </section>

        <!-- 추가된 리뷰 섹션 -->
        <section class="review-section">
            <h2>Review</h2>
            <div class="review-list">
                <c:forEach var="review" items="${cafeReviewList}">
                    <div class="review-item" style="margin-right: 50px;">
                        <div class="review-image">
                            <c:if test="${not empty review.userPic}">
                                <img src="${review.userPic}" alt="" style="width: 250px; height: 120px;" /> <!-- 사용자 프로필 사진 -->
                            </c:if>
                        </div>
                        <p>
                            <strong>평점: ${review.cafeScore} </strong> <!-- 리뷰 평점 -->
                            <div class="star-rating">
                                <div class="star-rating-filled" style="width: calc(${review.cafeScore} * 20%)"></div> <!-- 평점 표시 -->
                            </div>
                        </p>
                        <p>
                            <strong>리뷰 내용:</strong> ${review.cafeComment} <!-- 리뷰 내용 -->
                        </p>
                        <p>
                            <strong>메뉴별 금액:</strong> ${review.userPayment} 원 <!-- 메뉴별 금액 -->
                        </p>
                    </div>
                </c:forEach>
            </div>
            <form action="/cafeReview" method="get">
                <input type="hidden" name="cafeNum" value="${cafeteria.cafeNum}"> <!-- 카페 번호 전송 -->
                <button class="btn-review">리뷰 작성하기</button> <!-- 리뷰 작성 버튼 -->
            </form>
        </section>

    </main>
```

<br>

3. 회원 가입 코드(signup.jsp)
 -  폼의 데이터를 입력받아 POST하여 회원가입 처리

```
<main class="main">
		<h1>회원가입</h1> <!-- 페이지 제목 -->
		<form id="signupForm"> <!-- 회원가입 폼 -->
			<div class="form-group"> <!-- 아이디 입력 그룹 -->
				<label for="userID">아이디</label> <!-- 아이디 레이블 -->
				<input type="text" id="userID" name="userID" maxlength="20" required> <!-- 아이디 입력 필드 -->
				<p class="error hidden" id="errorID">ID</p> <!-- 아이디 오류 메시지 -->
			</div>
			<div class="form-group"> <!-- 비밀번호 입력 그룹 -->
				<label for="userPW">비밀번호</label> <!-- 비밀번호 레이블 -->
				<input type="password" id="userPW" name="userPW" maxlength="20" required> <!-- 비밀번호 입력 필드 -->
			</div>
			<div class="form-group"> <!-- 비밀번호 확인 그룹 -->
				<label for="confirmPW">비밀번호 확인</label> <!-- 비밀번호 확인 레이블 -->
				<input type="password" id="confirmPW" name="confirmPW" maxlength="20" required> <!-- 비밀번호 확인 입력 필드 -->
				<p class="error hidden" id="errorPW">비밀번호</p> <!-- 비밀번호 확인 오류 메시지 -->
			</div>
			<div class="form-group"> <!-- 닉네임 입력 그룹 -->
				<label for="userNickname">닉네임</label> <!-- 닉네임 레이블 -->
				<input type="text" id="userNickname" name="userNickname" maxlength="20" required> <!-- 닉네임 입력 필드 -->
				<p class="error hidden" id="errorNickname">닉네임</p> <!-- 닉네임 오류 메시지 -->
			</div>
			<div class="form-group"> <!-- 전화번호 입력 그룹 -->
				<label for="userPhoneNumber">전화번호</label> <!-- 전화번호 레이블 -->
				<input type="text" id="userPhoneNumber" name="userPhoneNumber" placeholder="010-1234-5678" maxlength="13" required> <!-- 전화번호 입력 필드 -->
				<p class="error hidden" id="errorPhoneNumber">전화번호</p> <!-- 전화번호 오류 메시지 -->
			</div>
			<div class="form-group"> <!-- 사업자 등록 버튼 -->
				<button type="button" id="businessOwnerBtn">사업자 등록</button> <!-- 사업자 등록 버튼 -->
			</div>
			<div id="businessOwnerFields" class="hidden"> <!-- 사업자 정보 입력 그룹 (초기에는 숨김) -->
				<div class="form-group"> <!-- 사업자 번호 입력 그룹 -->
					<label for="userOwnerNumber">사업자 번호</label> <!-- 사업자 번호 레이블 -->
					<input type="text" id="userOwnerNumber" name="userOwnerNumber" placeholder="012-34-56789" maxlength="12"> <!-- 사업자 번호 입력 필드 -->
					<p class="error hidden" id="errorOwnerNumber">사업자 번호</p> <!-- 사업자 번호 오류 메시지 -->
				</div>
				<div class="form-group"> <!-- 사업자 등록증 사진 업로드 그룹 -->
					<label for="inputPicture">사업자 등록증 사진</label> <!-- 사업자 등록증 사진 레이블 -->
					<input type="file" id="inputPicture" name="inputPicture" accept=".jpeg, .jpg, .png"> <!-- 파일 입력 필드 -->
					<p class="error hidden" id="errorPicture">사업자 등록증</p> <!-- 사업자 등록증 오류 메시지 -->
					<input type="hidden" id="userPicture" name="userPicture"> <!-- 숨겨진 입력 필드 (사진 경로 저장용) -->
				</div>
			</div>
			<div class="form-group"> <!-- 가입하기 버튼 -->
				<button type="submit" id="signupBtn">가입하기</button> <!-- 회원가입 제출 버튼 -->
			</div>
		</form>
	</main>
```
<br>

4. 회원 목록 보기 코드(admin-users.jsp)
 - DB를 통해 회원가입된 사용자들의 정보를 볼 수 있음
 - 특정 회원을 찾기 위한 검색 가능
   
```
   <!-- 검색창 -->
    <div class="search-bar">
        <form method="post" action="/admin/search">
            <input type="text" name="userId" placeholder="회원 ID 검색"> <!-- 회원 ID 검색 입력 필드 -->
            <button type="submit">검색</button> <!-- 검색 버튼 -->
        </form>
    </div>

    <!-- 회원 목록 -->
    <form method="post" action="/admin/search">
        <table class="user-table"> <!-- 사용자 정보를 표시할 테이블 -->
            <thead>
                <tr>
                    <th>회원 ID</th>
                    <th>전화번호</th>
                    <th>닉네임</th>
                    <th>회원 구분</th>
                    <th>활성화 상태
                        <button type="submit">변경</button> <!-- 일괄 변경 버튼 -->
                    </th>
                    <th>사업자등록증</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${userList}"> <!-- 사용자 목록 반복 -->
                    <tr>
                        <td>${user.userID}</td> <!-- 회원 ID -->
                        <td>${user.userPhoneNumber}</td> <!-- 전화번호 -->
                        <td>${user.userNickname}</td> <!-- 닉네임 -->
                        <td>
                            <c:choose> <!-- 회원 구분 -->
                                <c:when test="${user.userType == 1}">사업주</c:when> <!-- 사업주 -->
                                <c:when test="${user.userType == 2}">일반회원</c:when> <!-- 일반회원 -->
                                <c:otherwise>관리자</c:otherwise> <!-- 관리자 -->
                            </c:choose>
                        </td>
                        <td>
                            <select name="active_${user.userID}"> <!-- 활성화 상태 선택 -->
                                <option value="1" <c:if test="${user.active == 1}">selected</c:if>>활성화</option> <!-- 활성화 선택 -->
                                <option value="0" <c:if test="${user.active == 0}">selected</c:if>>비활성화</option> <!-- 비활성화 선택 -->
                            </select>
                        </td>
                        <td>
                            <c:choose> <!-- 사업자 등록증 여부 확인 -->
                                <c:when test="${user.userPicture == null}">없음</c:when> <!-- 사진 없음 -->
                                <c:otherwise>
                                    <a href="/admin/userPicture?userId=${user.userID}" target="_blank"> <!-- 사업자 등록증 보기 링크 -->
                                        <button type="button">보기</button> <!-- 보기 버튼 -->
                                    </a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </form>
```

<br>

