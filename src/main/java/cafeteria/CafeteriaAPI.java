package cafeteria;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;

import config.WebUtil;
import lombok.extern.slf4j.Slf4j;

@WebServlet("/api/v1/cafeteria")
@Slf4j
public class CafeteriaAPI extends HttpServlet {
	private CafeteriaService service = CafeteriaServiceImple.getInstance();

	// 조회
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Cafeteria> list = service.selectAll();

		resp.setHeader("Content-Type", "application/json; charset=utf-8");
		JsonMapper jsonmapper = new JsonMapper();
		String json = jsonmapper.writeValueAsString(list);

		PrintWriter pw = resp.getWriter();
		pw.print(json);
		pw.flush();
	}

	// 새로 추가
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();
		String json = webUtil.readBody(req);
		JsonMapper jsonMapper = new JsonMapper();
		Menu menus = jsonMapper.readValue(json, Menu.class);
//		Cafeteria cafetria = jsonMapper.readValue(json, Cafeteria.class);

		log.info(menus.toString());

		service.insertMenu(menus);
		webUtil.setCodeAndMimeType(resp, 201, "json");
		webUtil.writeBodyJson(resp, menus);
	}

	// 수정
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();
		String json = webUtil.readBody(req);
		JsonMapper jsonMapper = new JsonMapper();
		Cafeteria cafeteria = jsonMapper.readValue(json, Cafeteria.class);

		Cafeteria updateCafe = service.update(cafeteria);

		if (updateCafe == null) {
			resp.setStatus(404);
			return;
		}

		webUtil.setCodeAndMimeType(resp, 201, "json");
		webUtil.writeBodyJson(resp, updateCafe);
	}

	// 삭제
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		WebUtil webUtil = new WebUtil();
		String json = webUtil.readBody(req);
		JsonMapper jsonMapper = new JsonMapper();

		JsonNode jsonNode = jsonMapper.readTree(json);
		int cafeNum = jsonNode.path("cafeNum").asInt();

		int rows = service.delete(cafeNum);

		if (rows == 1) {
			resp.setStatus(204);
		} else {
			resp.setStatus(404);
		}
	}
}
