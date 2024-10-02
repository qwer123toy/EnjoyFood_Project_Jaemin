package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.getSession().invalidate();
		req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = (String) session.getAttribute("userID");
		String nickname = (String) session.getAttribute("userNickname");
		if (id == null || nickname == null) {
			req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, resp);
		} else {
//			resp.sendRedirect("https://www.naver.com");
		}

	}
}
