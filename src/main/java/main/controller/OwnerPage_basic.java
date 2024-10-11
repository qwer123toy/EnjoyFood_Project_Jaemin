package main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.json.JsonMapper;

import cafeteria.CafeCategory;
import cafeteria.Cafeteria;
import cafeteria.CafeteriaService;
import cafeteria.CafeteriaServiceImple;
import config.WebUtil;
import com.jayway.jsonpath.JsonPath;


@WebServlet("/ownerPage")
public class OwnerPage_basic extends HttpServlet {
	private CafeteriaService service = CafeteriaServiceImple.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<CafeCategory> categoryList = service.selectCategoryAll();
		
		req.setAttribute("categoryList", categoryList);
		
		
//		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
//		String storeExplain = req.getParameter("storeExplain");
	
		req.getRequestDispatcher("/WEB-INF/view/ownerPage.jsp").forward(req, resp);
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();
		String json = webUtil.readBody(req);
		JsonMapper jsonMapper = new JsonMapper();

		// JSON에서 값 추출
		String cafeName = JsonPath.read(json, "$.cafeName");
		String cafeExplain = JsonPath.read(json, "$.cafeExplain");
		String cafePhoneNumber = JsonPath.read(json, "$.cafePhoneNumber");
		String cafePrice = JsonPath.read(json, "$.cafePrice");        
		String cafeAddress = JsonPath.read(json, "$.cafeAddress");

		String cafeStartTime = JsonPath.read(json, "$.start-time");
		String cafeEndTime = JsonPath.read(json, "$.end-time");

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
		String cafeCategory = JsonPath.read(json, "$.cafeCategory");

		// JSON 형식으로 다시 String 생성
		String resultJson = "{"
		        + "\"cafeName\": \"" + cafeName + "\","
		        + "\"cafeExplain\": \"" + cafeExplain + "\","
		        + "\"cafePhoneNumber\": \"" + cafePhoneNumber + "\","
		        + "\"cafePrice\": \"" + cafePrice + "\","
		        + "\"cafeAddress\": \"" + cafeAddress + "\","
		        + "\"cafeOpenTime\": \"" + cafeOpenTime + "\","
		        + "}";

		// 결과 확인
		System.out.println(resultJson);

//		Menu menu = jsonMapper.readValue(json, Menu.class);
		Cafeteria cafeteria = jsonMapper.readValue(resultJson, Cafeteria.class);
		
		
		
		service.insert(cafeteria);
		webUtil.setCodeAndMimeType(resp, 201, "json");
		webUtil.writeBodyJson(resp, cafeteria);
		
		
		
	}
	
	
}
