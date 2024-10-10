package main.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Cafeteria.CafeteriaService;
import Cafeteria.CafeteriaServiceImple;
import Cafeteria.Menu;
import config.WebUtil;

@WebServlet("/addMenu")
@MultipartConfig

public class AddMenuServlet extends HttpServlet {
	// CafeteriaService 인스턴스 가져오기 (싱글톤 패턴)
	CafeteriaService service = CafeteriaServiceImple.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/addMenu.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 데이터 받기	
		WebUtil webUtil = new WebUtil();

		Menu menu = webUtil.readBodyJson(req, Menu.class);
		service.insertMenu(menu);
//		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
//		int menuNum = Integer.parseInt(req.getParameter("menuNum"));
//		String menuExplain = req.getParameter("menuExplain");
//		String menuName = req.getParameter("menuName");
//		System.out.println(menuName);
//		String menuPrice = (String) req.getParameter("menuPrice");
//		int parsedMenuPrice = Integer.parseInt(menuPrice);
//		String menuNamepic = req.getParameter("menuNamepic");
//		// insertMenu 메서드 호출하여 DB에 메뉴 추가
//		// Base64로 인코딩된 이미지 데이터 받기
//		String encodedImage = req.getParameter("encodedImage");
//		// Base64 디코딩 및 이미지 저장
//		if (encodedImage != null && !encodedImage.isEmpty()) {
//			byte[] decodedBytes = Base64.getDecoder().decode(encodedImage);
//
//			// 저장할 파일 경로 (예시: 서버의 /uploads 디렉토리)
//			String uploadPath = getServletContext().getRealPath("/uploads");
//			String fileName = menuName + ".png"; // 파일명은 메뉴명 기반으로
//			String filePath = uploadPath + "/" + fileName;
//
//			// 디코딩된 파일 저장
//			try (FileOutputStream fos = new FileOutputStream(filePath)) {
//				fos.write(decodedBytes);
//			}
//		}
//
//		Menus menus = Menus.builder().menuName(menuName).menuPrice(parsedMenuPrice).menuExplain(menuExplain)
//				.menuNamepic(menuNamepic).build();
//		int result = service.insertMenu(menus);
//
//		if (result > 0) {
//			System.out.println("메뉴 추가 성공!");
//		} else {
//			System.out.println("메뉴 추가 실패!");
//		}

	}

}
