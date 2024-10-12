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
import cafeteria.Cafeteria;
import cafeteria.CafeteriaService;
import cafeteria.CafeteriaServiceImple;
import config.WebUtil;


@WebServlet("/ownerPage")
@MultipartConfig
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
        req.setCharacterEncoding("UTF-8");

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
		String cafeCategoryStr = JsonPath.read(json, "$.cafeCategory");
		int cafeCategory = Integer.parseInt(cafeCategoryStr);

		List<String> cafeTagList = new ArrayList<>();

//		int tagCount = Integer.parseInt(req.getParameter("tagCount"));

		for (int i = 1; i <= 3; i++) {
		    String tagParam = req.getParameter("tagInput-" + i);
		    if (tagParam != null && !tagParam.isEmpty()) {
		        cafeTagList.add(tagParam);
		    }
		}


		System.out.println(json);
		System.out.println(cafeTagList);
		// JSON 형식으로 다시 String 생성
		String resultJsonForCafeteria = "{"
		        + "\"cafeName\": \"" + cafeName + "\","
		        + "\"cafeExplain\": \"" + cafeExplain + "\","
		        + "\"cafePhoneNumber\": \"" + cafePhoneNumber + "\","
		        + "\"cafePrice\": \"" + cafePrice + "\","
		        + "\"cafeAddress\": \"" + cafeAddress + "\","
		        + "\"cafeOpenTime\": \"" + cafeOpenTime + "\""
		        +  "}";


		Cafeteria cafeteria = jsonMapper.readValue(resultJsonForCafeteria, Cafeteria.class);
		
		
		int cafeNum = service.insert(cafeteria);
		service.insertCategoryM(cafeNum, cafeCategory);
		
		for(int i=0; i<3; i++) {
			service.insertTag(cafeNum, cafeTagList.get(i));
		}
		
		webUtil.setCodeAndMimeType(resp, 201, "json");
		webUtil.writeBodyJson(resp, cafeteria);
		
		
		
	}
	
	
}
