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
import lombok.extern.slf4j.Slf4j;

@WebServlet("/cafeReview")
@Slf4j
public class CafeReviewServlet extends HttpServlet {
	private CafeteriaService service = CafeteriaServiceImple.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cafeNumStr = req.getParameter("cafeNum");
		int cafeNum = Integer.parseInt(cafeNumStr);
		// 해당 카페 번호를 이용해서 해당 카페의 메뉴를 띄워주기
		// 이용 메뉴 탭에 띄워줄 예정
		// 근데 체크박스로 뜰거임
		// 그래서 나중에 평점, 내용, 금액, 메뉴체크, 이미지 다 넣고 작성 완료 하면
		// DB에 저장되어야 함

		List<Cafeteria> list = service.selectAll();

//		System.out.println(cafeteria);
		req.setAttribute("cafeNum", cafeNum);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/view/cafeReview.jsp").forward(req, resp);
	}
}
