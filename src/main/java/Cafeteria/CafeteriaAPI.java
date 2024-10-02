package Cafeteria;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.extern.slf4j.Slf4j;

@WebServlet("/api/v1/cafeteria")
@Slf4j
public class CafeteriaAPI extends HttpServlet {
	private CafeteriaService service = CafeteriaImple.getInstance();

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
}
