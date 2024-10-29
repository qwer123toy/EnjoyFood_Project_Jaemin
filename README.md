
<div align="center">
<img src="https://github.com/user-attachments/assets/4f7ef988-02be-4410-acf4-1d1dd6c8423b">
</div>


<h1 align="center">
  맛GPT
</h1>
<p align="center">맛집 소개 사이트</p>
<p align="center">기본적인 맛집 소개 사이트의 기능과 <br>
  예산에 따라 맛집을 조회할 수 있는 기능을 제공하여 <br>
  사용자가 예산 내에서 최적의 맛집을 찾을 수 있는 사이트</p>

---

## Contents

<p align="left">목차</p>
<p align="left">
  <a href="#what-is">What is?</a>  <br>
  <a href="#key-features">Key Features</a> <br>
  <a href="#how-to-use--development-setup">How To Use</a> <br>
  <a href="#repository-structure">Repository Structure</a> <br>
  <a href="#authors">Authors</a>
</p>
<br>

---

## 기능 설명
[메인 서블릿](src/main/java/main/controller)
 - 초기화면 페이지 및 각 페이지 별 서블릿 연결
 - 애플리케이션 전체 설정 관리
   
[유저 관리 서블릿](src/main/java/user)
 - 유저 관리 페이지
 - 회원가입과 예외처리, 유효성 확인
 - 로그인 및 일반회원, 사업주, 관리자 구분
 - 건의사항 작성 및 관리
 - 사용자 인증 및 세션 관리

[지도 서블릿](src/main/java/enjoyfood)
 - 지도 페이지
 - 카카오 맵 API를 사용하기 위한 맵 관리 페이지

[가게관리 서블릿](src/main/java/cafeteria)
 - 가게 정보 등록
 - 가게의 메뉴 관리 및 리뷰 확인
 - 리뷰에 따른 평점 변경

[페이지 화면 구성](src/main/webapp/WEB-INF/views)

 - 페이지별 구성된 화면 모음
   
[페이지 화면 구성(모듈)](src/main/webapp/WEB-INF/module)
 - 가게 페이지 부분 중 모듈화 된 부분
 - heaer와 footer를 통해 

[페이지 화면 관리](src/main/webapp/static)
 - 페이지 별 css, js 관리
 - 
<a href="src/main/webapp/static">
  <img src="https://flat.badgen.net/badge/%EB%A7%81%ED%81%AC/%EC%9D%B4%EB%8F%99/">
</a>

## What is

<h3>1. 개요 및 목적</h3>

  • 개요 
   - 맛집 정보를 제공하는 웹사이트로, 기존 맛집 소개 사이트와 유사한 기능을 제공하면서도 차별화된 기능을 추가하고자 함

  • 목적
   - 주요 목표는 예산에 따라 맛집을 조회할 수 있는 기능을 제공하여 사용자가 예산 내에서 최적의 맛집을 찾을 수 있도록 돕는 것
   - 예를 들어, 커플이 5만 원의 예산으로 데이트를 하고 싶을 때, 1인당 평균 25,000원의 비용이 드는 맛집을 검색할 수 있는 기능을 구현

<h3>2. 프로젝트 배경 및 적용 기술</h3>

  • 핵심 가치와 비즈니스적 영향 
   - 예산별 검색 기능은 기존 맛집 사이트에서 제공하지 않는 독창적인 기능으로, 사용자 경험을 향상시키고 사용자 맞춤형 검색 결과를 제공함으로써 차별화된 가치를 제공
     
  • 사용자 및 사용 환경
   - 사용자는 연령, 취향, 예산에 따라 다를 수 있으며, 주로 데이트나 가족 모임 등 특정 상황에 맞는 맛집 검색이 필요
     
  • 기존 시스템의 문제점 및 새로운 시스템의 차별점
   - 기존 맛집 사이트는 지역별, 음식 종류별 검색에 중점을 두었지만, 예산에 맞는 추천 기능이 잘 없어, 사용자의 예산 범위 내에서 최적의 맛집을 추천하는 기능을 추가

  • JSP와 서블릿을 사용하여 동적인 웹 페이지를 구현하고, 카카오 맵 API를 활용하여 지도를 연동하였으며, Git을 통해 협업 및 버전 관리 수행
  
## Key Features
- 페이지 별로 핵심 기능을 기술해두었으며, 여기서는 이 프로그램의 전체적인 클래스를 설명

| 핵심 기능                | 설명                                                                                                                                                       |
|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Cafeteria**                  | 가게 부분의 기능과 DB연결을 처리<br> - Mapper와 ServiceImpl 클래스를 통해 MySQL과 연결하여 DB와 연동 및 SQL문을 처리하고 가게 정보들을 불러오거나 저장                                |
| **User**                 | 회원가입과 로그인 등의 유저 관리<br> - 기본적으로 DB와 연동하여 처리되며, JSON으로 값을 불러온 뒤 UserValidator를 통해 유효성 검사를 진행하고 모두 통과했을 때 가입을 완료                                     |
| **UserController**                 | Filter 클래스를 통해 로그인과 로그아웃 상태를 확인하여 처리<br> - session을 확인하여 로그인 상태를 확인하고 특정 페이지의 경우 로그인이 되어있는지 확인하여 안되어있을 경우 로그인 페이지로 이동하도록 구현                                      |
| **Map**     | 카카오 맵 API를 활용하여 지도 데이터 구현<br> - API를 활용하여 DB에 저장된 주소를 불러왔을 때 해당 주소가 가리키는 실제 주소를 보여주고 체커로 해당 가게 이름을 표시                           |
| **SearchCateServlet**           | 사용자의 예산과 원하는 태그에 맞는 가게 검색<br>  - 사용자와 카페의 정보를 초기화하고, 카테고리와 가격 조건에 맞는 카페 리스트를 검색하여 카페에 대한 사진 및 태그 정보를 병합한 뒤 결과를 제공                                                  |
| **SearchAreaServlet**      | 지역별로 찾고 싶은 가게 리스트 출력<br> - 요청된 지역에 맞는 카페 목록을 검색하고, 해당 카페와 관련된 이미지 정보를 병합하여 JSON 형식으로 응답한 뒤 상태 코드를 설정하여 성공 또는 실패를 사용자에게 전달       |
| **CafeDetailServlet**        | 선택한 가게의 세부 정보를 사용자에게 보여줌<br> - 특정 카페의 세부 정보를 조회하여, 가게 정보, 메뉴 목록, 리뷰, 평균 평점, 카테고리, 태그 및 이미지를 보여주며, 예외 발생 시 에러 페이지로 포워딩                     |
| **OwnerPageServlet**    | 사업주로 등록된 사용자가 가게 정보를 등록하고 DB에 저장 <br> - 페이지에서 가게 정보를 입력하고 전송. GET 요청으로 카테고리 목록과 소유자 ID를 제공하며, POST 요청에서는 JSON 데이터를 읽고 가게 정보, 태그, 이미지를 데이터베이스에 저장                |
| **AdminServlet**               | 관리자가 가입된 사용자들의 정보와 사업자등록증 등 확인 및 제재 가능 <br> DB에서 사용자 리스트를 조회하여 가져오며, 사업주의 경우 사업자등록증 확인 가능. 아이디별로 활성화/비활성화 가능                                                        |

## How To Use / Development setup

* 사용 환경과 사용법 설명
* 또는 개발을 위한 개발환경 구축 설명하기

> To clone and run this application,
> you'll need [Git](https://git-scm.com)
> and [download Maven](https://maven.apache.org/download.cgi)
> Maven is a Java tool, so you must have Java installed in order to proceed. Set the JAVA_HOME environment variable pointing to your JDK installation or have the java executable on your PATH.
>
> From your command line:

```bash
# Clone this repository
$ git clone https://github.com/username/app-repository

# Go into the repository
$ cd app-repository

# Install dependencies
$ maven package

# Run the app
$ java -cp target/my-app-1.0-SNAPSHOT.jar com.mycompany.app.App
```

> **Note** > [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or use OpenJDK.

## Repository Structure

```sh
└──EnjoyFood_Project/
  ├─README.md
  ├─.gitignore
  ├─ pom.xml/
  ├─ src/
  │  ├─ main/
  │  │  ├─ java/
  │  │  │  ├─ main/
  │  │  │  │  ├─ controller/
  │  │  │  ├─ cafeteria/
  │  │  │  ├─ config/
  │  │  │  ├─ enjoyfood/
  │  │  │  ├─ user/
  │  │  │  │  ├─ controller/
  │  │  │  │  ├─ model/
  │  │  │  │  ├─ suggestion/
  │  │  ├─ webapp/
  │  │  │  ├─ META-INF/
  │  │  │  ├─ popup/
  │  │  │  ├─ static/
  │  │  │  │  ├─ css/
  │  │  │  │  ├─ ico/
  │  │  │  │  ├─ js/
  │  │  │  ├─ WEB-INF/
  │  │  │  │  ├─ module/
  │  │  │  │  ├─ view/
  ├─ java/
  │  │  │  ├─ main/
  │  │  │  │  ├─ controller/
  │  │  │  ├─ cafeteria/
  │  │  │  ├─ config/
  │  │  │  ├─ enjoyfood/
  │  │  │  ├─ user/
  │  │  │  │  ├─ controller/
  │  │  │  │  ├─ model/
  │  │  │  │  ├─ suggestion/
  ├─ webapp/
  │  │  │  ├─ META-INF/
  │  │  │  ├─ popup/
  │  │  │  ├─ static/
  │  │  │  │  ├─ css/
  │  │  │  │  ├─ ico/
  │  │  │  │  ├─ js/
  │  │  │  ├─ WEB-INF/
  │  │  │  │  ├─ module/
  │  │  │  │  ├─ view/

```

<p align="center">
  <h2>Built With</h2>
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white">
  <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white">


## Download

* 릴리즈 링크 있으면 첨부 가능
* [download]() the latest installable version of for Windows, macOS and Linux.

## ToDo

-   로드맵 서술. 체크 박스 등 활용

*   [x] Add Changelog
*   [x] Add back to top links
*   [ ] Multi-language Support
    -   [ ] English
  
## Authors
> 프로필 
>
> 이재민 [@깃허브 프로필 페이지](https://github.com/qwer123toy)
> 

