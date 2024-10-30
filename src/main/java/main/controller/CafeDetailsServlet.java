package main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafeteria.CafeCategory;
import cafeteria.CafePic;
import cafeteria.CafeReview;
import cafeteria.CafeTag;
import cafeteria.Cafeteria;
import cafeteria.CafeteriaService;
import cafeteria.CafeteriaServiceImple;
import cafeteria.Menu;
import enjoyfood.MapService;
import enjoyfood.MapServiceImple;
import lombok.extern.slf4j.Slf4j;
import user.model.User;
import user.model.UserService;
import user.model.UserServiceImpl;

@WebServlet("/cafeteria")
@Slf4j
public class CafeDetailsServlet extends HttpServlet {
	// 가게 정보 서비스를 위한 인스턴스 생성
	public CafeteriaService service = CafeteriaServiceImple.getInstance();
	private MapService mapservice = MapServiceImple.getInstance();
	private final UserService userService = UserServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 가게 정보 초기화
		try {
			initUser(req); // 사용자 정보를 초기화
			
			// 요청 파라미터로 가게 이름을 가져와 해당 가게 정보를 조회
			String cafeName = req.getParameter("cafeName");
			Cafeteria cafeteria = service.selectByName(cafeName);
			int cafeNum = cafeteria.getCafeNum(); // 가게 번호 조회

			// 메뉴 목록 조회 및 저장
			List<Menu> menuList = service.selectMenu(cafeNum);

			// 가게 리뷰 목록 및 평균 점수 조회
			List<CafeReview> cafeReviewList = service.selectCafeReview(cafeteria.getCafeNum());
			double score = service.selectAvg(cafeNum);
			score = Math.round(score * 100) / 100.0; // 소수 둘째 자리까지 반올림

			// 카테고리 번호 및 카테고리 객체 목록 조회
			List<Integer> cafeCategoryNumList = service.selectCategoryNum(cafeNum);
			List<CafeCategory> cafeCategoryList = new ArrayList<CafeCategory>();

			// 각 카테고리 번호에 대해 카테고리 객체를 조회하여 리스트에 추가
			for (int i = 0; i < cafeCategoryNumList.size(); i++) {
				CafeCategory cafeCategory = service.selectCategory(cafeCategoryNumList.get(i));
				cafeCategoryList.add(cafeCategory);
			}

			// 가게 태그 목록 조회
			List<CafeTag> cafeTagList = service.selectCafeTag(cafeNum);

			// 가게 이미지 조회
			CafePic cafePic = service.selectCafePic(cafeNum);

			// 평균 결제 금액 조회
			Integer customerPaymentAvg = service.selectAvgPayment(cafeNum);

			// 조회된 정보를 JSP에 전달하기 위해 요청 속성에 설정
			req.setAttribute("cafePic", cafePic.getCafePic());
			req.setAttribute("menuList", menuList);
			req.setAttribute("cafeteria", cafeteria);
			req.setAttribute("cafeReviewList", cafeReviewList);
			req.setAttribute("cafeCategoryList", cafeCategoryList);
			req.setAttribute("cafeTagList", cafeTagList);
			if (customerPaymentAvg != null) {
				req.setAttribute("customerPaymentAvg", customerPaymentAvg); // 결제 평균이 null이 아닐 경우에만 설정
			}
			req.setAttribute("score", score); // 가게 평점 설정

			// cafeDetails.jsp로 포워딩하여 렌더링
			req.getRequestDispatcher("/WEB-INF/view/cafeDetails.jsp").forward(req, resp);
		} catch (Exception e) {
			// 예외 발생 시 에러 로그 출력 후 에러 페이지로 포워딩
			log("Error fetching cafeteria details", e);
			req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
		}
	}

	// 사용자 세션 정보를 초기화하는 메서드
	private void initUser(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userSessionID = (String) session.getAttribute("userID");

		String userID = (String) req.getAttribute("userID");
		if (userSessionID != null) {
			User user = (User) userService.userInfo(userSessionID); // 사용자 정보를 조회
			req.setAttribute("userType", user.getUserType()); // 사용자 타입 설정
		}
		req.setAttribute("userID", userID); // 사용자 ID 설정
	}
}
