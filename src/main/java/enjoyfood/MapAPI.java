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
	private static final String API_URL = "/WEB-INF/view/map.jsp";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher(API_URL).forward(req, resp);
	}

	
	
//	public static void main(String[] args) {
//		try {
//            // API 요청 URL 생성
//            String requestUrl = API_URL + "?apikey=" + API_KEY;
//
//            // URL 객체 생성
//            URL url = new URL(requestUrl);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            // 응답 코드 확인
//            int responseCode = conn.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                // 응답 데이터 읽기
//                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                String inputLine;
//                StringBuilder response = new StringBuilder();
//
//                while ((inputLine = in.readLine()) != null) {
//                    response.append(inputLine);
//                }
//                in.close();
//
//                // 응답 출력
//                System.out.println("Response: " + response.toString());
//            } else {
//                System.out.println("GET 요청 실패: " + responseCode);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
	
