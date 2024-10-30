package main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.jayway.jsonpath.JsonPath;

import cafeteria.CafeCategory;
import cafeteria.CafePic;
import cafeteria.Cafeteria;
import cafeteria.CafeteriaService;
import cafeteria.CafeteriaServiceImple;
import cafeteria.Menu;
import config.WebUtil;

@WebServlet("/ownerPage")
@MultipartConfig
public class OwnerPage_basic extends HttpServlet {
	private CafeteriaService service = CafeteriaServiceImple.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 모든 카테고리 목록을 조회하여 요청 속성에 설정
		List<CafeCategory> categoryList = service.selectCategoryAll();
		req.setAttribute("categoryList", categoryList);

		// 가게 주인의 사용자 ID를 요청 파라미터에서 가져와 요청 속성에 설정
		String cafeOwner = req.getParameter("userId");
		req.setAttribute("cafeOwner", cafeOwner);

		// ownerPage.jsp로 포워딩
		req.getRequestDispatcher("/WEB-INF/view/ownerPage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    req.setCharacterEncoding("UTF-8"); // 요청의 문자 인코딩을 UTF-8로 설정

	    WebUtil webUtil = new WebUtil();
	    String json = webUtil.readBody(req); // 요청 본문에서 JSON 데이터 읽기
	    JsonMapper jsonMapper = new JsonMapper();

	    // JSON에서 가게 정보를 추출
	    String cafeName = JsonPath.read(json, "$.cafeName"); // 가게 이름
	    String cafeExplain = JsonPath.read(json, "$.cafeExplain"); // 가게 설명
	    String cafePhoneNumber = JsonPath.read(json, "$.cafePhoneNumber"); // 가게 전화번호
	    String cafePrice = JsonPath.read(json, "$.cafePrice"); // 가게 가격
	    String cafeAddress = JsonPath.read(json, "$.cafeAddress"); // 가게 주소
	    String cafeStartTime = JsonPath.read(json, "$.start-time"); // 시작 시간
	    String cafeEndTime = JsonPath.read(json, "$.end-time"); // 종료 시간
	    String tagCountStr = JsonPath.read(json, "$.tagCount"); // 태그 개수
	    String cafePic64 = JsonPath.read(json, "$.cafePic64"); // 카페 사진(64비트 인코딩)
	    String cafeOwner = JsonPath.read(json, "$.cafeOwner"); // 카페 소유자

	    // 사용자 지정 시간 처리를 위한 조건문
	    if (cafeStartTime.equals("custom-start")) { // 사용자 지정 시작 시간이면
	        cafeStartTime = JsonPath.read(json, "$.custom-start-time"); // 사용자 지정 시작 시간으로 대체
	    }
	    if (cafeEndTime.equals("custom-end")) { // 사용자 지정 종료 시간이면
	        cafeEndTime = JsonPath.read(json, "$.custom-end-time"); // 사용자 지정 종료 시간으로 대체
	    }

	    // 시작 및 종료 시간을 연결하여 영업시간을 설정
	    String cafeOpenTime = cafeStartTime.concat(" - ").concat(cafeEndTime); // "시작시간 - 종료시간" 형식으로 설정

	    // 카테고리 값 추출 후 정수형으로 변환
	    String cafeCategoryStr = JsonPath.read(json, "$.cafeCategory"); // 카테고리 문자열을 읽음
	    int cafeCategory = Integer.parseInt(cafeCategoryStr); // 카테고리 문자열을 정수형으로 변환

	    // JSON 형태로 Cafeteria 객체 생성에 필요한 데이터 구성
	    String resultJsonForCafeteria = "{" +
	            "\"cafeName\": \"" + cafeName + "\"," +
	            "\"cafeExplain\": \"" + cafeExplain + "\"," +
	            "\"cafePhoneNumber\": \"" + cafePhoneNumber + "\"," +
	            "\"cafePrice\": \"" + cafePrice + "\"," +
	            "\"cafeAddress\": \"" + cafeAddress + "\"," +
	            "\"cafeOpenTime\": \"" + cafeOpenTime + "\"," +
	            "\"cafeOwner\": \"" + cafeOwner + "\"" +
	            "}";

	    // Cafeteria 객체 생성 및 삽입
	    Cafeteria cafeteria = jsonMapper.readValue(resultJsonForCafeteria, Cafeteria.class); // JSON에서 Cafeteria 객체로 변환
	    int cafeNum = service.insert(cafeteria); // 새로운 가게를 삽입 후 번호 가져오기
	    service.insertCategoryM(cafeNum, cafeCategory); // 가게 번호와 카테고리 삽입

	    List<String> cafeTagList = new ArrayList<>(); // 태그 목록 초기화

	    // 태그 개수 확인 및 설정
	    int tagCount = 0; // 태그 카운터 초기화
	    if (tagCountStr != null && !tagCountStr.isEmpty()) { // 태그 개수가 null이 아니고 비어있지 않은 경우
	        tagCount = Integer.parseInt(tagCountStr); // 태그 개수 설정
	    } else {
	        System.out.println("tagCount is null or empty"); // 태그 개수가 null이거나 비어있을 경우 로그 출력
	    }

	    // 태그를 JSON에서 추출하고 유효한 태그만 추가
	    for (int i = 1; i <= tagCount; i++) { // 태그 개수만큼 반복
	        String tagParam = JsonPath.read(json, "$.tagInput-" + i); // 각 태그를 JSON에서 읽기
	        if (tagParam != null && !tagParam.isEmpty()) { // 태그가 유효한 경우
	            cafeTagList.add(tagParam); // 유효한 태그 추가
	        }
	    }

	    // 태그 목록에서 최대 5개만 유지
	    if (cafeTagList.size() > 5) { // 태그가 5개 초과일 경우
	        cafeTagList = cafeTagList.subList(0, 5); // 처음 5개만 유지
	    }

	    // 태그 삽입
	    for (int i = 0; i < cafeTagList.size(); i++) { // 각 태그에 대해
	        service.insertTag(cafeNum, cafeTagList.get(i)); // 태그 삽입
	    }

	    // 이미지 삽입
	    int result = service.insertPic(cafeNum, cafePic64); // 카페 사진 삽입

	    // 응답 코드와 MIME 타입 설정 및 응답 본문 작성
	    webUtil.setCodeAndMimeType(resp, 201, "json"); // 응답 코드 201과 MIME 타입 설정
	    webUtil.writeBodyJson(resp, "됨"); // 응답 본문에 "됨" 메시지 작성
	}

}
