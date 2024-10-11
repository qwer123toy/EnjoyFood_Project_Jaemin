package main.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import user.model.User;
import user.model.UserService;
import user.model.UserServiceImpl;

@WebServlet("/admin/search")
@Slf4j
public class AdminServlet extends HttpServlet {
    private final UserService service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 사용자 리스트를 가져오는 요청 처리
        List<User> userList = service.selectAll();
        req.setAttribute("userList", userList);
        req.getRequestDispatcher("/WEB-INF/view/admin-users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        List<User> userList;

        // 사용자가 입력한 ID로 검색
        if (userId != null && !userId.trim().isEmpty()) {
            userList = service.selectAllById(userId);
        } else {
            userList = service.selectAll();
        }

        // 활성화 상태 업데이트
        for (User user : userList) {
            String activeParam = req.getParameter("active_" + user.getUserID());
            int active = Integer.parseInt(activeParam);
            service.updateActivationStatus(user.getUserID(), active);
        }

        List<User> userListAfterActive;
        // 사용자가 입력한 ID로 검색
        if (userId != null && !userId.trim().isEmpty()) {
            userListAfterActive = service.selectAllById(userId);
        } else {
            userListAfterActive = service.selectAll();
        }
        req.setAttribute("userList", userListAfterActive); // 업데이트된 사용자 리스트
        req.getRequestDispatcher("/WEB-INF/view/admin-users.jsp").forward(req, resp); // JSP로 포워딩
    }
}
