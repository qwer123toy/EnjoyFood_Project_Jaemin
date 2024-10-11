package main.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cafeteria.Cafeteria;
import cafeteria.CafeteriaService;
import cafeteria.CafeteriaServiceImple;
import cafeteria.Menu;
import enjoyfood.MapService;
import enjoyfood.MapServiceImple;
import lombok.extern.slf4j.Slf4j;

@WebServlet("/cafeteria")
@Slf4j
public class CafeDetailsServlet extends HttpServlet {
	public CafeteriaService service = CafeteriaServiceImple.getInstance();
	private MapService mapservice = MapServiceImple.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// cafeteria 선언 추가
		try {
			 // action 파라미터로 로그아웃 요청 확인
	        String action = req.getParameter("action");
	        
	        // 로그아웃 처리
	        if ("logout".equals(action)) {
	            req.getSession().invalidate(); // 세션 무효화
	            resp.sendRedirect("mainpage"); // 로그인 페이지로 리다이렉트
	            return;
	        }
	        String userID = (String) req.getAttribute("userID");
			String userNickName = (String) req.getAttribute("userNickname");
			req.setAttribute("userID", userID);
			req.setAttribute("userNickName", userNickName);
			
			
			// 조회된 가게 정보를 cafeteria로 저장
			String cafeName = req.getParameter("cafeName");
			Cafeteria cafeteria = service.selectByName(cafeName);
			
			double score = service.selectAvg(cafeteria.getCafeNum());
			score = Math.round(score*100)/100.0;
			System.out.println(score);
			
			req.setAttribute("cafeteria", cafeteria);
			req.setAttribute("address", cafeteria.getCafeAddress());
			req.setAttribute("cafeName", cafeteria.getCafeName());
			req.setAttribute("score", score);
//	            JSP 페이지(cafeDetails.jsp)로 전달하여 렌더링
			req.getRequestDispatcher("/WEB-INF/view/cafeDetails.jsp").forward(req, resp);
		} catch (Exception e) {
			log("Error fetching cafeteria details", e);
			req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
		}
	}
}

