package main.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.model.User;
import user.model.UserService;
import user.model.UserServiceImpl;
import java.util.Base64;

@WebServlet("/admin/userPicture")
public class UserPictureServlet extends HttpServlet {
    private final UserService service = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        if (userId != null) {
            User user = service.userInfo(userId);
            if (user != null && user.getUserPicture() != null) {
                String userPicture = user.getUserPicture();
                
                // 앞부분을 제거하여 Base64 데이터만 가져오기
                if (userPicture.startsWith("data:image/png;base64,")) {
                    userPicture = userPicture.substring("data:image/png;base64,".length());
                }
                
                byte[] imageBytes = Base64.getDecoder().decode(userPicture); // Base64 디코딩

                resp.setContentType("image/jpeg");
                resp.getOutputStream().write(imageBytes);
                resp.getOutputStream().flush();
                return; // 이미지를 반환하고 메서드 종료
            } 
        }
    }
}
