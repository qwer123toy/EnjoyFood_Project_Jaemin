package cafeteria;
//
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
			mapper.insert(cafetria);

			sqlSession.commit();

			return cafetria.getCafeNum();
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
	public int insertMenu(Menu menu) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
			int pk = mapper.insertMenu(menu);

			sqlSession.commit();

			return pk;
		}
	}

	@Override
	public List<Cafeteria> searchByAll(String menuName, String categoryName, String cafetag, String cafeName,
			String cafeAddress) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
			List<Cafeteria> list = mapper.searchByAll(menuName, categoryName, cafetag, cafeName, cafeAddress);

			return list;
		}
	}

//	@Override
//	public List<Cafeteria> searchByPT(int cafePrice, List<String> cafetags) {
//		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
//			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
//
//			List<Cafeteria> resultList = mapper.searchByPT(cafePrice, cafetags)
//					;
//
////			if (cafetags == null || cafetags.isEmpty()) {
////				resultList = mapper.searchByPT(cafePrice, null); // 태그를 null로 전달
////			} else {
////				// 각 태그를 싱글 쿼테이션으로 감싸기
////				String tags = cafetags.stream().map(tag -> "'" + tag + "'") // 각 태그를 '태그' 형식으로 감싸기
////						.collect(Collectors.joining(",")); // 콤마로 연결
////
////				// 만약 맨 앞과 맨 뒤에 쌍따옴표가 필요 없다면
////				// tags 변수에는 따옴표가 포함된 상태로 저장되므로 추가적인 조작이 필요 없습니다
////
////				resultList = mapper.searchByPT(cafePrice, tags);
////			}
//
//			return resultList;
//		}
//	}

	@Override
	public double selectAvg(int cafeNum) {
		// TODO Auto-generated method stub
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
			String score = mapper.selectAvg(cafeNum);
			double parsedScore = 0;
			if (score != null) {
				parsedScore = Double.parseDouble(score);
			}

			return parsedScore;
		}
	}

	@Override
	public List<Cafeteria> getCafeByPriceAndTags(int cafePrice, List<String> cafetags) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaDynamicMapper mapper = sqlSession.getMapper(CafeteriaDynamicMapper.class);

			List<Cafeteria> resultList = mapper.getCafeByPriceAndTags(cafePrice, cafetags);

			return resultList;
		}
	}

	@Override
	public List<Menu> showCafeMenu(int cafeNum) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession();) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);

			List<Menu> resultList = mapper.showCafeMenu(cafeNum);
			return resultList;
		}
	}

	@Override
	public int insertReview(CafeReview cafeReview) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
			int rew = mapper.insertReview(cafeReview);

			sqlSession.commit();

			return rew;
		}
	}

	@Override
	public List<CafeReview> selectCafeReview(int cafeNum) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession();) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);

			List<CafeReview> resultList = mapper.selectCafeReview(cafeNum);
			return resultList;
		}
	}

	@Override
	public int insertCategoryM(int cafeNum, int categoryNum) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
			int rew = mapper.insertCategoryM(cafeNum, categoryNum);

			sqlSession.commit();

			return rew;
		}
	}

	@Override
	public int insertPic(int cafeNum, String cafePic) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
			int rew = mapper.insertPic(cafeNum, cafePic);

			sqlSession.commit();

			return rew;
		}
	}

	@Override
	public int insertTag(int cafeNum, String cafeTag) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);
			int rew = mapper.insertTag(cafeNum, cafeTag);

			sqlSession.commit();

			return rew;
		}
	}

	@Override
	public List<CafeCategory> selectCategoryAll() {
		try (SqlSession sqlSession = AppContextListener.getSqlSession();) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);

			List<CafeCategory> resultList = mapper.selectCategoryAll();
			return resultList;
		}
	}

	@Override
	public List<String> selectCafePic(int cafeNum) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession();) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);

			List<String> resultList = mapper.selectCafePic(cafeNum);
			return resultList;
		}
	}

	@Override
	public List<Integer> selectCategoryNum(int cafeNum) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession();) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);

			List<Integer> resultList = mapper.selectCategoryNum(cafeNum);
			return resultList;
		}
	}

	@Override
	public CafeCategory selectCategory(int categoryNum) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession();) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);

			CafeCategory result = mapper.selectCategory(categoryNum);
			return result;
		}
	}

	@Override
	public List<CafeTag> selectCafeTag(int cafeNum) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession();) {
			CafeteriaMapper mapper = sqlSession.getMapper(CafeteriaMapper.class);

			List<CafeTag> resultList = mapper.selectCafeTag(cafeNum);
			return resultList;
		}
	}
}
