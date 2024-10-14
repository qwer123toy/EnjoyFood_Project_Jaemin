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

import com.jayway.jsonpath.JsonPath;

import cafeteria.CafeteriaService;
import cafeteria.CafeteriaServiceImple;
import cafeteria.Menu;
import config.WebUtil;

@WebServlet("/addMenu")
@MultipartConfig

public class AddMenuServlet extends HttpServlet {
	// CafeteriaService 인스턴스 가져오기 (싱글톤 패턴)
	CafeteriaService service = CafeteriaServiceImple.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String cafeOwner = req.getParameter("userId");
			req.setAttribute("cafeOwner", cafeOwner);
		req.getRequestDispatcher("/WEB-INF/view/addMenu.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 데이터 받기	
		WebUtil webUtil = new WebUtil();
		
		String json = webUtil.readBody(req);
		//		Menu menu = webUtil.readBodyJson(req, Menu.class);
//		int result = service.insertMenu(menu);
		 // 카페 번호(cafeNum) 받기
//        String cafeNumStr = req.getParameter("cafeNum"); // 요청 파라미터에서 cafeNum 가져오기
//        int cafeNum = Integer.parseInt(cafeNumStr); // cafeNum을 정수로 변환
		String cafeOwner = JsonPath.read(json, "$.cafeOwner");
		int cafeNum = service.selectCafeNumBycafeOwner(cafeOwner);
        // 메뉴 리스트와 태그 개수 처리
        List<String> menuList = new ArrayList<>();

     // 메뉴 개수 받기
        String menuCountStr = JsonPath.read(json, "$.menuCount"); // menuCount를 요청 파라미터에서 가져옵니다.
        int menuCount = 0;
        
        if (menuCountStr != null && !menuCountStr.isEmpty()) {
        	menuCount = Integer.parseInt(menuCountStr); // null 또는 빈 문자열 체크
        	System.out.println(menuCount);
        } else {
            // 메뉴 카운트가 null일 경우, 기본값 설정 (0 또는 다른 적절한 값으로)
            System.out.println("menuCount is null or empty");
            menuCount = 0;
        }
        
        for (int i = 1; i <= menuCount; i++) {
            String menuJson = extractMenuJson(cafeNum, json, i);
            System.out.println("메뉴 " + i + " JSON: " + menuJson);
           Menu menu = webUtil.readBodyJsonForMenu(menuJson, Menu.class);
            int results = service.insertMenu(menu);
           
        }
//
//        // 메뉴 추가
//        for (int i = 1; i <= menuCount; i++) { // 메뉴 카운트만큼 반복
//            String menuParam = req.getParameter("menuInput" + i); // 태그 정보를 요청 파라미터에서 가져옵니다.
//            if (menuParam != null && !menuParam.isEmpty()) {
//            	menuList.add(menuParam); // 유효한 메뉴만 추가
//            }
//        }
//
//        // 메뉴 개수 제한 (혹시 모를 오류 대비)
//        if (menuList.size() > 20) {
//        	menuList = menuList.subList(0, 20); // 처음 20개만 남김
//        }

        // 메뉴 DB에 저장
//        int results = service.insertMenu(cafeNum, menuCountStr);
        
//        webUtil.setCodeAndMimeType(resp, 201, "json");
//		webUtil.writeBodyJson(resp, menu);
//		if (result > 0) {
//			System.out.println("메뉴 추가 성공!");
//		} else {
//			System.out.println("메뉴 추가 실패!");
//		}

	}
	   private static String extractMenuJson(int cafeNum, String jsonString, int menuIndex) {
	        String menuName = extractValue(jsonString, "menuName" + menuIndex);
	        String menuPrice = extractValue(jsonString, "menuPrice" + menuIndex);
	        String menuNamepic = extractValue(jsonString, "menuNamepic" + menuIndex);
	        String menuExplain = extractValue(jsonString, "menuExplain" + menuIndex);

	        return String.format("{\"cafeNum\":%d,\"menuName\":\"%s\",\"menuPrice\":%s,\"menuNamepic\":\"%s\",\"menuExplain\":\"%s\"}",
	                cafeNum, menuName, menuPrice, menuNamepic, menuExplain);
	    }

	    private static String extractValue(String jsonString, String key) {
	        String pattern = String.format("\"%s\":\"(.*?)\"", key);
	        java.util.regex.Pattern regex = java.util.regex.Pattern.compile(pattern);
	        java.util.regex.Matcher matcher = regex.matcher(jsonString);
	        if (matcher.find()) {
	            return matcher.group(1);
	        }
	        return "";
	    }






}
