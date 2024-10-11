package user.suggestion;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import config.AppContextListener;

public class SuggestionServiceImpl implements SuggestionService {
	private static final SuggestionServiceImpl instance = new SuggestionServiceImpl();

	private SuggestionServiceImpl() {
	}

	public static SuggestionService getInstance() {
		return instance;
	}

	@Override
	public boolean insert(Suggestion suggestion) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession();) {
			SuggestionMapper mapper = sqlSession.getMapper(SuggestionMapper.class);
			int result = mapper.insert(suggestion);
			if (result == 1) {
				sqlSession.commit();
				return true;
			} else {
				sqlSession.rollback();
				return false;
			}
		}
	}

	@Override
	public List<Suggestion> select() {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			SuggestionMapper mapper = sqlSession.getMapper(SuggestionMapper.class);
			List<Suggestion> list = mapper.select();

			return list;
		}
	}

}
