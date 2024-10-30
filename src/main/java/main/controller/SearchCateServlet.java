package main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafeteria.CafePic;
import cafeteria.CafeTag;
import cafeteria.Cafeteria;
import cafeteria.CafeteriaService;
import cafeteria.CafeteriaServiceImple;
import cafeteria.CafeteriaWithPicDTO;
import lombok.extern.slf4j.Slf4j;
import user.model.User;
import user.model.UserService;
import user.model.UserServiceImpl;

// "/searchCategory" URL 요청을 처리하는 서블릿
@WebServlet("/searchCategory")
@Slf4j
public class SearchCateServlet extends HttpServlet {
	private CafeteriaService service = CafeteriaServiceImple.getInstance(); // 식당 서비스 객체 생성
	private final UserService userService = UserServiceImpl.getInstance();  // 사용자 서비스 객체 생성

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 사용자 정보 초기화
		initUserAttributes(req);

		// 카페 리스트 초기화
		initCafeteriaList(req);

		// searchCategory.jsp로 포워딩
		req.getRequestDispatcher("/WEB-INF/view/searchCategory.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 사용자 정보 초기화
		initUserAttributes(req);

		// 카테고리에 따라 카페 리스트 초기화
		initCafeteriaListByCategory(req);

		// 검색 결과를 JSP로 전달
		req.getRequestDispatcher("/WEB-INF/view/searchCategory.jsp").forward(req, resp);
	}

	// 선택된 카테고리에 따라 식당 리스트를 초기화하는 메서드
	private void initCafeteriaListByCategory(HttpServletRequest req) {
	    String searchByPrice = (String) req.getParameter("max-price"); // 최대 가격 파라미터를 가져옴
	    String countPpl = (String) req.getParameter("people-combo"); // 인원수 파라미터를 가져옴
	    int searchByPriceAvg = (int) Math.round(Double.parseDouble(searchByPrice) / Double.parseDouble(countPpl)); // 1인당 평균 가격 계산

	    String[] categories = req.getParameterValues("category"); // 선택된 카테고리 목록을 가져옴
	    String additionalCategory = req.getParameter("additionalCategory"); // 추가 카테고리 입력 값을 가져옴
	    List<Cafeteria> searchResults; // 검색 결과를 저장할 리스트
	    List<String> chkList = new ArrayList<>(); // 체크된 카테고리를 저장할 리스트

	    // 체크된 카테고리와 추가 카테고리 처리
	    if (categories != null) { // 선택된 카테고리가 있는 경우
	        for (String category : categories) { // 각 카테고리를 체크리스트에 추가
	            chkList.add(category);
	        }
	    }
	    if (additionalCategory != null && !additionalCategory.trim().isEmpty()) { // 추가 카테고리가 있는 경우
	        chkList.add(additionalCategory); // 체크리스트에 추가
	    }
	    // 가격 필터링이 있는 경우, 가격과 태그를 기반으로 카페 검색
	    if (searchByPrice != null && !searchByPrice.trim().isEmpty()) {
	        searchResults = service.getCafeByPriceAndTags(searchByPriceAvg, chkList); // 가격과 태그로 카페 검색
	    } else {
	        searchResults = service.selectAll(); // 검색 조건이 없으면 전체 카페 목록 반환
	    }

	    // 검색된 카페와 해당 사진 정보 병합
	    List<CafeteriaWithPicDTO> mergedList = new ArrayList<>(); // 병합된 리스트 초기화
	    for (Cafeteria cafeteria : searchResults) { // 각 카페에 대해
	        List<CafePic> cafePicList = service.selectPicsByCafeNum(cafeteria.getCafeNum()); // 카페 사진 리스트 가져옴
	        mergedList.add(new CafeteriaWithPicDTO(cafeteria, cafePicList)); // DTO로 병합하여 추가
	    }

	    // 병합된 결과를 JSP로 전달
	    req.setAttribute("mergedList", mergedList); // 병합된 리스트를 요청 속성으로 설정

	    // 검색 결과에 해당하는 태그 정보를 초기화
	    initTags(req, searchResults); // 검색된 카페에 대한 태그 정보 초기화
	}


	// 검색 결과의 각 카페에 대한 태그 정보를 초기화
	private void initTags(HttpServletRequest req, List<Cafeteria> searchResults) {
		Map<Integer, List<CafeTag>> cafeTagsMap = new HashMap<>();

		// 각 카페의 태그 정보를 조회하여 cafeTagsMap에 저장
		for (Cafeteria cafeteria : searchResults) {
			List<CafeTag> tags = service.selectCafeTag(cafeteria.getCafeNum());
			cafeTagsMap.put(cafeteria.getCafeNum(), tags);
		}

		// 태그 맵을 요청 속성에 설정하여 JSP에서 사용 가능하도록 설정
		req.setAttribute("cafeTagsMap", cafeTagsMap);
	}

	// 세션에서 사용자 정보를 가져와 초기화
	private void initUserAttributes(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userSessionID = (String) session.getAttribute("userID");
		String userID = (String) req.getAttribute("userID");
		if (userSessionID != null) {
			User user = (User) userService.userInfo(userSessionID); // 사용자 정보를 조회
			req.setAttribute("userType", user.getUserType());       // 사용자 유형 설정
		}
		req.setAttribute("userID", userID);                        // userID 속성 설정
	}

	// 전체 Cafeteria 목록을 가져와 초기화
	private void initCafeteriaList(HttpServletRequest req) {
		List<Cafeteria> list = service.selectAll();                   // 전체 카페 목록 가져오기

		// Cafeteria와 CafePic 리스트 병합
		List<CafeteriaWithPicDTO> mergedList = mergeCafeteriaAndPic(list);

		// 병합된 리스트를 JSP로 전달
		req.setAttribute("mergedList", mergedList);

		// 태그 정보를 초기화
		initTags(req, list);
	}

	// Cafeteria와 CafePic을 병합하는 메서드
	private List<CafeteriaWithPicDTO> mergeCafeteriaAndPic(List<Cafeteria> cafeteriaList) {
		List<CafeteriaWithPicDTO> mergedList = new ArrayList<>();

		for (Cafeteria cafeteria : cafeteriaList) {
			// 해당 Cafeteria에 대한 모든 CafePic을 가져와 병합 리스트에 추가
			List<CafePic> cafePics = service.selectPicsByCafeNum(cafeteria.getCafeNum());
			mergedList.add(new CafeteriaWithPicDTO(cafeteria, cafePics));
		}

		return mergedList; // 병합된 리스트 반환
	}
}
