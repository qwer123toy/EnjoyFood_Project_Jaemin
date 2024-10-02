package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.WebUtil;
import user.model.User;
import user.model.UserService;
import user.model.UserServiceImpl;

@WebServlet({ "/api/v1/user", "/api/v1/user/*" })
public class UserAPI extends HttpServlet {
	private final WebUtil webUtil = WebUtil.getInstance();
	private final UserService service = UserServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getParameter("method");

		if (method.equalsIgnoreCase("login")) {
			String userID = req.getParameter("userID");
			String userPW = req.getParameter("userPW");
			User user = User.builder().userID(userID).userPW(userPW).build();
			User loginUser = service.login(user);
			System.out.println(loginUser);
			if (loginUser != null) {
				webUtil.setCodeAndMimeType(resp, 200, "plain");
				HttpSession session = req.getSession();
				session.setAttribute("userID", userID);
				session.setAttribute("userNickname", loginUser.getUserNickname());
			} else {
				webUtil.setCodeAndMimeType(resp, 400, "plain");
			}
			return;
		} else if (method.equalsIgnoreCase("chkID")) {
			String userID = req.getParameter("userID");
			if (!service.isIdDuplicate(userID)) {
				webUtil.setCodeAndMimeType(resp, 200, "plain");
			} else {
				webUtil.setCodeAndMimeType(resp, 400, "plain");
			}
		} else if (method.equalsIgnoreCase("chkPhoneNumber")) {
			String userPhoneNumber = req.getParameter("userPhoneNumber");
			if (!service.isPhoneNumberDuplicate(userPhoneNumber)) {
				webUtil.setCodeAndMimeType(resp, 200, "plain");
			} else {
				webUtil.setCodeAndMimeType(resp, 400, "plain");
			}
		} else if (method.equalsIgnoreCase("chkNickname")) {
			String userNickname = req.getParameter("userNickname");
			if (!service.isNicknameDuplicate(userNickname)) {
				webUtil.setCodeAndMimeType(resp, 200, "plain");
			} else {
				webUtil.setCodeAndMimeType(resp, 400, "plain");
			}
		} else if (method.equalsIgnoreCase("chkOwnerNumber")) {
			String userOwnerNumber = req.getParameter("userOwnerNumber");
			if (!service.isOwnerNumberDuplicate(userOwnerNumber)) {
				webUtil.setCodeAndMimeType(resp, 200, "plain");
			} else {
				webUtil.setCodeAndMimeType(resp, 400, "plain");
			}
		}
	}
}
