package main.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Cafeteria.Cafeteria;
import Cafeteria.CafeteriaService;
import Cafeteria.CafeteriaServiceImple;
import lombok.extern.slf4j.Slf4j;

@WebServlet("/cafeteria")
@Slf4j
public class CafeDetailsServlet extends HttpServlet {
	public CafeteriaService service = CafeteriaServiceImple.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// cafeteria 선언 추가
		 try {
			 // 조회된 가게 정보를 cafeteria로 저장
	            Cafeteria cafeteria = service.selectByName("꽃가람");
	            log.info(cafeteria.toString());
	            req.setAttribute("cafeteria", cafeteria);
//	            JSP 페이지(cafeDetails.jsp)로 전달하여 렌더링
	            req.getRequestDispatcher("/WEB-INF/view/cafeDetails.jsp").forward(req, resp);
	        } catch (Exception e) {
	            log("Error fetching cafeteria details", e);
	            req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
	        }
	}
}
