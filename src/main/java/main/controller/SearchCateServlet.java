package main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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


@WebServlet("/searchCategory")
@Slf4j
public class SearchCateServlet extends HttpServlet {
	private CafeteriaService service = CafeteriaServiceImple.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 // action 파라미터로 로그아웃 요청 확인
        String action = req.getParameter("action");
        
        // 로그아웃 처리
        if ("logout".equals(action)) {
            req.getSession().invalidate(); // 세션 무효화
            resp.sendRedirect("mainpage"); // 로그인 페이지로 리다이렉트
            return;
        }
        String userID = (String) req.getAttribute("userID");
		String userNickName = (String) req.getAttribute("userNickname");
		req.setAttribute("userID", userID);
		req.setAttribute("userNickName", userNickName);
		
		
		List<Cafeteria> list = (List<Cafeteria>) req.getAttribute("list");

	    // 리스트가 없으면 전체 목록 조회
	    if (list == null) {
	        list = service.selectAll();
	    }

	    // 리스트를 요청에 다시 설정하고 JSP로 포워드
	    req.setAttribute("list", list);
		
		req.getRequestDispatcher("/WEB-INF/view/searchCategory.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String searchByPrice  = (String) req.getParameter("max-price");
		String countPpl = (String) req.getParameter("people-combo");
		int searchByPriceAvg = (int) Math.round(Double.parseDouble(searchByPrice)/Double.parseDouble(countPpl));
		
		String[] categories = req.getParameterValues("category");
	    String additionalCategory = req.getParameter("additionalCategory");
	    System.out.println("Categories: " + Arrays.toString(categories));  // For debugging
	    System.out.println("Additional Category: " + additionalCategory);  // For debugging
	    // 검색어가 비어 있지 않은 경우, 검색 수행
	    List<Cafeteria> searchResults;
	    List<String> chkList= new ArrayList<String>();
	    
	    // 체크된 카테고리와 추가 카테고리 추가
	    if (categories != null) {
	        for (String category : categories) {
	            chkList.add(category);
	        }
	    }
	    if (additionalCategory != null && !additionalCategory.trim().isEmpty()) {
	        chkList.add(additionalCategory);
	    }
	    if (searchByPrice != null && !searchByPrice.trim().isEmpty()) {
	        searchResults = service.getCafeByPriceAndTags(searchByPriceAvg, chkList);
	    } else {
	        // 검색어가 없으면 전체 가게 목록을 출력
	        searchResults = service.selectAll();
	    }

	    // 검색 결과를 JSP로 전달
	    req.setAttribute("list", searchResults);
	    req.getRequestDispatcher("/WEB-INF/view/searchCategory.jsp").forward(req, resp);
	}
}
