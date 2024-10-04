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

@WebServlet("/cafeteria")
public class CafeDetailsServlet extends HttpServlet {
	private CafeteriaService service = CafeteriaServiceImple.getInstance();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// cafeteria 선언 추가
		 try {
	            Cafeteria cafeteria = service.selectByName("카페 C");
	            req.setAttribute("cafeteria", cafeteria);
	            req.getRequestDispatcher("/WEB-INF/view/cafeDetails.jsp").forward(req, resp);
	        } catch (Exception e) {
	            log("Error fetching cafeteria details", e);
	            req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
	        }
	}
}
