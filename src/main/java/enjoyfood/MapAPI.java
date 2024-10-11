package enjoyfood;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/map")
public class MapAPI extends HttpServlet {
    private static final String API_KEY = "1b35dc634ac3bd42d8c421860d626613";
//	 private static final String API_KEY = "c6a61a4bbecc0856bf9d155b6330653a";
    private static final String API_URL = "/WEB-INF/view/map.jsp";
    private MapService service = MapServiceImple.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String cafeName = req.getParameter("cafeName");     
      String address = service.findAddressByName(cafeName);

      // 조회된 address 값을 request 객체에 저장
      req.setAttribute("address", address);

      // JSP 페이지로 포워딩
//      req.getRequestDispatcher(API_URL + "?apikey=" + API_KEY).forward(req, resp);
      System.out.println(address);
      req.getRequestDispatcher(API_URL).forward(req, resp);
    }
}
	