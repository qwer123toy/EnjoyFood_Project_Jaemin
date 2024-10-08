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
import cafeteria.Menus;
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
			// 조회된 가게 정보를 cafeteria로 저장
			String cafeName = req.getParameter("cafeName");
			Cafeteria cafeteria = service.selectByName(cafeName);

			log.info(cafeteria.toString());
			req.setAttribute("cafeteria", cafeteria);
			req.setAttribute("address", cafeteria.getCafeAddress());
			req.setAttribute("cafeName", cafeteria.getCafeName());
//	            JSP 페이지(cafeDetails.jsp)로 전달하여 렌더링
			req.getRequestDispatcher("/WEB-INF/view/cafeDetails.jsp").forward(req, resp);
		} catch (Exception e) {
			log("Error fetching cafeteria details", e);
			req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
		}
	}
}

