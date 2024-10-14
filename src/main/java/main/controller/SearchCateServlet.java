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

@WebServlet("/searchCategory")
@Slf4j
public class SearchCateServlet extends HttpServlet {
	private CafeteriaService service = CafeteriaServiceImple.getInstance();
	private final UserService userService = UserServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		initUserAttributes(req);

		initCafeteriaList(req);

		req.getRequestDispatcher("/WEB-INF/view/searchCategory.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		initUserAttributes(req);

		initCafeteriaListByCategory(req);

		// 검색 결과를 JSP로 전달
		req.getRequestDispatcher("/WEB-INF/view/searchCategory.jsp").forward(req, resp);
	}

	private void initCafeteriaListByCategory(HttpServletRequest req) {
		String searchByPrice = (String) req.getParameter("max-price");
		String countPpl = (String) req.getParameter("people-combo");
		int searchByPriceAvg = (int) Math.round(Double.parseDouble(searchByPrice) / Double.parseDouble(countPpl));

		String[] categories = req.getParameterValues("category");
		String additionalCategory = req.getParameter("additionalCategory");
		// 검색어가 비어 있지 않은 경우, 검색 수행
		List<Cafeteria> searchResults;
		List<String> chkList = new ArrayList<String>();

		// 체크된 카테고리와 추가 카테고리 추가
		if (categories != null) {
			for (String category : categories) {
				chkList.add(category);
			}
		}
		if (additionalCategory != null && !additionalCategory.trim().isEmpty()) {
			chkList.add(additionalCategory);
		}
		if (searchByPrice != null && !searchByPrice.trim().isEmpty()) {
			searchResults = service.getCafeByPriceAndTags(searchByPriceAvg, chkList);
		} else {
			// 검색어가 없으면 전체 가게 목록을 출력
			searchResults = service.selectAll();
		}

		// Cafeteria 결과에 맞는 CafePic 리스트를 개별적으로 가져오기
		List<CafeteriaWithPicDTO> mergedList = new ArrayList<>();
		for (Cafeteria cafeteria : searchResults) {
			List<CafePic> cafePicList = service.selectPicsByCafeNum(cafeteria.getCafeNum());
			mergedList.add(new CafeteriaWithPicDTO(cafeteria, cafePicList));
		}

		// 검색 결과를 JSP로 전달
		req.setAttribute("mergedList", mergedList);

		initTags(req, searchResults);
	}

	private void initTags(HttpServletRequest req, List<Cafeteria> searchResults) {
		// 카페에 대한 태그를 저장할 리스트
		Map<Integer, List<CafeTag>> cafeTagsMap = new HashMap<>();

		// 각 카페에 대해 태그를 조회
		for (Cafeteria cafeteria : searchResults) {
			List<CafeTag> tags = service.selectCafeTag(cafeteria.getCafeNum());
			cafeTagsMap.put(cafeteria.getCafeNum(), tags);
		}

		// 태그 정보를 요청 속성으로 설정
		req.setAttribute("cafeTagsMap", cafeTagsMap);
	}

	private void initUserAttributes(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userSessionID = (String) session.getAttribute("userID");
		String userID = (String) req.getAttribute("userID");
		if (userSessionID != null) {
			User user = (User) userService.userInfo(userSessionID);
			req.setAttribute("userType", user.getUserType());
		}
		req.setAttribute("userID", userID);
	}

	private void initCafeteriaList(HttpServletRequest req) {
		// Cafeteria와 CafePic 리스트 가져오기
		List<Cafeteria> list = service.selectAll();

		// Cafeteria와 CafePic 리스트 병합
		List<CafeteriaWithPicDTO> mergedList = mergeCafeteriaAndPic(list);

		// 병합된 리스트를 JSP로 전달
		req.setAttribute("mergedList", mergedList);

		// service.selectCafeTag(list.get(0).getCafeNum());

		initTags(req, list);
	}

	// Cafeteria와 CafePic을 병합하는 메서드
	private List<CafeteriaWithPicDTO> mergeCafeteriaAndPic(List<Cafeteria> cafeteriaList) {
		List<CafeteriaWithPicDTO> mergedList = new ArrayList<>();

		for (Cafeteria cafeteria : cafeteriaList) {
			// 해당 Cafeteria에 대한 모든 CafePic을 가져옴
			List<CafePic> cafePics = service.selectPicsByCafeNum(cafeteria.getCafeNum());
			mergedList.add(new CafeteriaWithPicDTO(cafeteria, cafePics));
		}

		return mergedList;
	}
}
