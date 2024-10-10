package user.model;

import org.apache.ibatis.session.SqlSession;

import config.AppContextListener;

public class UserServiceImpl implements UserService {

	private static final UserService instance = new UserServiceImpl();

	private UserServiceImpl() {
		super();
	}

	public static UserService getInstance() {
		return instance;
	}

	@Override
	public User login(User user) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			User selectById = mapper.selectById(user.getUserID());
			if (selectById == null)
				return null;
			if (selectById.getUserPW().equals(user.getUserPW())) {
				return selectById;
			}
			return null;
		}
	}

	@Override
	public boolean signup(User user) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			int result = mapper.insert(user);
			sqlSession.commit();
			return result == 1;
		}
	}

	@Override
	public boolean isIdDuplicate(String id) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			int result = mapper.countUserById(id);
			return result == 1;
		}
	}

	@Override
	public boolean isPhoneNumberDuplicate(String phoneNumber) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			int result = mapper.countByPhoneNumber(phoneNumber);
			return result == 1;
		}
	}

	@Override
	public boolean isNicknameDuplicate(String nickname) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			int result = mapper.countByNickname(nickname);
			return result == 1;
		}
	}

	@Override
	public boolean isOwnerNumberDuplicate(String ownerNumber) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			int result = mapper.countByOwnerNumber(ownerNumber);
			return result == 1;
		}
	}

	@Override
	public User findUser(String phoneNumber) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			User selectByPhoneNumber = mapper.selectByPhoneNumber(phoneNumber);
			return selectByPhoneNumber;
		}
	}

	@Override
	public boolean update(User user) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			int result = mapper.update(user);
			return result == 1;
		}
	}

	@Override
	public boolean changePW(User user) {
		try (SqlSession sqlSession = AppContextListener.getSqlSession()) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			int result = mapper.updatePW(user);
			sqlSession.commit();
			return result == 1;
		}
	}

}
