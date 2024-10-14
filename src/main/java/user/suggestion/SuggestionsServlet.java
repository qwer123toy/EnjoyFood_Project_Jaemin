package user.suggestion;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

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

		req.setAttribute("listSuggestion", list);
		req.getRequestDispatcher("/WEB-INF/view/admin-suggestions.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Suggestion> list = service.select();

		 // 활성화 상태 업데이트
       for (Suggestion suggestion : list) {
           String activeParam = req.getParameter("active_" + suggestion.getSuggestionId());
           int active = Integer.parseInt(activeParam);
           service.updateSuggestionStatus(suggestion.getSuggestionId(), active);
       }
       
       List<Suggestion> listAfterActive = service.select();;

		req.setAttribute("listSuggestion", listAfterActive);
		req.getRequestDispatcher("/WEB-INF/view/admin-suggestions.jsp").forward(req, resp);
	}
	
	

}