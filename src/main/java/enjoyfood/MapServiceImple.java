package enjoyfood;

import org.apache.ibatis.session.SqlSession;

import config.AppContextListener;

public class MapServiceImple implements MapService {
	private static final MapServiceImple instance = new MapServiceImple();

	private MapServiceImple() {

	}

	public static MapService getInstance() {
		return instance;
	}

	@Override
	public String findAddressByName(String cafe_name) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			// BookMapper 인터페이스의 구현체를 SqlSession에서 가져옴
			MapMapper mapper = sqlSession.getMapper(MapMapper.class);
			String address = mapper.findAddressByName(cafe_name);
			
			return address;
		}
	}
	
}
