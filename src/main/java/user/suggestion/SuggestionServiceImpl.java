package user.suggestion;

import org.apache.ibatis.session.SqlSession;

import config.AppContextListener;

public class SuggestionServiceImpl implements SuggestionService {

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

}
