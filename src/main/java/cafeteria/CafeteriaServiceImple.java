package cafeteria;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import config.AppContextListener;

public class CafeteriaServiceImple implements CafeteriaService {
	// 싱글턴 패턴?
	public static final CafeteriaServiceImple instance = new CafeteriaServiceImple();

	private CafeteriaServiceImple() {

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

	@Override
	public int insert(Cafeteria cafetria) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
			int pk = mapper.insert(cafetria);

			sqlSession.commit();

			return pk;
		}
	}

	@Override
	public Cafeteria update(Cafeteria cafeteria) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
			int rows = mapper.update(cafeteria);

			if (rows == 1) {
				sqlSession.commit();
				return cafeteria;
			}
		}
		return null;
	}

	@Override
	public int delete(int cafeNum) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
			int rows = mapper.delete(cafeNum);

			if (rows == 1) {
				sqlSession.commit();
				return rows;
			}
		}
		return 0;
	}

	@Override
	public Cafeteria selectByName(String cafeName) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
			Cafeteria cafeteria = mapper.selectByName(cafeName);

			return cafeteria;
		}
	}

	@Override
	public List<Cafeteria> showByPic(int cafeNum) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
			List<Cafeteria> cafeteria = mapper.showByPic(cafeNum);

			return cafeteria;
		}
	}

	@Override
	public int insertMenu(Menus menus) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
			int pk = mapper.insertMenu(menus);

			sqlSession.commit();

			return pk;
		}
	}

	@Override
	public List<Cafeteria> searchByAll(String menuName, String cafeCategory, String cafeTag, String cafeName,
			String cafeAddress) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
			List<Cafeteria> list = mapper.searchByAll(menuName, cafeCategory, cafeTag, cafeName, cafeAddress);

			return list;
		}
	}

}
