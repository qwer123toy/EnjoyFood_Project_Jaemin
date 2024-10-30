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
    // UserService 인스턴스 생성
    private final UserService service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 모든 사용자 리스트를 가져와 요청 속성에 설정
        List<User> userList = service.selectAll();
        req.setAttribute("userList", userList);

        // admin-users.jsp 페이지로 포워딩하여 사용자 목록을 렌더링
        req.getRequestDispatcher("/WEB-INF/view/admin-users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자가 입력한 ID로 사용자 검색
        String userId = req.getParameter("userId");
        List<User> userList;

        if (userId != null && !userId.trim().isEmpty()) {
            userList = service.selectAllById(userId); // 특정 ID로 사용자 검색
        } else {
            userList = service.selectAll(); // 전체 사용자 검색
        }

        // 활성화 상태 업데이트
        for (User user : userList) {
            String activeParam = req.getParameter("active_" + user.getUserID());
            int active = Integer.parseInt(activeParam); // 활성화 상태를 정수로 변환
            service.updateActivationStatus(user.getUserID(), active); // DB에 상태 업데이트
        }

        // 업데이트된 사용자 리스트를 다시 가져오기
        List<User> userListAfterActive;
        if (userId != null && !userId.trim().isEmpty()) {
            userListAfterActive = service.selectAllById(userId); // 특정 ID로 다시 검색
        } else {
            userListAfterActive = service.selectAll(); // 전체 사용자 다시 검색
        }

        // 업데이트된 사용자 리스트를 요청 속성에 설정
        req.setAttribute("userList", userListAfterActive);

        // 업데이트된 사용자 정보를 admin-users.jsp 페이지로 포워딩
        req.getRequestDispatcher("/WEB-INF/view/admin-users.jsp").forward(req, resp);
    }
}
