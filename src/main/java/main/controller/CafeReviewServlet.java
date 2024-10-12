package main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafeteria.CafeReview;
import cafeteria.CafeteriaService;
import cafeteria.CafeteriaServiceImple;
import cafeteria.Menu;
import config.WebUtil;
import lombok.extern.slf4j.Slf4j;
import user.model.User;
import user.model.UserService;
import user.model.UserServiceImpl;

@WebServlet("/cafeReview")
@Slf4j
public class CafeReviewServlet extends HttpServlet {
	private CafeteriaService service = CafeteriaServiceImple.getInstance();
	private final UserService userService = UserServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
		String userSessionID = (String) session.getAttribute("userID");
		
		String userID = (String) req.getAttribute("userID");
		if(userSessionID != null) {
			User user = (User) userService.userInfo(userSessionID);
			req.setAttribute("userType", user.getUserType());			
		}
		req.setAttribute("userID", userID);

		
		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
		
		
		List<Menu> list = service.showCafeMenu(cafeNum);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/view/cafeReview.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청 파라미터에서 값 가져오기
		WebUtil webUtil = new WebUtil();

		CafeReview cafeReview = webUtil.readBodyJson(req, CafeReview.class);
		String userID = (String) req.getSession(false).getAttribute("userID");
		if (userID == null) {
			webUtil.writeBody(resp, "{\"success\":false,\"message\":\"로그인\"}");
			webUtil.setCodeAndMimeType(resp, 401, "json");
			return;
		}
		cafeReview.setUserId(userID);
		int result = service.insertReview(cafeReview);
		if (result == 1) {
			webUtil.writeBody(resp, "{\"success\":true,\"message\":\"성공\"}");
			webUtil.setCodeAndMimeType(resp, 200, "json");
		} else {
			webUtil.writeBody(resp, "{\"success\":false,\"message\":\"실패\"}");
			webUtil.setCodeAndMimeType(resp, 401, "json");
		}
	}
}
