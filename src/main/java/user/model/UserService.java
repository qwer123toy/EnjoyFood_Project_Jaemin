package user.model;
import user.model.User;

public interface UserService {

	boolean login(User user);

	boolean siunup(User user);

	boolean isIdDuplicate(String id);

	boolean isPhoneNumberDuplicate(String phoneNumber);

	boolean isOwnerNumberDuplicate(String ownerNumber);

}
