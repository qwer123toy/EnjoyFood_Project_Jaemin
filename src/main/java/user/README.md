
<div align="center">
<img src="https://github.com/user-attachments/assets/dce7da6e-db12-48b1-882a-9907f629260b">


</div>
<h1 align="center">
  User
</h1>
<p align="center">유저 등록, 정보 유효성, DB 관리, 건의 사항 등을 다루는 클래스 </p>
<p align="center">회원 정보 등록과 정보 수정, 관련 SQL문, 유효성 검사 및 비밀번호 제한, 건의사항 등의 <br>
  데이터를 관리하고 DB에 저장하여 사용자에게 보여주는 서블릿</p>

--- 

## Contents

<p align="left">목 차</p>
<p align="left">
  <a href="#클래스-설명">클래스 설명</a> <br>
  <a href="#주요-코드">주요 코드</a> 
</p>

---

## 클래스 설명

### Controller

| 클래스명                | 설명                                                                                                                                                       |
|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **[LoginFilter.java](controller/LoginFilter.java)**                  |  ● 사용자가 로그인되어 있는지 확인하는 필터 <br> -  사용자가 로그인되지 않은 경우 로그인 페이지로 리다이렉트                                |
| **[MainFilter.java](controller/MainFilter.java)**                 |    ● 특정 페이지 접근 시 로그인 상태를 확인하는 필터를 설정 <br> - 검색된 카페에 대한 사진 및 태그 정보를 병합하여 결과를 제공                       |
| **[UserAPI.java](controller/UserAPI.java)**                 |    ● 사용자 관련 요청(로그인, 회원가입, 중복 검사 등)을 처리하는 서블릿 <br> - 사용자 조회, 로그인, 회원가입, 정보 변경 등의 다양한 API 요청을 처리 <br> - 중복 검사와 정보 검색, 비밀번호 변경 같은 세부 기능을 개별 메서드로 나눠 효율적으로 처리                                   |

<br>

### Model

| 클래스명                | 설명                                                                                                                                                       |
|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **[User.java](model/User.java)**     |    ● 유저 데이터를 선언하는 클래스 <br> -  DB와 동일한 필드값을 보유한 유저 클래스                          |
| **[UserMapper.java](model/UserMapper.java)**           |    ● 사용자 데이터베이스에 접근하여 CRUD와 관련된 다양한 쿼리를 실행하는 인터페이스 <br> -  MyBatis를 사용하여 사용자 정보 조회, 생성, 수정, 삭제 작업을 수행하는 메서드를 정의 <br> - 각 메서드는 특정 SQL 쿼리를 실행하여 User 객체를 반환하거나 영향을 받은 행 수를 리턴                                                 |
| **[UserService.java](model/UserService.java)**      |   ● 사용자 정보에 대한 로직을 처리하고, 데이터베이스와의 연동을 위해 UserMapper를 호출하는 메서드를 정의 <br> - 로그인, 회원가입, 중복 확인, 사용자 정보 조회 및 수정 등 사용자 관련 기능을 제공 <br> - UserMapper 메서드를 활용하여 데이터베이스와 상호작용하며, 사용자 데이터의 비즈니스 로직을 구현하는 역할      |
| **[UserServiceImpl.java](model/UserServiceImpl.java)**        |   ● UserService 인터페이스의 메서드를 구현하여 사용자 데이터와 관련된 비즈니스 로직을 수행하고, UserMapper와의 상호작용을 통해 데이터베이스 작업을 처리 <br> - 로그인, 회원가입, 중복 확인, 사용자 정보 수정 등 사용자와 관련된 기능을 구현하여 사용자 정보의 검증과 암호화를 포함한 비즈니스 로직을 관리 <br> -  각 메서드는 SqlSession을 통해 UserMapper를 호출하고 데이터베이스에 쿼리를 실행하며, 필요 시 세션을 커밋하여 변경 사항을 반영    |
| **[UserValidator.java](model/UserValidator.java)**    |    ● User 객체의 필드 유효성을 검사하는 메서드를 제공하여 사용자 입력의 형식적 유효성을 확인 <br> -  ID, 비밀번호, 닉네임, 전화번호, 사업자 번호 등의 유효성을 개별 메서드로 검사하고, 모든 검사 결과를 종합하여 오류 또는 유효성 메시지를 반환  <br> - 각 유효성 검사는 정규 표현식이나 조건을 사용하며, 비밀번호는 대소문자, 숫자, 특수문자의 포함 여부를 기준으로 확인     |
| **[AuthRequest.java](model/AuthRequest.java)**               |   ● 사용자 인증 요청에 필요한 데이터를 캡슐화 <br> - 특정 인증 요청의 동작을 정의     |
| **[AuthResponse.java](model/AuthResponse.java)**               |   ● 사용자 인증 요청의 결과를 전달하는 응답 정보를 포함 <br> - 인증 성공 여부를 나타내는 success 필드와 관련 메시지를 포함    |
| **[PasswordEncryption.java](model/PasswordEncryption.java)**               |   ● 주어진 비밀번호를 SHA-256 해시 함수로 암호화하는 기능을 제공 <br> - encryptPassword 메서드는 비밀번호를 SHA-256으로 해시한 후, 결과를 16진수 문자열로 변환하여 반환   |
<br>

### Suggestion

| 클래스명                | 설명                                                                                                                                                       |
|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **[Suggestion.java](suggestion/Suggestion.java)**        |   ● 사용자 제안의 정보를 나타내는 데이터 모델로, 제안 ID, 사용자 ID, 제목, 제안 내용, 처리 상태 및 업로드 시간을 포함   |
| **[SuggestionMapper.java](suggestion/SuggestionMapper.java)**    |    ● 사용자의 제안을 데이터베이스에 접근하기 위한 MyBatis 매퍼로, 제안 조회, 추가, 업데이트 기능을 제공하는 인터페이스 <br> - SQL 쿼리를 사용하여 user_suggestion 테이블의 모든 건의사항 조회, 추가, 상태 전환 메서드를 정의      |
| **[SuggestionService.java](suggestion/SuggestionService.java)**               |   ● 사용자 제안에 대한 서비스 레이어로, 제안의 추가, 조회, 및 상태 업데이트 기능을 제공 <br> - 제안 데이터를 추가하는 insert 메서드, 모든 제안을 조회하는 select 메서드, 그리고 특정 제안의 처리 상태를 업데이트하는 updateSuggestionStatus 메서드를 정의      |
| **[SuggestionServiceImpl.java](suggestion/SuggestionServiceImpl.java)**               |   ●  SuggestionService 인터페이스를 구현하여 사용자 건의사항의 추가, 조회 및 상태 업데이트 기능을 제공 <br> - 싱글톤 패턴을 사용하여 인스턴스를 관리하며, insert 메서드는 건의사항을 데이터베이스에 추가하고, 조회, 삽입, 수정 진행   |
| **[SuggestionsServlet.java](suggestion/SuggestionsServlet.java)**               |   ● 사용자 건의사항을 조회하고 처리 상태를 업데이트하는 서블릿 <br> - 데이터베이스에서 모든 제안 목록을 조회하여 JSP 페이지로 전달하고, 제출된 요청을 기반으로 각 제안의 활성화 상태를 업데이트한 후 업데이트된 목록을 JSP 페이지에 다시 전달     |


<br>

## 주요 코드

1. 로그인 필터 코드(LoginFilter.java)
 - 특정 페이지 접근 시 로그인 상태를 확인하는 필터를 설정
     
```
   // 특정 URL 패턴에 대해 로그인 필터 적용
@WebFilter(filterName = "LoginFilter", urlPatterns = {
    "/userInfo", "/cafeReview", "/userSuggestion", "/suggestions", "/admin/search", "/ownerPage", "/addMenu"
})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // 세션이 없거나 세션에 userID가 없는 경우 로그인 페이지로 리다이렉트
        if (session == null || session.getAttribute("userID") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            // 세션이 유효한 경우 다음 필터나 서블릿으로 요청을 전달
            chain.doFilter(request, response);
        }
    }
}

```
<br>

2. 유저 클래스(User.java)
 - DB와 연동할 수 있도록 필드값들을 정의하는 클래스
   
```
package user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

	private String userID;
	private String userPW;
	private String userPhoneNumber;
	private String userNickname;
	private int userType;
	private String userOwnerNumber;
	private String userPicture;
	private int active;

}
```

<br>

3. 비밀번호 암호화 코드(PasswordEncryption.java)
 - 주어진 비밀번호를 SHA-256 해시 알고리즘으로 암호화하여 반환하는 메서드
   
```
    public static String encryptPassword(String password) {
        try {
            // SHA-256 해시 함수의 인스턴스를 생성
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // 비밀번호 바이트를 해시 함수에 업데이트
            md.update(password.getBytes());

            // 해시 계산을 수행하고 바이트 배열로 결과를 반환
            byte[] byteData = md.digest();

            // 결과를 16진수 문자열로 변환하기 위한 StringBuilder 생성
            StringBuilder sb = new StringBuilder();
            for (byte b : byteData) {
                // 각 바이트를 2자리 16진수 문자열로 변환하여 StringBuilder에 추가
                sb.append(String.format("%02x", b));
            }

            // 변환된 16진수 문자열 반환
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // SHA-256 알고리즘을 사용할 수 없는 경우 예외 처리
            throw new RuntimeException(e);
        }
    }
```
<br>

4. 아이디 유효성 검사 코드(UserPictureServlet.java)
 - 사용자의 정보를 검증하고 결과를 Map에 담아 반환
 - 대문자, 소문자, 특수문자 등의 여부를 확인하여 유효성을 검사
   
```
   // 
    public static Map<String, String> validate(User user) {
        // 검증 결과를 담을 Map 생성
        Map<String, String> map = new HashMap<String, String>();
        
        // 각 필드를 검증하고 오류가 발생할 경우 오류 메시지를 Map에 추가
        if (!isValidID(user.getUserID())) {
            map.put("error", "아이디 유효성 에러");
        } else if (!isValidPW(user.getUserPW())) {
            map.put("error", "비밀번호 유효성 에러");
        } else if (!isValidNickname(user.getUserNickname())) {
            map.put("error", "닉네임 유효성 에러");
        } else if (!isValidPhoneNumber(user.getUserPhoneNumber())) {
            map.put("error", "전화번호 유효성 에러");
        } else if (!isValidOwnerInput(user.getUserOwnerNumber(), user.getUserPicture())) {
            map.put("error", "사업자 유효성 에러");
        } else if (user.getUserOwnerNumber().length() > 0 && !isValidOwnerNumber(user.getUserOwnerNumber())) {
            map.put("error", "사업자번호 유효성 에러");
        } else {
            // 모든 필드가 유효할 경우 유효성 메시지 추가
            map.put("valid", "유효함");
        }
        return map; // 검증 결과 반환
    }

    // 사용자 ID의 유효성을 검증하는 메서드
    public static boolean isValidID(String id) {
        // ID는 알파벳으로 시작하고, 4~20자 길이의 알파벳 또는 숫자로 구성되어야 함
        String regex = "^[a-zA-Z][a-zA-Z0-9]{3,19}$";
        return Pattern.matches(regex, id); // 정규식에 따라 검증
    }

    // 비밀번호의 유효성을 검증하는 메서드
    public static boolean isValidPW(String pw) {
        // 비밀번호 길이는 8자 이상 20자 이하
        if (pw.length() < 8 || pw.length() > 20) {
            return false; // 길이 제한을 통과하지 못한 경우 false 반환
        }

        // 대문자, 소문자, 숫자, 특수문자 포함 여부 확인
        boolean hasUpperCase = pw.chars().anyMatch(Character::isUpperCase);
        boolean hasLowerCase = pw.chars().anyMatch(Character::isLowerCase);
        boolean hasNumber = pw.chars().anyMatch(Character::isDigit);
        boolean hasSpecialChar = pw.chars().anyMatch(ch -> "~!@#$%^&*_-+=`|\\(){}[\\]:;\"'<>,.?/".indexOf(ch) >= 0);

        // 대문자 또는 소문자, 숫자, 특수문자가 모두 포함된 경우 true 반환
        return (hasUpperCase || hasLowerCase) && hasNumber && hasSpecialChar;
    }
```
<br>

5. 유저 로그인 코드(UserServiceImpl.java)
 - 입력받은 유저 데이터를 DB에 조회한 후 비밀번호 암호화를 통해 일치하는지 확인
 - 일치할 경우 로그인 아닐 경우 null 반환

```
@Override
public User login(User user) {
    // SQL 세션을 열고 자원을 자동으로 해제하기 위해 try-with-resources 구문 사용
    try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
        // UserMapper 인터페이스의 인스턴스를 가져옴
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        
        // 사용자 ID를 기반으로 데이터베이스에서 사용자 정보를 조회
        User selectById = mapper.selectById(user.getUserID());
        
        // 입력된 비밀번호를 SHA-256 해시로 암호화
        user.setUserPW(PasswordEncryption.encryptPassword(user.getUserPW()));
        
        // 조회된 사용자가 없는 경우(null) null 반환
        if (selectById == null)
            return null;

        // 조회된 사용자의 비밀번호와 입력된 비밀번호가 일치하는지 확인
        if (selectById.getUserPW().equals(user.getUserPW())) {
            // 비밀번호가 일치하면 사용자 정보를 반환
            return selectById;
        }

        // 비밀번호가 일치하지 않으면 null 반환
        return null;
    }
}

```

