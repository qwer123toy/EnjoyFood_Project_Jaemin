package main.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cafeteria.CafePic;
import cafeteria.CafeSQLProvider;
import cafeteria.Cafeteria;
import cafeteria.CafeteriaService;
import cafeteria.CafeteriaServiceImple;
import cafeteria.CafeteriaWithPicDTO;
import config.WebUtil;

// 서블릿 매핑: /searchArea 요청에 대해 이 서블릿이 호출됨
@WebServlet("/searchArea")
public class searchAreaServlet extends HttpServlet {
	private CafeteriaService service = CafeteriaServiceImple.getInstance(); // CafeteriaService 인스턴스 생성

	// GET 요청을 처리하여 검색 화면으로 포워드
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/view/searchArea.jsp").forward(req, resp); // searchArea.jsp로 포워드
	}

	// POST 요청을 처리하여 지역 기반 검색을 수행
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil(); // 웹 유틸리티 객체 생성

		// JSON 형식으로 전달된 요청 본문을 읽어서 List 형태로 변환
		List<String> bodyJson = webUtil.readBodyJson(req, List.class);
		
		// SQL 쿼리 생성을 위해 주소 목록을 CafeSQLProvider로 전달
		String cafesByAddress = CafeSQLProvider.getCafesByAddress(bodyJson);

		// 주소 조건에 맞는 Cafeteria 리스트를 가져옴
		List<Cafeteria> list = service.selectByArea(bodyJson);

		// Cafeteria와 관련된 CafePic 리스트를 병합하여 DTO 리스트 생성
		List<CafeteriaWithPicDTO> mergedList = mergeCafeteriaAndPic(list);

		// JSON 형식으로 응답 본문에 병합된 리스트를 작성
		webUtil.writeBodyJson(resp, mergedList);

		// 응답 코드 및 MIME 타입 설정
		if (list != null) {
			webUtil.setCodeAndMimeType(resp, 200, "json"); // 성공 응답
		} else {
			webUtil.setCodeAndMimeType(resp, 400, "json"); // 실패 응답
		}
	}

	// Cafeteria와 CafePic 리스트를 병합하여 DTO 리스트 생성
	private List<CafeteriaWithPicDTO> mergeCafeteriaAndPic(List<Cafeteria> cafeteriaList) {
		List<CafeteriaWithPicDTO> mergedList = new ArrayList<>();

		// 각 Cafeteria에 대해 CafePic 리스트를 조회하고 병합
		for (Cafeteria cafeteria : cafeteriaList) {
			List<CafePic> cafePics = service.selectPicsByCafeNum(cafeteria.getCafeNum());
			mergedList.add(new CafeteriaWithPicDTO(cafeteria, cafePics)); // Cafeteria와 관련된 CafePic 리스트를 DTO로 병합
		}

		return mergedList; // 병합된 DTO 리스트 반환
	}
}
