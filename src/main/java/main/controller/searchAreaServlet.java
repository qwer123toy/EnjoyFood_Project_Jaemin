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

@WebServlet("/searchArea")
public class searchAreaServlet extends HttpServlet {
	private CafeteriaService service = CafeteriaServiceImple.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/view/searchArea.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();

		List<String> bodyJson = webUtil.readBodyJson(req, List.class);
//		System.out.println(bodyJson);
		String cafesByAddress = CafeSQLProvider.getCafesByAddress(bodyJson);
//		System.out.println(cafesByAddress);

		List<Cafeteria> list = service.selectByArea(bodyJson);

		List<CafeteriaWithPicDTO> mergedList = mergeCafeteriaAndPic(list);

//		System.out.println(list);
		webUtil.writeBodyJson(resp, mergedList);
		if (list != null) {
			webUtil.setCodeAndMimeType(resp, 200, "json");
		} else {
			webUtil.setCodeAndMimeType(resp, 400, "json");
		}
	}

	private List<CafeteriaWithPicDTO> mergeCafeteriaAndPic(List<Cafeteria> cafeteriaList) {
		List<CafeteriaWithPicDTO> mergedList = new ArrayList<>();

		for (Cafeteria cafeteria : cafeteriaList) {
			// 해당 Cafeteria에 대한 모든 CafePic을 가져옴
			List<CafePic> cafePics = service.selectPicsByCafeNum(cafeteria.getCafeNum());
			mergedList.add(new CafeteriaWithPicDTO(cafeteria, cafePics));
		}

		return mergedList;
	}
}
