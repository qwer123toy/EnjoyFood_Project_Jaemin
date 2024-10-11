package main.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.json.JsonMapper;

import cafeteria.CafeCategory;
import cafeteria.Cafeteria;
import cafeteria.CafeteriaService;
import cafeteria.CafeteriaServiceImple;
import config.WebUtil;

@WebServlet("/ownerPage")
public class OwnerPage_basic extends HttpServlet {
	private CafeteriaService service = CafeteriaServiceImple.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<CafeCategory> categoryList = service.selectCategoryAll();
		
		req.setAttribute("categoryList", categoryList);
		
		
//		int cafeNum = Integer.parseInt(req.getParameter("cafeNum"));
//		String storeExplain = req.getParameter("storeExplain");
	
		req.getRequestDispatcher("/WEB-INF/view/ownerPage.jsp").forward(req, resp);
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();
		String json = webUtil.readBody(req);
		System.out.println("여기 출력!! : "+ json);
		JsonMapper jsonMapper = new JsonMapper();
		
//		Menu menu = jsonMapper.readValue(json, Menu.class);
		Cafeteria cafeteria = jsonMapper.readValue(json, Cafeteria.class);
		
//		service.insert(cafeteria);
		webUtil.setCodeAndMimeType(resp, 201, "json");
		webUtil.writeBodyJson(resp, cafeteria);
		
		
	
	}
	
}
