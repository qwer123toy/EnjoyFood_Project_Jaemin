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

import cafeteria.CafeTag;
import cafeteria.Cafeteria;
import cafeteria.CafeteriaService;
import cafeteria.CafeteriaServiceImple;
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
		// action 파라미터로 로그아웃 요청 확인
		String action = req.getParameter("action");

		// 로그아웃 처리
		if ("logout".equals(action)) {
			req.getSession().invalidate(); // 세션 무효화
			resp.sendRedirect("mainpage"); // 로그인 페이지로 리다이렉트
			return;
		}
		HttpSession session = req.getSession();
		String userSessionID = (String) session.getAttribute("userID");

		String userID = (String) req.getAttribute("userID");
		if (userSessionID != null) {
			User user = (User) userService.userInfo(userSessionID);
			req.setAttribute("userType", user.getUserType());
		}
		req.setAttribute("userID", userID);

		List<Cafeteria> list = (List<Cafeteria>) req.getAttribute("list");

		// 리스트가 없으면 전체 목록 조회
		if (list == null) {
			list = service.selectAll();
		}

		// service.selectCafeTag(list.get(0).getCafeNum());

		// 카페에 대한 태그를 저장할 리스트
		Map<Integer, List<CafeTag>> cafeTagsMap = new HashMap<>();

		// 각 카페에 대해 태그를 조회
		for (Cafeteria cafeteria : list) {
			List<CafeTag> tags = service.selectCafeTag(cafeteria.getCafeNum());
			cafeTagsMap.put(cafeteria.getCafeNum(), tags);
		}

		// 태그 정보를 요청 속성으로 설정
		req.setAttribute("cafeTagsMap", cafeTagsMap);

		// 리스트를 요청에 다시 설정하고 JSP로 포워드
		req.setAttribute("list", list);

		req.getRequestDispatcher("/WEB-INF/view/searchCategory.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String searchByPrice = (String) req.getParameter("max-price");
		String countPpl = (String) req.getParameter("people-combo");
		int searchByPriceAvg = (int) Math.round(Double.parseDouble(searchByPrice) / Double.parseDouble(countPpl));

		String[] categories = req.getParameterValues("category");
		String additionalCategory = req.getParameter("additionalCategory");
		// 검색어가 비어 있지 않은 경우, 검색 수행
		List<Cafeteria> searchResults;
		List<String> chkList = new ArrayList<String>();

		HttpSession session = req.getSession();
		String userSessionID = (String) session.getAttribute("userID");
		String userID = (String) req.getAttribute("userID");
		if (userSessionID != null) {
			User user = (User) userService.userInfo(userSessionID);
			req.setAttribute("userType", user.getUserType());
		}
		req.setAttribute("userID", userID);

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

		// 카페에 대한 태그를 저장할 리스트
		Map<Integer, List<CafeTag>> cafeTagsMap = new HashMap<>();

		System.out.println(searchResults);
		// 각 카페에 대해 태그를 조회
		for (Cafeteria cafeteria : searchResults) {
			List<CafeTag> tags = service.selectCafeTag(cafeteria.getCafeNum());
			cafeTagsMap.put(cafeteria.getCafeNum(), tags);
		}

		// 태그 정보를 요청 속성으로 설정
		req.setAttribute("cafeTagsMap", cafeTagsMap);

		// 검색 결과를 JSP로 전달
		req.setAttribute("list", searchResults);
		req.getRequestDispatcher("/WEB-INF/view/searchCategory.jsp").forward(req, resp);
	}
}
