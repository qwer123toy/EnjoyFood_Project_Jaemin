package user.suggestion;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import cafeteria.Cafeteria;
import config.AppContextListener;

@WebServlet("/suggestions")
public class SuggestionsServlet extends HttpServlet {
	private SuggestionService service = SuggestionServiceImpl.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Suggestion> list = (List<Suggestion>) req.getAttribute("list");

		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			SuggestionMapper suggestionMapper = sqlSession.getMapper(SuggestionMapper.class);
			list = suggestionMapper.select();

		}
		System.out.println("출력확인" + list);

		req.setAttribute("listSuggestion", list);
		req.getRequestDispatcher("/WEB-INF/view/admin-suggestions.jsp").forward(req, resp);
	}

}