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
	public CafeteriaService service = CafeteriaServiceImple.getInstance();
	private MapService mapservice = MapServiceImple.getInstance();
	private final UserService userService = UserServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// cafeteria 선언 추가
		try {
			initUser(req);
//			req.setAttribute("userNickName", userNickName);

			// 조회된 가게 정보를 cafeteria로 저장
			String cafeName = req.getParameter("cafeName");
			Cafeteria cafeteria = service.selectByName(cafeName);
			int cafeNum = cafeteria.getCafeNum();
            List<Menu> menuList = service.selectMenu(cafeNum);

			List<CafeReview> cafeReviewList = service.selectCafeReview(cafeteria.getCafeNum());
			double score = service.selectAvg(cafeNum);
			score = Math.round(score * 100) / 100.0;

			List<Integer> cafeCategoryNumList = service.selectCategoryNum(cafeNum);
			List<CafeCategory> cafeCategoryList = new ArrayList<CafeCategory>();

			for (int i = 0; i < cafeCategoryNumList.size(); i++) {
				CafeCategory cafeCategory = service.selectCategory(cafeCategoryNumList.get(i));
				cafeCategoryList.add(cafeCategory);
			}

			List<CafeTag> cafeTagList = service.selectCafeTag(cafeNum);
			
			CafePic cafePic = service.selectCafePic(cafeNum);
			
			Integer customerPaymentAvg = service.selectAvgPayment(cafeNum);
			req.setAttribute("cafePic", cafePic.getCafePic());
			req.setAttribute("menuList", menuList);
			req.setAttribute("cafeteria", cafeteria);
			req.setAttribute("cafeReviewList", cafeReviewList);
			req.setAttribute("cafeCategoryList", cafeCategoryList);
			req.setAttribute("cafeTagList", cafeTagList);
			if (customerPaymentAvg != null)
				req.setAttribute("customerPaymentAvg", customerPaymentAvg);
			req.setAttribute("score", score);
//	        JSP 페이지(cafeDetails.jsp)로 전달하여 렌더링
			req.getRequestDispatcher("/WEB-INF/view/cafeDetails.jsp").forward(req, resp);
		} catch (Exception e) {
			log("Error fetching cafeteria details", e);
			req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
		}
	}

	private void initUser(HttpServletRequest req) {
		HttpSession session = req.getSession();
		String userSessionID = (String) session.getAttribute("userID");

		String userID = (String) req.getAttribute("userID");
		if (userSessionID != null) {
			User user = (User) userService.userInfo(userSessionID);
			req.setAttribute("userType", user.getUserType());
		}
		req.setAttribute("userID", userID);
	}
}
