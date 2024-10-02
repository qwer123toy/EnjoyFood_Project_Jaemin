package Cafeteria;

import java.awt.print.Book;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import config.AppContextListener;

public class CafeteriaImple implements CafeteriaService {

	public static final CafeteriaImple instance = new CafeteriaImple();

	private CafeteriaImple() {

	}

	public static CafeteriaService getInstance() {

		return instance;
	}

	@Override
	public List<Cafeteria> selectAll() {
		// SqlSession 사용 및 Mapper호출하여 list 반환
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
			List<Cafeteria> list = mapper.selectAll();

			return list;
		}
	}
}
