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
		List<CafeCategory> categoryList = service.selectCategoryAll();
		req.setAttribute("categoryList", categoryList);
		 String cafeOwner = req.getParameter("userId");
		req.setAttribute("cafeOwner", cafeOwner);
		req.getRequestDispatcher("/WEB-INF/view/ownerPage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
//		 String cafeOwner = req.getParameter("cafeOwner");
//        String cafeOwner = (String) req.getAttribute("cafeOwner");
//        System.out.println(cafeOwner);
		WebUtil webUtil = new WebUtil();
		String json = webUtil.readBody(req);
		JsonMapper jsonMapper = new JsonMapper();
		
//		System.out.println(json);

		// JSON에서 값 추출
		String cafeName = JsonPath.read(json, "$.cafeName");
		String cafeExplain = JsonPath.read(json, "$.cafeExplain");
		String cafePhoneNumber = JsonPath.read(json, "$.cafePhoneNumber");
		String cafePrice = JsonPath.read(json, "$.cafePrice");
		String cafeAddress = JsonPath.read(json, "$.cafeAddress");
		String cafeStartTime = JsonPath.read(json, "$.start-time");
		String cafeEndTime = JsonPath.read(json, "$.end-time");
		String tagCountStr = JsonPath.read(json, "$.tagCount");
		String cafePic64 = JsonPath.read(json, "$.cafePic64");
		String cafeOwner = JsonPath.read(json, "$.cafeOwner");
		
		// Custom 시간 처리
		if (cafeStartTime.equals("custom-start")) {
			cafeStartTime = JsonPath.read(json, "$.custom-start-time"); // custom-start-time 값으로 대체
		}

		if (cafeEndTime.equals("custom-end")) {
			cafeEndTime = JsonPath.read(json, "$.custom-end-time"); // custom-end-time 값으로 대체
		}

		// Start와 End Time을 연결하여 cafeOpenTime 구성
		String cafeOpenTime = cafeStartTime.concat(" - ").concat(cafeEndTime);

		// 카테고리 값 추출
		String cafeCategoryStr = JsonPath.read(json, "$.cafeCategory");
		int cafeCategory = Integer.parseInt(cafeCategoryStr);
	
		// JSON 형식으로 다시 String 생성
				String resultJsonForCafeteria = "{" + "\"cafeName\": \"" + cafeName + "\"," + "\"cafeExplain\": \""
						+ cafeExplain + "\"," + "\"cafePhoneNumber\": \"" + cafePhoneNumber + "\"," + "\"cafePrice\": \""
						+ cafePrice + "\"," + "\"cafeAddress\": \"" + cafeAddress + "\"," + "\"cafeOpenTime\": \""
						+ cafeOpenTime + "\"," + "\"cafeOwner\": \"" + cafeOwner + "\"" +
						"}";
				
				Cafeteria cafeteria = jsonMapper.readValue(resultJsonForCafeteria, Cafeteria.class);
				int cafeNum = service.insert(cafeteria);
				service.insertCategoryM(cafeNum, cafeCategory);
		
		List<String> cafeTagList = new ArrayList<>();

		// 태그 개수 받기
		int tagCount = 0;

		if (tagCountStr != null && !tagCountStr.isEmpty()) {
			tagCount = Integer.parseInt(tagCountStr); // null 또는 빈 문자열 체크
		} else {
			// 태그 카운트가 null일 경우, 기본값 설정 (0 또는 다른 적절한 값으로)
			System.out.println("tagCount is null or empty");
			tagCount = 0;
		}

		// 태그 추가
		for (int i = 1; i <= tagCount; i++) { // 태그 카운트만큼 반복
			String tagParam = JsonPath.read(json, "$.tagInput-" + i);
			if (tagParam != null && !tagParam.isEmpty()) {
				cafeTagList.add(tagParam); // 유효한 태그만 추가
			}
		}

		// 태그 개수 제한 (혹시 모를 오류 대비)
		if (cafeTagList.size() > 5) {
			cafeTagList = cafeTagList.subList(0, 5); // 처음 5개만 남김
		}
		
		// 태그 추가 부분에서 인덱스 범위 확인
		for (int i = 0; i < cafeTagList.size(); i++) {
			service.insertTag(cafeNum, cafeTagList.get(i));
		}

		int result = service.insertPic(cafeNum, cafePic64);
		webUtil.setCodeAndMimeType(resp, 201, "json");
		webUtil.writeBodyJson(resp, cafeteria);
	}
}
