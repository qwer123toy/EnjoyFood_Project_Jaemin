package user.model;

public interface UserService {

	User login(User user);

	boolean siunup(User user);

	boolean isIdDuplicate(String id);

	boolean isPhoneNumberDuplicate(String phoneNumber);

	boolean isNicknameDuplicate(String nickname);

	boolean isOwnerNumberDuplicate(String ownerNumber);

}
