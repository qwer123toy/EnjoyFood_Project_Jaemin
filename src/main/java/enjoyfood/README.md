<div align="center">
<img src="https://github.com/user-attachments/assets/0dc2146e-293b-4146-9ea2-359e794916c7">
</div>

<h1 align="center">
  Map
</h1>
<p align="center">카카오 API를 통해 실제 주소와 지도를 다루는 클래스 </p>
<p align="center"> 카카오 맵 API를 활용하여 KEY를 발급받고 해당 KEY를 통해 실제 주소를 입력하면 <br>
  그 곳에 해당하는 지도를 보여주기 위한 클래스 </p>
  
--- 

## Contents
<p align="left">목 차</p>
<p align="left">
  <a href="#클래스-설명">클래스 설명</a> <br>
  <a href="#주요-코드">주요 코드</a> 
</p>

---

## 클래스 설명

| 클래스명                | 설명                                                                                                                                                       |
|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **[MapAPI.java](MapAPI.java)**                  |  ● 클라이언트 요청에서 카페 이름을 받아 해당 주소를 조회하고, JSP 페이지로 결과를 전달 <br> -  입력된 카페 이름에 대한 주소를 찾아내고, 이를 HTTP 요청의 속성으로 설정하여 JSP 페이지로 포워딩                                |
| **[MapMapper.java](MapMapper.java)**                 |    ● 식당 이름을 기반으로 해당 식당의 주소를 조회하는 메소드를 정의                      |
| **[MapService.java](MapService.java)**                 |    ●   카페 이름을 입력받아 해당 카페의 주소를 찾는 메소드를 정의하는 인터페이스                                   |
| **[MapServiceImple.java](MapServiceImple.java)**                 |    ●  Singleton 패턴을 사용하여 식당 이름에 대한 주소를 검색하는 서비스 구현                                    |

<br>

## 주요 코드
1. 가게 검색 코드(MainServlet.java)
 - 카카오 API에서 받아온 KEY를 활용하여 주소를 조회하고 JSP 페이지로 전달
     
```
@WebServlet("/map")
public class MapAPI extends HttpServlet {
    // API 키 설정 (예시)
    private static final String API_KEY = "---------------------";
    // JSP 페이지의 URL
    private static final String API_URL = "/WEB-INF/view/map.jsp";
    // MapService 인스턴스 초기화
    private MapService service = MapServiceImple.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 클라이언트 요청에서 카페 이름을 파라미터로 가져옴
        String cafeName = req.getParameter("cafeName");     
        // 카페 이름을 기반으로 주소를 조회
        String address = service.findAddressByName(cafeName);

        // 조회된 address 값을 request 객체에 저장
        req.setAttribute("address", address);

        // JSP 페이지로 포워딩하여 사용자에게 결과를 보여줌
        System.out.println(address); // 콘솔에 주소 출력
        req.getRequestDispatcher(API_URL).forward(req, resp);
    }
}
```
<br>
