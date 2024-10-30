
<div align="center">
<img src="https://github.com/user-attachments/assets/f55a3a05-7254-4463-9fc1-21ece90625b6">

</div>
<h1 align="center">
  MainController
</h1>
<p align="center">맛집 등록, 검색/정보 관리/관리자 페이지 서블릿 </p>
<p align="center">회원의 맛집 등록과 정보 저장, 검색, 관리자 페이지 등의 <br>
  데이터를 관리하고 DB에 저장하여 사용자에게 보여주는 서블릿</p>

--- 

## Contents

<p align="center">목 차</p>
<p align="center">
  <a href="#클래스-설명">클래스 설명</a> |
  <a href="#주요-코드">주요 코드</a> |
  <a href="#how-to-use--development-setup">How To Use</a> |
  <a href="#authors">Authors</a>
</p>

---

## 클래스 설명

| 클래스명                | 설명                                                                                                                                                       |
|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **[MainServlet.java](MainServlet.java)**                  |  - 초기화면 페이지 및 각 페이지 별 서블릿 연결<br> - 애플리케이션 전체 설정 관리                                |
| **[SearchCateServlet.java](SearchCateServlet.java)**                 |    - 사용자와 카페의 정보를 초기화하고, 카테고리와 가격 조건에 맞는 카페 리스트를 검색하여 JSP 페이지로 전달<br> - 검색된 카페에 대한 사진 및 태그 정보를 병합하여 결과를 제공                                     |
| **[searchAreaServlet.java](searchAreaServlet.java)**                 |    - 요청된 지역에 맞는 카페 목록을 검색하고, 해당 카페와 관련된 이미지 정보를 병합하여 JSON 형식으로 응답 <br> - 응답 상태 코드를 설정하여 성공 또는 실패를 클라이언트에 전달                                   |
| **[CafeDetailsServlet.java](CafeDetailsServlet.java)**     |    -  특정 카페의 세부 정보를 조회하여, 가게 정보, 메뉴 목록, 리뷰, 평균 평점, 카테고리, 태그 및 이미지를 JSP에 전달 <br> -  사용자 세션에서 정보를 가져와 사용자의 타입을 설정하며, 예외 발생 시 에러 페이지로 포워딩                          |
| **[OwnerPage_basic.java](OwnerPage_basic.java)**           |    - 가게 소유자가 자신의 페이지에서 가게 정보를 입력하고 전송<br> - GET 요청으로 카테고리 목록과 소유자 ID를 제공<br> - POST 요청에서는 JSON 데이터를 읽고 가게 정보, 태그, 이미지를 데이터베이스에 저장                                                 |
| **[AddMenuServlet.java](AddMenuServlet.java)**      |    - 유저로부터 메뉴 데이터를 받아 카페 번호를 확인한 뒤, 해당 카페에 여러 메뉴를 등록하는 작업을 처리<br> - 메뉴 데이터는 JSON 형식으로 전달되며, 각 메뉴 정보는 별도로 추출 및 삽입<br> -  메뉴 삽입 작업은 데이터베이스에 저장      |
| **[AdminServlet.java](AdminServlet.java)**        |    - 모든 사용자 리스트를 가져와 admin-users.jsp에 전달<br> - 사용자가 입력한 ID로 검색하여 사용자 활성화 상태를 업데이트한 후, 최신 사용자 리스트를 admin-users.jsp로 전달     |
| **[CafeReviewServlet.java](CafeReviewServlet.java)**    |    - 사용자 세션을 확인하여 특정 카페의 메뉴 및 리뷰 정보를 조회하고, 로그인한 사용자만 리뷰를 작성할 수 있도록 처리<br> - doGet 메서드는 메뉴 리스트와 사용자 정보를 조회<br> - doPost 메서드는 리뷰 데이터를 데이터베이스에 저장하는 기능을 수행               |
| **[SuggestionServlet.java](SuggestionServlet.java)**               |    - 사용자 제안 페이지로 이동하고 제안 내용을 처리<br> - doGet 메서드는 건의사항 페이지로 포워딩<br> - doPost 메서드는 로그인한 사용자의 건의사항을 데이터베이스에 저장하고, 성공 여부에 따라 메인 페이지 또는 건의사항 페이지로 리다이렉트                                             |
| **[UserPictureServlet.java](UserPictureServlet.java)**               |    - 사용자 ID로 검색된 사용자의 프로필 이미지를 가져와 반환<br> - doGet 메서드는 요청된 사용자의 Base64 인코딩된 이미지를 디코딩하여 JPEG 형식으로 출력 스트림에 전달    |
<br>

## 주요 코드

1. ㄹ

2. ㅁㄴㅇ

3. ㅁㄴㅇ

4. ㅁㄴㅇ

5. ㅁㄴㅇ


6. ㅁㄴㅇ

## Key Features

- 핵심 기능 서술하기 위해 텍스트, 하이퍼링크와 스크린샷, 도표 등을 첨부해주세요.

> [!TIP]
> 헤딩, 단락, 링크 및 도표, 목록, 인용 코드, 체크박스 작성법 등을 알아보세요
> 
> [Markdown 기분 문법 알아보기](https://www.markdownguide.org/basic-syntax/)
>
> [Markdown Github 확장 문법 알아보기](https://docs.github.com/ko/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax)

> 도표 생성 문법이 어렵다면 생성기를 활용하세요 => [MD Table Generator](https://www.tablesgenerator.com/markdown_tables)




## ToDo

-   로드맵 서술. 체크 박스 등 활용

*   [x] Add Changelog
*   [x] Add back to top links
*   [ ] Multi-language Support
    -   [ ] English

## FAQ

<details>
  <summary>자주 묻는 질문?</summary>
  <dl>
  <dt>질문 1</dt>
  <dd>답변 1</dd>
  </dl>
</details>
    
