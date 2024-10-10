package user.model;

public interface UserService {

	User login(User user);

	User userInfo(String id);

	boolean signup(User user);

	boolean isIdDuplicate(String id);

	boolean isPhoneNumberDuplicate(String phoneNumber);

	boolean isNicknameDuplicate(String nickname);

	boolean isOwnerNumberDuplicate(String ownerNumber);

	User findUser(String phoneNumber);

	boolean update(User user);

	boolean changePW(User user);
}
