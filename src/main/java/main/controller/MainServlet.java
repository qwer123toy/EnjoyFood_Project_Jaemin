package main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cafeteria.CafePic;
import cafeteria.CafeTag;
import cafeteria.Cafeteria;
import cafeteria.CafeteriaService;
import cafeteria.CafeteriaServiceImple;
import cafeteria.CafeteriaWithPicDTO;
import lombok.extern.slf4j.Slf4j;
import user.model.User;
import user.model.UserService;
import user.model.UserServiceImpl;

@WebServlet("/mainpage")
@Slf4j
public class MainServlet extends HttpServlet {
    // Cafeteria와 User 서비스를 초기화
    private final CafeteriaService service = CafeteriaServiceImple.getInstance();
    private final UserService userService = UserServiceImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // "action" 파라미터를 통해 로그아웃 요청을 확인
        String action = req.getParameter("action");

        // 로그아웃 요청이 있으면 해당 메서드로 이동
        if ("logout".equals(action)) {
            handleLogout(req, resp);
            return;
        }

        // 사용자의 세션 정보와 페이지 속성 초기화
        initUserAttributes(req);

        // 모든 식당 목록과 이미지를 합친 DTO 리스트 생성 후 설정
        initCafeteriaList(req);

        // mainpage.jsp로 포워딩하여 데이터를 전달
        req.getRequestDispatcher("/WEB-INF/view/mainpage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자 정보를 초기화하고 설정
        initUserAttributes(req);

        // 검색 쿼리를 초기화하고 결과를 병합하여 설정
        initSearchQuery(req);

        // mainpage.jsp로 포워딩하여 데이터를 전달
        req.getRequestDispatcher("/WEB-INF/view/mainpage.jsp").forward(req, resp);
    }

    /**
     * 사용자의 세션 정보 및 속성을 초기화합니다. 세션에 있는 userID를 통해 
     * 사용자 정보를 가져오고 요청에 속성으로 userType을 추가하여 설정합니다.
     */
    private void initUserAttributes(HttpServletRequest req) {
        HttpSession session = req.getSession();
        String userSessionID = (String) session.getAttribute("userID");
        String userID = (String) req.getAttribute("userID");

        if (userSessionID != null) {
            // 세션에 userID가 존재하면 UserService에서 사용자 정보 가져옴
            User user = userService.userInfo(userSessionID);
            req.setAttribute("userType", user.getUserType()); // userType 속성 설정
        }
        req.setAttribute("userID", userID); // userID 속성 설정
    }

    /**
     * 로그아웃 요청을 처리하는 메서드로, 세션을 무효화하여 모든 세션 속성을 삭제하고 
     * 메인 페이지로 리다이렉트합니다.
     */
    private void handleLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate(); // 세션 무효화
        resp.sendRedirect("mainpage"); // 메인 페이지로 리다이렉트
    }

    /**
     * 모든 식당 목록을 가져와 Cafeteria와 CafePic을 병합한 DTO 리스트를 초기화하고 
     * 요청 속성에 설정합니다.
     */
    private void initCafeteriaList(HttpServletRequest req) {
        List<Cafeteria> list = service.selectAll(); // 모든 식당 리스트 가져오기

        // Cafeteria와 CafePic 리스트 병합
        List<CafeteriaWithPicDTO> mergedList = mergeCafeteriaAndPic(list);

        // 병합된 리스트를 요청 속성에 설정하여 JSP로 전달
        req.setAttribute("mergedList", mergedList);

        // 태그 정보 초기화 및 설정
        initTags(req, list);
    }

    /**
     * 검색 쿼리를 초기화하고 검색된 Cafeteria와 CafePic을 병합하여 
     * 요청 속성으로 설정합니다.
     */
    private void initSearchQuery(HttpServletRequest req) {
        String searchQuery = req.getParameter("searchQuery");
        log.info("검색어: " + searchQuery);

        List<Cafeteria> searchResults;

        // 검색어가 존재할 경우 검색 실행, 없으면 전체 목록을 반환
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            searchResults = service.searchByAll(searchQuery, searchQuery, searchQuery, searchQuery, searchQuery);
        } else {
            searchResults = service.selectAll(); // 검색어가 없으면 전체 목록 반환
        }

        // 검색된 Cafeteria 결과에 맞는 CafePic 리스트와 병합
        List<CafeteriaWithPicDTO> mergedList = new ArrayList<>();
        for (Cafeteria cafeteria : searchResults) {
            List<CafePic> cafePicList = service.selectPicsByCafeNum(cafeteria.getCafeNum());
            mergedList.add(new CafeteriaWithPicDTO(cafeteria, cafePicList));
        }

        // 병합된 리스트를 요청 속성으로 설정하여 JSP로 전달
        req.setAttribute("mergedList", mergedList);

        // 태그 정보 초기화
        initTags(req, searchResults);
    }

    /**
     * 주어진 Cafeteria 리스트와 각 Cafeteria의 CafePic 리스트를 병합하여 
     * CafeteriaWithPicDTO 리스트로 반환합니다.
     */
    private List<CafeteriaWithPicDTO> mergeCafeteriaAndPic(List<Cafeteria> cafeteriaList) {
        List<CafeteriaWithPicDTO> mergedList = new ArrayList<>();

        // 각 Cafeteria에 대해 CafePic 리스트를 가져와 DTO 리스트에 추가
        for (Cafeteria cafeteria : cafeteriaList) {
            List<CafePic> cafePics = service.selectPicsByCafeNum(cafeteria.getCafeNum());
            mergedList.add(new CafeteriaWithPicDTO(cafeteria, cafePics));
        }

        return mergedList;
    }

    /**
     * Cafeteria의 태그 정보를 초기화하고 각 Cafeteria의 ID별로 리스트를 생성하여 
     * 요청 속성으로 설정합니다.
     */
    private void initTags(HttpServletRequest req, List<Cafeteria> searchResults) {
        Map<Integer, List<CafeTag>> cafeTagsMap = new HashMap<>(); // 카페별 태그를 저장할 맵 생성

        // 각 Cafeteria에 대해 태그 리스트 가져와 맵에 추가
        for (Cafeteria cafeteria : searchResults) {
            List<CafeTag> tags = service.selectCafeTag(cafeteria.getCafeNum());
            cafeTagsMap.put(cafeteria.getCafeNum(), tags);
        }

        // 태그 맵을 요청 속성으로 설정하여 JSP로 전달
        req.setAttribute("cafeTagsMap", cafeTagsMap);
    }

}
