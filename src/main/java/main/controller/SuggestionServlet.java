package main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import config.WebUtil;
import user.suggestion.Suggestion;
import user.suggestion.SuggestionServiceImpl;

@WebServlet("/userSuggestion")
public class SuggestionServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("user", null);
		req.getRequestDispatcher("/WEB-INF/view/userSuggestion.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();

		Suggestion suggestion = webUtil.readBodyJson(req, Suggestion.class);
		System.out.println(suggestion);
		String userID = (String) req.getSession(false).getAttribute("userID");
		if (userID == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		suggestion.setUserId(userID);
		if (new SuggestionServiceImpl().insert(suggestion)) {
			resp.sendRedirect(req.getContextPath() + "/mainpage");
		} else {
			resp.sendRedirect(req.getContextPath() + "/userSuggestion");
		}

	}
}
