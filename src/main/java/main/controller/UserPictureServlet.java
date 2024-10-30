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

// UserPictureServlet 클래스는 사용자 프로필 사진을 반환하는 서블릿
@WebServlet("/admin/userPicture") // 이 서블릿의 URL 매핑
public class UserPictureServlet extends HttpServlet {
    private final UserService service = UserServiceImpl.getInstance(); // UserService 인스턴스 초기화

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId"); // 요청 파라미터에서 userId 가져오기
        if (userId != null) { // userId가 null이 아닐 경우
            User user = service.userInfo(userId); // 사용자 정보를 조회
            if (user != null && user.getUserPicture() != null) { // 사용자 정보가 존재하고 프로필 사진이 있을 경우
                String userPicture = user.getUserPicture(); // 사용자 사진 정보 가져오기
                
                // Base64 데이터에서 "data:image/png;base64," 부분 제거
                if (userPicture.startsWith("data:image/png;base64,")) {
                    userPicture = userPicture.substring("data:image/png;base64,".length()); // Base64 문자열 추출
                }
                
                byte[] imageBytes = Base64.getDecoder().decode(userPicture); // Base64 문자열을 바이트 배열로 디코딩

                resp.setContentType("image/jpeg"); // 응답 콘텐츠 타입을 이미지 JPEG로 설정
                resp.getOutputStream().write(imageBytes); // 디코딩된 이미지 바이트를 응답으로 작성
                resp.getOutputStream().flush(); // 응답 스트림 플러시
                return; // 이미지를 반환하고 메서드 종료
            } 
        }
    }
}
