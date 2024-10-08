package main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cafeteria.Cafeteria;
import cafeteria.CafeteriaService;
import cafeteria.CafeteriaServiceImple;

@WebServlet("/cafeReview")
public class CafeReviewServlet extends HttpServlet {
	private CafeteriaService service = CafeteriaServiceImple.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Cafeteria> list = service.selectAll();

//		System.out.println(cafeteria);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/view/cafeReview.jsp").forward(req, resp);
	}
}
