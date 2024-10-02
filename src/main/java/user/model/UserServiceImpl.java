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
	public boolean siunup(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isIdDuplicate(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isPhoneNumberDuplicate(String phoneNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isNicknameDuplicate(String nickname) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOwnerNumberDuplicate(String ownerNumber) {

		return false;
	}

}
