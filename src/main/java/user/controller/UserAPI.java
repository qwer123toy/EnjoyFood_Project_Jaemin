package user.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import config.SessionManager;
import config.WebUtil;
import user.model.AuthRequest;
import user.model.AuthResponse;
import user.model.User;
import user.model.UserService;
import user.model.UserServiceImpl;
import user.model.UserValidator;

@WebServlet({ "/api/v1/user", "/api/v1/user/*" })
public class UserAPI extends HttpServlet {

	private final UserService service = UserServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();

		String action = req.getParameter("action");
		if (action.equals("chkDuple")) {
			String userID = req.getParameter("userID");
			if (userID != null) {
				checkIdDuplicate(resp, webUtil, userID);
				return;
			}
			String userNickname = req.getParameter("userNickname");
			if (userNickname != null) {
				checkNicknameDuplicate(resp, webUtil, userNickname);
				return;
			}
			String userPhoneNumber = req.getParameter("userPhoneNumber");
			if (userPhoneNumber != null) {
				checkPhoneNumberDuplicate(resp, webUtil, userPhoneNumber);
				return;
			}
			String userOwnerNumber = req.getParameter("userOwnerNumber");
			if (userOwnerNumber != null) {
				checkOwnerNumberDuplicate(resp, webUtil, userOwnerNumber);
				return;
			}
		} else if (action.equals("findID")) {
			String userPhoneNumber = req.getParameter("userPhoneNumber");
			handleFindId(resp, webUtil, userPhoneNumber);
			return;
		} else if (action.equals("findPW")) {
			String userID = req.getParameter("userID");
			String userPhoneNumber = req.getParameter("userPhoneNumber");
			handleFindPw(resp, webUtil, userID, userPhoneNumber);
			return;
		}
		handleBadRequest(resp, webUtil);
	}

	private void handleFindPw(HttpServletResponse resp, WebUtil webUtil, String userID, String userPhoneNumber)
			throws IOException {
		User user = service.findUser(userPhoneNumber);
		if (user != null && user.getUserID().equals(userID)) {
			webUtil.setCodeAndMimeType(resp, 200, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(true, "일치하는 계정 확인"));
		} else {
			webUtil.setCodeAndMimeType(resp, 404, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(false, "일치하는 계정이 없습니다."));
		}
	}

	private void handleFindId(HttpServletResponse resp, WebUtil webUtil, String userPhoneNumber) throws IOException {
		User user = service.findUser(userPhoneNumber);
		if (user != null) {
			webUtil.setCodeAndMimeType(resp, 200, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(true, "아이디는 [" + user.getUserID() + "] 입니다."));
		} else {
			webUtil.setCodeAndMimeType(resp, 404, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(false, "일치하는 계정이 없습니다."));
		}
	}

	private void checkOwnerNumberDuplicate(HttpServletResponse resp, WebUtil webUtil, String userOwnerNumber)
			throws IOException {
		if (!service.isOwnerNumberDuplicate(userOwnerNumber)) {
			webUtil.setCodeAndMimeType(resp, 200, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(true, "사용 가능한 사업주 번호"));
		} else {
			webUtil.setCodeAndMimeType(resp, 409, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(false, "사업주 번호 중복"));
		}
	}

	private void checkPhoneNumberDuplicate(HttpServletResponse resp, WebUtil webUtil, String userPhoneNumber)
			throws IOException {
		if (!service.isPhoneNumberDuplicate(userPhoneNumber)) {
			webUtil.setCodeAndMimeType(resp, 200, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(true, "사용 가능한 전화번호"));
		} else {
			webUtil.setCodeAndMimeType(resp, 409, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(false, "전화번호 중복"));
		}
	}

	private void checkNicknameDuplicate(HttpServletResponse resp, WebUtil webUtil, String userNickname)
			throws IOException {
		if (!service.isNicknameDuplicate(userNickname)) {
			webUtil.setCodeAndMimeType(resp, 200, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(true, "사용 가능한 닉네임"));
		} else {
			webUtil.setCodeAndMimeType(resp, 409, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(false, "닉네임 중복"));
		}
	}

	private void checkIdDuplicate(HttpServletResponse resp, WebUtil webUtil, String userID) throws IOException {
		if (!service.isIdDuplicate(userID)) {
			webUtil.setCodeAndMimeType(resp, 200, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(true, "사용 가능한 아이디"));
		} else {
			webUtil.setCodeAndMimeType(resp, 409, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(false, "아이디 중복"));
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();

		AuthRequest authRequest = webUtil.readBodyJson(req, AuthRequest.class);

		String action = authRequest.getAction();
		User user = authRequest.getUser();

		switch (action) {
		case "login":
			handleLogin(req, resp, webUtil, user);
			break;
		case "signup":
			handleSignup(resp, webUtil, user);
			break;
		default:
			handleBadRequest(resp, webUtil);
			break;
		}
	}

	private void handleLogin(HttpServletRequest req, HttpServletResponse resp, WebUtil webUtil, User user)
			throws IOException {
		User loginUser = service.login(user);
		if (loginUser == null) {
			webUtil.setCodeAndMimeType(resp, 401, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(false, "아이디 또는 비밀번호가 잘못되었습니다."));
		} else {
			HttpSession oldSession = SessionManager.getSession(loginUser.getUserID());
			if (oldSession != null) {
				try {
					oldSession.invalidate();
				} catch (IllegalStateException e) {
					log("Session has already been invalidated.");
				}
			}
			HttpSession session = req.getSession();
			session.setAttribute("userID", loginUser.getUserID());
			session.setAttribute("userNickname", loginUser.getUserNickname());
			SessionManager.addSession(loginUser.getUserID(), session);
			webUtil.setCodeAndMimeType(resp, 200, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(true, "로그인 성공"));
		}
	}

	private void handleSignup(HttpServletResponse resp, WebUtil webUtil, User user) throws IOException {
		Map<String, String> map = UserValidator.validate(user);
		if (map.containsKey("valid")) {
			map.clear();
			if (service.isIdDuplicate(user.getUserID())) {
				map.put("error", "아이디 중복 에러");
			} else if (service.isNicknameDuplicate(user.getUserNickname())) {
				map.put("error", "닉네임 중복 에러");
			} else if (service.isPhoneNumberDuplicate(user.getUserPhoneNumber())) {
				map.put("error", "번화번호 중복 에러");
			} else if (user.getUserOwnerNumber().length() > 0
					&& service.isOwnerNumberDuplicate(user.getUserOwnerNumber())) {
				map.put("error", "사업자번호 중복 에러");
			} else {
				if (user.getUserOwnerNumber().length() > 0) {
					user.setUserType(1);
				} else {
					user.setUserType(2);
					user.setUserOwnerNumber(null);
					user.setUserPicture(null);
				}
				if (service.signup(user)) {
					map.put("success", "회원가입 성공");
				} else {
					map.put("error", "회원가입 에러");
				}
			}
		}

		if (map.containsKey("error")) {
			webUtil.setCodeAndMimeType(resp, 400, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(false, map.get("error")));
		} else {
			webUtil.setCodeAndMimeType(resp, 201, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(true, "회원가입 성공"));
		}
	}

	private void handleBadRequest(HttpServletResponse resp, WebUtil webUtil) throws IOException {
		webUtil.setCodeAndMimeType(resp, 400, "json");
		webUtil.writeBodyJson(resp, new AuthResponse(false, "잘못된 요청입니다."));
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();

		AuthRequest authRequest = webUtil.readBodyJson(req, AuthRequest.class);

		String action = authRequest.getAction();
		User user = authRequest.getUser();

		switch (action) {
		case "changePW":
			handleChangePW(resp, webUtil, user);
			break;
		default:
			handleBadRequest(resp, webUtil);
			break;
		}
	}

	private void handleChangePW(HttpServletResponse resp, WebUtil webUtil, User user) throws IOException {
		if (UserValidator.isValidPW(user.getUserPW())) {
			if (service.changePW(user)) {
				webUtil.setCodeAndMimeType(resp, 200, "json");
				webUtil.writeBodyJson(resp, new AuthResponse(true, "비밀번호 변경 성공"));
			} else {
				webUtil.setCodeAndMimeType(resp, 400, "json");
				webUtil.writeBodyJson(resp, new AuthResponse(false, "비밀번호 변경 에러"));
			}
		} else {
			webUtil.setCodeAndMimeType(resp, 400, "json");
			webUtil.writeBodyJson(resp, new AuthResponse(false, "잘못된 비밀번호 형식"));
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doDelete(req, resp);
	}
}
