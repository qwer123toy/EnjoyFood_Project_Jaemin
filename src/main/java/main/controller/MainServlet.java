package main.controller;

import java.io.IOException;
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

@WebServlet("/mainpage")
@Slf4j
public class MainServlet extends HttpServlet {
	private CafeteriaService service = CafeteriaServiceImple.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 List<Cafeteria> list = (List<Cafeteria>) req.getAttribute("list");

		    // 리스트가 없으면 전체 목록 조회
		    if (list == null) {
		        list = service.selectAll();
		    }

		    // 리스트를 요청에 다시 설정하고 JSP로 포워드
		    req.setAttribute("list", list);
		    req.getRequestDispatcher("/WEB-INF/view/mainpage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String searchQuery  = (String) req.getParameter("searchQuery");
		  // 로그 남기기 (검색어 확인)
	    log.info("검색어: " + searchQuery);

	    // 검색어가 비어 있지 않은 경우, 검색 수행
	    List<Cafeteria> searchResults;
	    if (searchQuery != null && !searchQuery.trim().isEmpty()) {
	        searchResults = service.searchByAll(searchQuery, searchQuery, searchQuery, searchQuery, searchQuery);
	        System.out.println(searchResults);
	    } else {
	        // 검색어가 없으면 전체 가게 목록을 출력
	        searchResults = service.selectAll();
	    }

	    // 검색 결과를 JSP로 전달
	    req.setAttribute("list", searchResults);
	    req.getRequestDispatcher("/WEB-INF/view/mainpage.jsp").forward(req, resp);
	}
	
	
}
